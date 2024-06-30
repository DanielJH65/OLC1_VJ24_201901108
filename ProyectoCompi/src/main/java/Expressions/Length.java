/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expressions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.Symbol;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class Length extends Instruction {

    private Instruction expression;

    public Length(Instruction expression, int line, int col) {
        super(new Type(TipoDato.INTEGER), line, col);
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        this.expression.interpretar(tree, table);
        if (expression.getType().getType() == TipoDato.STRING) {
            return ((String) expression.interpretar(tree, table)).length();
        }
        Symbol value = null;
        if(this.expression instanceof VarAcces){
            value = table.getVariable(((VarAcces)this.expression).getId());
        }
        if(this.expression instanceof Vector1Acces){
            value = table.getVariable(((Vector1Acces)this.expression).getId());
        }
        if(this.expression instanceof Vector2Acces){
            value = table.getVariable(((Vector2Acces)this.expression).getId());
        }
        if (value == null) {
            return new Errores("Semantico", "El vector no existe", this.getLine(), this.getCol());
        }
        if (value.getValue() instanceof LinkedList list) {
            return list.size();
        } else {
            return new Errores("Semantico", "La variable no es un vector", this.getLine(), this.getCol());
        }
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoLENGTH = "n" + tree.getContAST();
        String nodoRL = "n" + tree.getContAST();
        String nodoPA = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();
        String nodoPC = "n" + tree.getContAST();

        String result = nodoLENGTH + "[label=\"LENGTH\"];\n";
        result += previous + " -> " + nodoLENGTH + ";\n";

        result += nodoRL + "[label=\"length\"];\n";
        result += nodoPA + "[label=\"(\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";
        result += nodoPC + "[label=\")\"];\n";

        result += nodoLENGTH + " -> " + nodoRL + ";\n";
        result += nodoLENGTH + " -> " + nodoPA + ";\n";
        result += nodoLENGTH + " -> " + nodoEXP + ";\n";
        result += nodoLENGTH + " -> " + nodoPC + ";\n";

        result += ((Instruction) this.expression).createAST(tree, nodoEXP);

        return result;
    }

}
