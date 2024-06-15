/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

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
public class Case extends Instruction {

    private Instruction expression;
    private LinkedList<Instruction> instrucciones;

    public Case(Instruction expression, LinkedList<Instruction> instrucciones, int line, int col) {
        super(new Type(TipoDato.BOOLEAN), line, col);
        this.expression = expression;
        this.instrucciones = instrucciones;
    }

    public Object getExpression(Tree tree, SymbolsTable table) {
        return this.expression.interpretar(tree, table);
    }
    
    public TipoDato getTypeExpression(){
        return this.expression.getType().getType();
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        for (var ins : this.instrucciones) {
            var result = ins.interpretar(tree, table);
            if (result instanceof Errores) {
                tree.getErrores().add((Errores) result);
            }
        }
        return null;
    }

}
