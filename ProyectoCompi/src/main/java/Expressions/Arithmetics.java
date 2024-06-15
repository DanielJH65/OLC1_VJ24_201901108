/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expressions;

import Abstract.Instruction;
import Exceptions.Errores;
import static Expressions.ArithmeticOperators.ADDITION;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import static Symbol.TipoDato.BOOLEAN;
import static Symbol.TipoDato.CHAR;
import static Symbol.TipoDato.DOUBLE;
import static Symbol.TipoDato.INTEGER;
import static Symbol.TipoDato.STRING;
import Symbol.Tree;
import Symbol.Type;

/**
 *
 * @author daniel
 */
public class Arithmetics extends Instruction {

    private Instruction op1;
    private Instruction op2;
    private ArithmeticOperators operation;

    public Arithmetics(Instruction op1, Instruction op2, ArithmeticOperators operation, int line, int col) {
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
            case ADDITION ->
                this.addition(op1, op2);
            case SUBTRACTION ->
                this.subtraction(op1, op2);
            case MULTIPLICATION ->
                this.multiplication(op1, op2);
            case DIVISION ->
                this.division(op1, op2);
            case POWER ->
                this.power(op1, op2);
            case MODULE ->
                this.module(op1, op2);
            case DENIAL ->
                this.denial(op2);
            default ->
                new Errores("Semantico", "Operador no valido", this.getCol(), this.getCol());
        };
    }

    public Object addition(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) op1 + (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) op1 + (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) op1 + (int) ((char) op2);
                    }
                    case STRING -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("Semantico", "Suma entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 + (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 + (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 + (int) ((char) op2);
                    }
                    case STRING -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("Semantico", "Suma entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case BOOLEAN -> {
                switch (type2) {
                    case STRING -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("Semantico", "Suma entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) ((char) op1) + (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) ((char) op1) + (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    case STRING -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("Semantico", "Suma entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case STRING -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    case STRING -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    case BOOLEAN -> {
                        this.getType().setType(TipoDato.STRING);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("Semantico", "Suma entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Suma entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object subtraction(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) op1 - (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) op1 - (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) op1 - (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Resta entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 - (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 - (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 - (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Resta entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) ((char) op1) - (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) ((char) op1) - (Double) op2;
                    }
                    default -> {
                        return new Errores("Semantico", "Resta entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Resta entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object multiplication(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) op1 * (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) op1 * (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) op1 * (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Multiplicación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 * (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 * (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 * (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Multiplicación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) ((char) op1) * (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) ((char) op1) * (Double) op2;
                    }
                    default -> {
                        return new Errores("Semantico", "Multiplicación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Multiplicación entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object division(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        if ((int) op2 == 0) {
                            return new Errores("Semantico", "No se puede dividir entre 0", this.getLine(), this.getCol());
                        }
                        this.getType().setType(TipoDato.DOUBLE);
                        return ((int) op1 + 0.0) / (int) op2;
                    }
                    case DOUBLE -> {
                        if ((double) op2 == 0.0) {
                            return new Errores("Semantico", "No se puede dividir entre 0", this.getLine(), this.getCol());
                        }
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) op1 / (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return ((int) op1 + 0.0) / (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Division entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        if ((int) op2 == 0) {
                            return new Errores("Semantico", "No se puede dividir entre 0", this.getLine(), this.getCol());
                        }
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 / (int) op2;
                    }
                    case DOUBLE -> {
                        if ((double) op2 == 0.0) {
                            return new Errores("Semantico", "No se puede dividir entre 0", this.getLine(), this.getCol());
                        }
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 / (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (Double) op1 / (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Division entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        if ((int) op2 == 0) {
                            return new Errores("Semantico", "No se puede dividir entre 0", this.getLine(), this.getCol());
                        }
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) ((char) op1) / ((int) op2 + 0.0);
                    }
                    case DOUBLE -> {
                        if ((double) op2 == 0.0) {
                            return new Errores("Semantico", "No se puede dividir entre 0", this.getLine(), this.getCol());
                        }
                        this.getType().setType(TipoDato.DOUBLE);
                        return (int) ((char) op1) / (Double) op2;
                    }
                    default -> {
                        return new Errores("Semantico", "División entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "División entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object power(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.INTEGER);
                        return (int) Math.pow((int) op1, (int) op2);
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return Math.pow((int) op1, (Double) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Potencia entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return Math.pow((Double) op1, (int) op2);
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return Math.pow((Double) op1, (Double) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Potencia entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Potencia entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object module(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (double) ((int) op1 % (int) op2);
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (double) ((int) op1 % (Double) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Módulo entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (double) ((Double) op1 % (int) op2);
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.DOUBLE);
                        return (double) ((Double) op1 % (Double) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Módulo entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Potencia entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object denial(Object op) {
        var type = this.op2.getType().getType();

        switch (type) {
            case INTEGER -> {
                this.getType().setType(TipoDato.INTEGER);
                return 0 - (int) op;
            }
            case DOUBLE -> {
                this.getType().setType(TipoDato.DOUBLE);
                return 0 - (Double) op;
            }
            default -> {
                return new Errores("Semantico", "Negación con tipo no valida", this.getLine(), this.getCol());
            }
        }
    }

}
