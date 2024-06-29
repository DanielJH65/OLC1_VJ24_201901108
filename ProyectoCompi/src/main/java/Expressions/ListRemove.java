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
        if (value.getValue() instanceof LinkedList list) {
            var posi = pos.interpretar(tree, table);
            if (posi instanceof Errores) {
                return posi;
            }
            if ((int) posi < 0 || (int) posi > list.size() - 1) {
                return new Errores("Semantico", "Posición de lista invalida", this.getLine(), this.getCol());
            }
            this.getType().setType(value.getType().getType());
            return list.remove((int) posi);
        } else {
            return new Errores("Semantico", "La variable no es una lista", this.getLine(), this.getCol());
        }
    }

}
