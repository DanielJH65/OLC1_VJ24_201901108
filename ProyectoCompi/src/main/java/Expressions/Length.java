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
public class Length extends Instruction {

    private Object expression;

    public Length(Instruction expression, int line, int col) {
        super(new Type(TipoDato.INTEGER), line, col);
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        if (expression instanceof Instruction nativ) {
            if (nativ.getType().getType() == TipoDato.STRING) {
                return ((String) nativ.interpretar(tree, table)).length();
            }
        }
        var value = table.getVariable(((VarAcces) this.expression).getId());
        if (value == null) {
            return new Errores("Semantico", "El vector no existe", this.getLine(), this.getCol());
        }
        if (value.getValue() instanceof LinkedList list) {
            return list.size();
        } else {
            return new Errores("Semantico", "La variable no es un vector", this.getLine(), this.getCol());
        }
    }

}
