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
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class StructAcces extends Instruction {

    private String id;
    private String field;

    public StructAcces(String id, String field, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
        this.field = field;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var struct = table.getVariable(this.id);
        if (struct == null) {
            return new Errores("Semantico", "El struct no existe", this.getLine(), this.getCol());
        }
        if (struct.getValue() instanceof LinkedList list) {
            for (var field : list) {
                var hash = (HashMap) field;
                if (((String) (hash.get("id"))).equalsIgnoreCase(this.field)){
                    this.setType((Type) hash.get("type"));
                    return hash.get("value");
                }
            }
            return new Errores("Semantico", "No hay un campo con ese nombre en el struct", this.getLine(), this.getCol());
        } else {
            return new Errores("Semantico", "La variable no es un struct", this.getLine(), this.getCol());
        }
    }

}
