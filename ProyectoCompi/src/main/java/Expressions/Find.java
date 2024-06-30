/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expressions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class Find extends Instruction{
    private String id;
    private Instruction expression;

    public Find(String id, Instruction expression, int line, int col) {
        super(new Type(TipoDato.BOOLEAN), line, col);
        this.id = id;
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var value = table.getVariable(this.id);
        if (value == null) {
            return new Errores("Semantico", "El vector no existe", this.getLine(), this.getCol());
        }
        if (value.getValue() instanceof LinkedList list) {
            return list.indexOf(expression.interpretar(tree, table)) != -1;
        } else {
            return new Errores("Semantico", "La variable no es un vector", this.getLine(), this.getCol());
        }
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoFIND = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoDOT = "n" + tree.getContAST();
        String nodoRFIND = "n" + tree.getContAST();
        String nodoPA = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();
        String nodoPC = "n" + tree.getContAST();

        String result = nodoFIND + "[label=\"FIND\"];\n";
        result += previous + " -> " + nodoFIND + ";\n";

        result += nodoID + "[label=\""+this.id+"\"];\n";
        result += nodoDOT + "[label=\"\\.\"];\n";
        result += nodoRFIND + "[label=\"find\"];\n";
        result += nodoPA + "[label=\"(\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";
        result += nodoPC + "[label=\")\"];\n";
        
        result += nodoFIND + " -> " + nodoID + ";\n";
        result += nodoFIND + " -> " + nodoDOT + ";\n";
        result += nodoFIND + " -> " + nodoRFIND + ";\n";
        result += nodoFIND + " -> " + nodoPA + ";\n";
        result += nodoFIND + " -> " + nodoEXP + ";\n";
        result += nodoFIND + " -> " + nodoPC + ";\n";
        
        result += this.expression.createAST(tree, nodoEXP);

        return result;
    }

    
}
