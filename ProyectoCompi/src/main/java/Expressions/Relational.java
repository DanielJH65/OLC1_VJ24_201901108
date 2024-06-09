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
public class Relational extends Instruction {

    private Instruction op1;
    private Instruction op2;
    private RelationalOperators operation;

    public Relational(Instruction op1, Instruction op2, RelationalOperators operation, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.op1 = op1;
        this.op2 = op2;
        this.operation = operation;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        Object op1 = null;
        Object op2 = null;

        op1 = this.op1.interpretar(tree, table);
        if (op1 instanceof Errores) {
            return op1;
        }
        op2 = this.op2.interpretar(tree, table);
        if (op2 instanceof Errores) {
            return op2;
        }

        return switch (this.operation) {
            case EQUAL ->
                this.equal(op1, op2);
            case DIFFERENT ->
                this.different(op1, op2);
            case SMALLER ->
                this.smaller(op1, op2);
            case SMALLEREQUAL ->
                this.smallerEqual(op1, op2);
            case GREATER ->
                this.greater(op1, op2);
            case GREATEREQUAL ->
                this.greaterEqual(op1, op2);
            default ->
                new Errores("Semantico", "Operador relacional no valido", this.getCol(), this.getCol());
        };
    }

    public Object equal(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 == (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 == (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 == (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Igualación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 == (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (double) op1 == (double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 == (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Igualación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) == (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) == (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) == (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Igualación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case BOOLEAN -> {
                switch (type2) {
                    case BOOLEAN -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return Boolean.parseBoolean(op1.toString()) == Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("Semantico", "Igualación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case STRING -> {
                switch (type2) {
                    case STRING -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return op1.toString().equals(op2.toString());
                    }
                    default -> {
                        return new Errores("Semantico", "Igualación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Igualación entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object different(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 != (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 != (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 != (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Diferenciación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 != (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 != (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 != (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Diferenciación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) != (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) != (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) != (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Diferenciación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case BOOLEAN -> {
                switch (type2) {
                    case BOOLEAN -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return Boolean.parseBoolean(op1.toString()) != Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("Semantico", "Diferenciación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case STRING -> {
                switch (type2) {
                    case STRING -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return !op1.toString().equals(op2.toString());
                    }
                    default -> {
                        return new Errores("Semantico", "Diferenciación entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Diferenciación entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object smaller(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 < (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 < (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 < (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Menor que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 < (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 < (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 < (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Menor que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) < (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) < (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) < (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Menor que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Menor que entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object smallerEqual(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 <= (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 <= (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 <= (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Menor o igual que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 <= (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 <= (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 <= (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Menor o igual que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) <= (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) <= (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) <= (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Menor o igual que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Menor o igual que entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object greater(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 > (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 > (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 > (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Mayor que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 > (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 > (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 > (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Mayor que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) > (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) > (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) > (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Mayor que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Mayor que entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }

    public Object greaterEqual(Object op1, Object op2) {
        var type1 = this.op1.getType().getType();
        var type2 = this.op2.getType().getType();

        switch (type1) {
            case INTEGER -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 >= (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 >= (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) op1 >= (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Mayor o igual que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case DOUBLE -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 >= (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 >= (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (Double) op1 >= (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "MAyor o igual que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            case CHAR -> {
                switch (type2) {
                    case INTEGER -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) >= (int) op2;
                    }
                    case DOUBLE -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) >= (Double) op2;
                    }
                    case CHAR -> {
                        this.getType().setType(TipoDato.BOOLEAN);
                        return (int) ((char) op1) >= (int) ((char) op2);
                    }
                    default -> {
                        return new Errores("Semantico", "Mayor o igual que entre tipos no valida", this.getLine(), this.getCol());
                    }
                }
            }
            default -> {
                return new Errores("Semantico", "Mayor o igual que entre tipos no valida", this.getLine(), this.getCol());
            }
        }
    }
}
