/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;

/**
 *
 * @author daniel
 */
public class VarAssignement extends Instruction {

    private String id;
    Instruction expression;

    public VarAssignement(String id, Instruction expression, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var value = table.getVariable(this.id);
        if (value == null) {
            return new Errores("Semantico", "La variable no existe", this.getLine(), this.getCol());
        }
        if (!value.isMutable()) {
            return new Errores("Semantico", "La variable no se puede modificar", this.getLine(), this.getCol());
        }

        var newValue = this.expression.interpretar(tree, table);
        if (newValue instanceof Errores) {
            return newValue;
        }
        if (this.expression.getType().getType() != value.getType().getType()) {
            return new Errores("Semantico", "El tipo de la variable no coincide con el tipo de la expresion", this.getLine(), this.getCol());
        }

        value.setValue(newValue);
        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoLA = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoDOT = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();

        String result = nodoLA + "[label=\"VARIABLE ASSIGNEMENT\"];\n";
        result += previous + " -> " + nodoLA + ";\n";

        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoDOT + "[label=\"=\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";
        result += nodoLA + " -> " + nodoID + ";\n";
        result += nodoLA + " -> " + nodoDOT + ";\n";
        result += nodoLA + " -> " + nodoEXP + ";\n";
        
        result += this.expression.createAST(tree, nodoEXP);
        return result;
    }

}
