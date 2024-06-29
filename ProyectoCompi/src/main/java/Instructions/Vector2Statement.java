/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.Symbol;
import Symbol.SymbolsTable;
import Symbol.Tree;
import Symbol.Type;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class Vector2Statement  extends Instruction {

    private boolean mutable;
    private String id;
    private LinkedList<LinkedList> expression;
    LinkedList<Integer> dimensions;

    public Vector2Statement(boolean mutable, String id, LinkedList<LinkedList> expression, Type type, int line, int col) {
        super(type, line, col);
        this.mutable = mutable;
        this.id = id;
        this.expression = expression;
        this.dimensions = new LinkedList<>();
    }
    
    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        this.dimensions.add(this.expression.size());
        LinkedList<LinkedList> list = new LinkedList();
        for (int i = 0; i < dimensions.getFirst(); i++) {
            var result = vectors(this.expression.get(i), i, 1, tree, table);
            list.add(result);
        }

        Symbol sym = new Symbol(this.getType(), this.id, list, this.mutable, this.getLine(), this.getCol());

        boolean isCreated = table.setVariable(sym);
        if (!isCreated) {
            return new Errores("Sintactico", "Identificador de variable existente", this.getLine(), this.getCol());
        }
        return null;
    }

    private LinkedList vectors(Object exp, int i, int nivel, Tree tree, SymbolsTable table) {
        if (exp instanceof LinkedList exp2) {
            if (i == 0) {
                dimensions.add(exp2.size());
            }
            LinkedList<Object> list = new LinkedList();
            for (int j = 0; j < exp2.size(); j++) {
                var result = vectors2(exp2.get(j), j, nivel + 1, tree, table);
                list.add(result);
            }
            return list;
        }
        return null;
    }
    
    private Object vectors2(Object exp, int i, int nivel, Tree tree, SymbolsTable table) {
        if (exp instanceof LinkedList exp2) {
            LinkedList<Object> list = new LinkedList();
            for (int j = 0; j < exp2.size(); j++) {
                var result = vectors2(exp2.get(j), j, nivel, tree, table);
                if (result instanceof Errores) {
                    return result;
                }
                list.add(result);
            }
        } else if (exp instanceof Instruction ins) {
            var result = ins.interpretar(tree, table);
            if (result instanceof Errores) {
                return result;
            }
            return result;
        } else {
            return new Errores("Semantico", "Valor no valido dentro del vector", this.getLine(), this.getCol());
        }
        return null;
    }
    
}
