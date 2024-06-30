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
public class ListRemove extends Instruction {

    private String id;
    private Instruction pos;

    public ListRemove(String id, Instruction pos, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
        this.pos = pos;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var value = table.getVariable(this.id);
        if (value == null) {
            return new Errores("Semantico", "La lista no existe", this.getLine(), this.getCol());
        }
        var posi = this.pos.interpretar(tree, table);
        if (posi instanceof Errores) {
            return posi;
        }
        if (value.getValue() instanceof LinkedList list) {
            if ((int) posi < 0 || (int) posi > list.size() - 1) {
                return new Errores("Semantico", "Posición de lista invalida", this.getLine(), this.getCol());
            }
            this.getType().setType(value.getType().getType());
            return list.remove((int) posi);
        } else {
            return new Errores("Semantico", "La variable no es una lista", this.getLine(), this.getCol());
        }
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoLRM = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoDOT = "n" + tree.getContAST();
        String nodoRR = "n" + tree.getContAST();
        String nodoPA = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();
        String nodoPC = "n" + tree.getContAST();

        String result = nodoLRM + "[label=\"REMOVE\"];\n";
        result += previous + " -> " + nodoLRM + ";\n";

        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoDOT + "[label=\"\\.\"];\n";
        result += nodoRR + "[label=\"remove\"];\n";
        result += nodoPA + "[label=\"(\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";
        result += nodoPC + "[label=\")\"];\n";

        result += nodoLRM + " -> " + nodoID + ";\n";
        result += nodoLRM + " -> " + nodoDOT + ";\n";
        result += nodoLRM + " -> " + nodoRR + ";\n";
        result += nodoLRM + " -> " + nodoPA + ";\n";
        result += nodoLRM + " -> " + nodoEXP + ";\n";
        result += nodoLRM + " -> " + nodoPC + ";\n";

        result += this.pos.createAST(tree, nodoEXP);

        return result;
    }

}
