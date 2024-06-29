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
public class Round extends Instruction {

    private Instruction expression;

    public Round(Instruction expression, int line, int col) {
        super(new Type(TipoDato.INTEGER), line, col);
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var op = this.expression.interpretar(tree, table);
        if (op instanceof Errores) {
            return op;
        }

        if (this.expression.getType().getType() != TipoDato.DOUBLE) {
            new Errores("Semantico", "La expresion debe ser un número double", this.getCol(), this.getCol());
        }

        return (int) Math.round((double) op);
    }

}
