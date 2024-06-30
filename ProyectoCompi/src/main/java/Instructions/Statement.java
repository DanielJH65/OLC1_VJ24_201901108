/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Expressions.Native;
import Symbol.Symbol;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;

/**
 *
 * @author daniel
 */
public class Statement extends Instruction {

    private boolean mutable;
    private String id;
    private Instruction expression;

    public Statement(boolean mutable, String id, Instruction expression, Type type, int line, int col) {
        super(type, line, col);
        this.mutable = mutable;
        this.id = id;
        this.expression = expression;
    }

    public Statement(boolean mutable, String id, Type type, int line, int col) {
        super(type, line, col);
        this.mutable = mutable;
        this.id = id;
        switch (type.getType()) {
            case INTEGER ->
                this.expression = new Native(0, new Type(TipoDato.INTEGER), line, col);
            case DOUBLE ->
                this.expression = new Native(0.0, new Type(TipoDato.DOUBLE), line, col);
            case STRING ->
                this.expression = new Native("", new Type(TipoDato.STRING), line, col);
            case CHAR ->
                this.expression = new Native('\u0000', new Type(TipoDato.CHAR), line, col);
            case BOOLEAN ->
                this.expression = new Native(true, new Type(TipoDato.BOOLEAN), line, col);
        }
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var expression = this.expression.interpretar(tree, table);

        if (expression instanceof Errores) {
            return expression;
        }

        if (this.expression.getType().getType() != this.getType().getType()) {
            return new Errores("Sintactico", "Variable de distinto tipo", this.getLine(), this.getCol());
        }

        Symbol sym = new Symbol(this.getType(), this.id, expression, this.mutable, this.getLine(), this.getCol());

        boolean created = table.setVariable(sym);

        if (!created) {
            return new Errores("Sintactico", "Identificador de variable existente", this.getLine(), this.getCol());
        }

        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoLA = "n" + tree.getContAST();
        String nodoMUT = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoDOSP = "n" + tree.getContAST();
        String nodoTYPE = "n" + tree.getContAST();
        String nodoEQUAL = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();
        
        String nodoMUT2 = "n" + tree.getContAST();
        String nodoTYPE2 = "n" + tree.getContAST();

        String result = nodoLA + "[label=\"VARIABLE STATEMENT\"];\n";
        result += previous + " -> " + nodoLA + ";\n";

        result += nodoMUT + "[label=\"MUTABLE\"];\n";
        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoDOSP + "[label=\":\"];\n";
        result += nodoTYPE + "[label=\"TYPE\"];\n";
        result += nodoEQUAL + "[label=\"=\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";
        result += nodoLA + " -> " + nodoMUT + ";\n";
        result += nodoLA + " -> " + nodoID + ";\n";
        result += nodoLA + " -> " + nodoDOSP + ";\n";
        result += nodoLA + " -> " + nodoTYPE + ";\n";
        result += nodoLA + " -> " + nodoEQUAL + ";\n";
        result += nodoLA + " -> " + nodoEXP + ";\n";

        result += nodoMUT2 + "[label=\"" + (this.mutable ? "var" : "const") + "\"];\n";
        result += nodoMUT + " -> " + nodoMUT2 + ";\n";
        
        result += nodoTYPE2 + "[label=\"" + this.getType().getType() + "\"];\n";
        result += nodoTYPE + " -> " + nodoTYPE2 + ";\n";
        
        result += this.expression.createAST(tree, nodoEXP);

        return result;
    }

}
