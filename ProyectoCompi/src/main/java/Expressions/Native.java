/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expressions;

import Abstract.Instruction;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;

/**
 *
 * @author daniel
 */
public class Native extends Instruction {

    private Object value;

    public Native(Object value, Type type, int line, int col) {
        super(type, line, col);
        this.value = value;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        return this.value;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoNATIVE = "n" + tree.getContAST();
        String nodoVAL = "n" + tree.getContAST();

        String result = nodoNATIVE + "[label=\"NATIVO\"];\n";
        result += previous + " -> " + nodoNATIVE + ";\n";

        result += nodoVAL + "[label=\"" + this.value.toString() + "\"];\n";
        result += nodoNATIVE + " -> " + nodoVAL + ";\n";

        return result;
    }

}
