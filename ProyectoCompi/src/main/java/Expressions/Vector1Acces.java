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
public class Vector1Acces extends Instruction {

    private String id;
    private Instruction pos;

    public Vector1Acces(String id, Instruction pos, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.pos = pos;
        this.id = id;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var value = table.getVariable(this.id);
        if (value == null) {
            return new Errores("Semantico", "El vector no existe", this.getLine(), this.getCol());
        }
        if (value.getValue() instanceof LinkedList list) {
            var posi = pos.interpretar(tree, table);
            if (posi instanceof Errores) {
                return posi;
            }
            if ((int) posi < 0 || (int) posi > list.size() - 1) {
                return new Errores("Semantico", "Posición de vector invalida", this.getLine(), this.getCol());
            }
            this.getType().setType(value.getType().getType());
            return list.get((int) posi);
        } else {
            return new Errores("Semantico", "La variable no es un vector", this.getLine(), this.getCol());
        }
    }

    public String getId() {
        return id;
    }
    

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoVACCES = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoCORA = "n" + tree.getContAST();
        String nodoPOS = "n" + tree.getContAST();
        String nodoCORC = "n" + tree.getContAST();

        String result = nodoVACCES + "[label=\"VECTOR ACCES\"];\n";
        result += previous + " -> " + nodoVACCES + ";\n";

        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoCORA + "[label=\"\\[\"];\n";
        result += nodoPOS + "[label=\"EXPRESION\"];\n";
        result += nodoCORC + "[label=\"\\]\"];\n";

        result += nodoVACCES + " -> " + nodoID + ";\n";
        result += nodoVACCES + " -> " + nodoCORA + ";\n";
        result += nodoVACCES + " -> " + nodoPOS + ";\n";
        result += nodoVACCES + " -> " + nodoCORC + ";\n";

        result += this.pos.createAST(tree, nodoPOS);

        return result;
    }

}
