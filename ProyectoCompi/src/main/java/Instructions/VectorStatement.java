/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.SymbolsTable;
import Symbol.Tree;
import Symbol.Type;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class VectorStatement extends Instruction {

    private boolean mutable;
    private String id;
    private LinkedList<Object> expression;

    public VectorStatement(boolean mutable, String id, LinkedList<Object> expression, Type type, int line, int col) {
        super(type, line, col);
        this.mutable = mutable;
        this.id = id;
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        LinkedList<Integer> dimensions = new LinkedList();
        dimensions.add(this.expression.size());
        for (int i = 0; i < dimensions.getFirst(); i++) {

        }
//        for (var exp : this.expression){
//            if(exp instanceof LinkedList linkedList){
//                for (var exp2 : linkedList){
//                    if(exp2 instanceof Instruction ins){
//                        var expression = ins.interpretar(tree, table);
//                        if(expression instanceof Errores){
//                            return expression;
//                        }
//                    }
//                }
//            }
//        }
        return null;
    }
}
