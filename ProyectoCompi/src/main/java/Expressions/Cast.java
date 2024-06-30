/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expressions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.SymbolsTable;
import Symbol.Tree;
import Symbol.Type;

/**
 *
 * @author daniel
 */
public class Cast extends Instruction {

    private Instruction expression;

    public Cast(Instruction expression, Type type, int line, int col) {
        super(type, line, col);
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        Object op = expression.interpretar(tree, table);
        if (op instanceof Errores) {
            return op;
        }

        var type = this.expression.getType().getType();

        switch (this.getType().getType()) {
            case INTEGER -> {
                switch (type) {
                    case DOUBLE -> {
                        return (int) ((double) op);
                    }
                    case CHAR -> {
                        return (int) ((char) op);
                    }
                    default -> {
                        return new Errores("Semantico", "Casteo entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type) {
                    case INTEGER -> {
                        return (double) ((int) op);
                    }
                    case CHAR -> {
                        return (double) ((char) op);
                    }
                    default -> {
                        return new Errores("Semantico", "Casteo entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type) {
                    case INTEGER -> {
                        return (char) ((int) op);
                    }
                    default -> {
                        return new Errores("Semantico", "Casteo entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Casteo entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoCAST = "n" + tree.getContAST();
        String nodoPA = "n" + tree.getContAST();
        String nodoTYPE = "n" + tree.getContAST();
        String nodoTYPE2 = "n" + tree.getContAST();
        String nodoPC = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();

        String result = nodoCAST + "[label=\"CASTEO\"];\n";
        result += previous + " -> " + nodoCAST;

        result += nodoPA + "[label=\"(\"];\n";
        result += nodoTYPE + "[label=\"TYPE\"];\n";
        result += nodoPC + "[label=\")\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";
        result += nodoTYPE2 + "[label=\"" + this.getType().getType() + "\"];\n";
        
        result += nodoCAST + " -> " + nodoPA;
        result += nodoCAST + " -> " + nodoTYPE;
        result += nodoCAST + " -> " + nodoPC;
        result += nodoCAST + " -> " + nodoEXP;
        result += nodoTYPE + " -> " + nodoTYPE2;
        
        result += this.expression.createAST(tree, nodoEXP);

        return result;
    }

}
