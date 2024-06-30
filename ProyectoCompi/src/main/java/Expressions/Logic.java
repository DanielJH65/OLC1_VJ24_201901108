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
public class Logic extends Instruction {

    private Instruction op1;
    private Instruction op2;
    private LogicOperators operation;

    public Logic(Instruction op1, Instruction op2, LogicOperators operation, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.op1 = op1;
        this.op2 = op2;
        this.operation = operation;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        Object op1 = null;
        Object op2 = null;
        if (this.op1 == null) {
            op2 = this.op2.interpretar(tree, table);
            if (op2 instanceof Errores) {
                return op2;
            }
        } else {
            op1 = this.op1.interpretar(tree, table);
            if (op1 instanceof Errores) {
                return op1;
            }
            op2 = this.op2.interpretar(tree, table);
            if (op2 instanceof Errores) {
                return op2;
            }
        }

        return switch (this.operation) {
            case OR ->
                this.or(op1, op2);
            case AND ->
                this.and(op1, op2);
            case XOR ->
                this.xor(op1, op2);
            case NOT ->
                this.not(op2);
            default ->
                new Errores("Semantico", "Operador logico no valido", this.getCol(), this.getCol());
        };
    }

    public Object or(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case BOOLEAN -> {
                switch (type2) {
                    case BOOLEAN -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return Boolean.parseBoolean(op1.toString()) || Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("Semantico", "Or entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Or entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object and(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case BOOLEAN -> {
                switch (type2) {
                    case BOOLEAN -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return Boolean.parseBoolean(op1.toString()) && Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("Semantico", "And entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "And entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object xor(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case BOOLEAN -> {
                switch (type2) {
                    case BOOLEAN -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return Boolean.parseBoolean(op1.toString()) ^ Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("Semantico", "Xor entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Xor entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object not(Object op2) {
        var type2 = this.op2.getType().getType();

        switch (type2) {
            case BOOLEAN -> {
                this.getType().setType(TipoDato.BOOLEAN);
                return !Boolean.parseBoolean(op2.toString());
            }
            default -> {
                return new Errores("Semantico", "Not de tipo no valida", this.getLine(), this.getCol());
            }
        }
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String result = "";
        if (this.operation == LogicOperators.NOT) {
            String nodoExp = "n" + tree.getContAST();
            String nodoMin = "n" + tree.getContAST();

            result += nodoExp + "[label=\"EXPRESION\"];\n";
            result += nodoMin + "[label=\"!\"];\n";

            result += previous + " -> " + nodoExp + ";\n";
            result += nodoExp + " -> " + nodoMin + ";\n";
            result += this.op2.createAST(tree, nodoExp);
            return result;
        }
        String nodoExp1 = "n" + tree.getContAST();
        String nodoOp = "n" + tree.getContAST();
        String nodoExp2 = "n" + tree.getContAST();

        result += nodoExp2 + "[label=\"EXPRESION\"];\n";
        result += nodoOp + "[label=\"" + switch (this.operation) {
            case OR ->
                "||";
            case AND ->
                "&&";
            case XOR ->
                "^";
            default ->
                "";
        } + "\"];\n";
        result += nodoExp2 + "[label=\"EXPRESION\"];\n";

        result += previous + " -> " + nodoExp1 + ";\n";
        result += previous + " -> " + nodoOp + ";\n";
        result += previous + " -> " + nodoExp2 + ";\n";
        result += this.op1.createAST(tree, nodoExp1);
        result += this.op2.createAST(tree, nodoExp2);
        return result;
    }
    
}
