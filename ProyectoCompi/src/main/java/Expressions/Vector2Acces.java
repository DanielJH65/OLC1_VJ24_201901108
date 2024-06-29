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
public class Vector2Acces extends Instruction {

    private String id;
    private Instruction pos1;
    private Instruction pos2;

    public Vector2Acces(String id, Instruction pos1, Instruction pos2, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var value = table.getVariable(this.id);
        if (value == null) {
            return new Errores("Semantico", "El vector no existe", this.getLine(), this.getCol());
        }
        if (value.getValue() instanceof LinkedList list) {
            var posi1 = pos1.interpretar(tree, table);
            if (posi1 instanceof Errores) {
                return posi1;
            }
            if ((int) posi1 < 0 || (int) posi1 > list.size() - 1) {
                return new Errores("Semantico", "Posición de vector invalida", this.getLine(), this.getCol());
            }
            var posi2 = pos2.interpretar(tree, table);
            if (posi2 instanceof Errores) {
                return posi2;
            }
            this.getType().setType(value.getType().getType());
            var value2 = list.get((int) posi1);
            if (value2 instanceof LinkedList list2) {
                if ((int) posi2 < 0 || (int) posi2 > list2.size() - 1) {
                    return new Errores("Semantico", "Posición de vector invalida", this.getLine(), this.getCol());
                }
                return list2.get((int) posi2);
            } else {
                return new Errores("Semantico", "El vector no es de dos dimensiones", this.getLine(), this.getCol());
            }
        } else {
            return new Errores("Semantico", "La variable no es un vector", this.getLine(), this.getCol());
        }
    }

}
