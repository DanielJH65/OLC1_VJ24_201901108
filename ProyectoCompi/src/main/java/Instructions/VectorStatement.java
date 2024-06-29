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
public class VectorStatement extends Instruction {

    private boolean mutable;
    private String id;
    private LinkedList<Object> expression;
    LinkedList<Integer> dimensions;

    public VectorStatement(boolean mutable, String id, LinkedList<Object> expression, Type type, int line, int col) {
        super(type, line, col);
        this.mutable = mutable;
        this.id = id;
        this.expression = expression;
        this.dimensions = new LinkedList();
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        this.dimensions.add(this.expression.size());
        LinkedList<Object> list = new LinkedList();
        for (int i = 0; i < dimensions.getFirst(); i++) {
            var result = vectors(this.expression.get(i), i, 1, tree, table);
            if (result instanceof Errores) {
                return result;
            }
            list.add(result);
        }

        Symbol sym = new Symbol(this.getType(), this.id, list, this.mutable, this.getLine(), this.getCol());

        boolean isCreated = table.setVariable(sym);
        if (!isCreated) {
            return new Errores("Sintactico", "Identificador de variable existente", this.getLine(), this.getCol());
        }
        return null;
    }

    private Object vectors(Object exp, int i, int nivel, Tree tree, SymbolsTable table) {
        if (exp instanceof LinkedList exp2) {
            if (i == 0) {
                dimensions.add(exp2.size());
            }
            if (exp2.size() != dimensions.get(nivel)) {
                return new Errores("Semantico", "No pueden haber vectores de distintas dimensiones", this.getLine(), this.getCol());
            }
            LinkedList<Object> list = new LinkedList();
            for (int j = 0; j < exp2.size(); j++) {
                var result = vectors(exp2.get(j), j, nivel + 1, tree, table);
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
