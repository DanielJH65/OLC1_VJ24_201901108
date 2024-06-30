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

/**
 *
 * @author daniel
 */
public class VarAcces extends Instruction {

    private String id;

    public VarAcces(String id, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var value = table.getVariable(this.id);
        if (value == null) {
            return new Errores("Semantico", "La variable no existe", this.getLine(), this.getCol());
        }
        this.getType().setType(value.getType().getType());
        return value.getValue();
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoVACCES = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();

        String result = nodoVACCES + "[label=\"VARIABLE ACCES\"];\n";
        result += previous + " -> " + nodoVACCES + ";\n";
        
        result += nodoID + "[label=\""+this.id+"\"];\n";
        
        result += nodoVACCES + " -> " + nodoID + ";\n";

        return result;
    }
    
    

}
