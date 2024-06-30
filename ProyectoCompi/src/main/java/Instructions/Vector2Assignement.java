package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;
import java.util.LinkedList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author daniel
 */
public class Vector2Assignement extends Instruction {

    private String id;
    private Instruction pos;
    private Instruction pos2;
    private Instruction expression;

    public Vector2Assignement(String id, Instruction pos, Instruction pos2, Instruction expression, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
        this.pos = pos;
        this.pos2 = pos2;
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var value = table.getVariable(this.id);
        if (value == null) {
            return new Errores("Semantico", "El vector no existe", this.getLine(), this.getCol());
        }
        if (!value.isMutable()) {
            return new Errores("Semantico", "El vector no se puede modificar", this.getLine(), this.getCol());
        }

        var newValue = this.expression.interpretar(tree, table);
        if (newValue instanceof Errores) {
            return newValue;
        }
        if (this.expression.getType().getType() != value.getType().getType()) {
            return new Errores("Semantico", "El tipo del vector no coincide con el tipo de la expresion", this.getLine(), this.getCol());
        }
        var newpos = this.pos.interpretar(tree, table);
        if (newpos instanceof Errores) {
            return newpos;
        }
        var newpos2 = this.pos2.interpretar(tree, table);
        if (newpos2 instanceof Errores) {
            return newpos;
        }
        if (pos.getType().getType() != TipoDato.INTEGER) {
            return new Errores("Semantico", "La posicion debe ser un entero", this.getLine(), this.getCol());
        }
        if (value.getValue() instanceof LinkedList linkedList) {
            if ((int) newpos > linkedList.size() - 1 || (int) newpos < 0) {
                return new Errores("Semantico", "La posicion no es valida", this.getLine(), this.getCol());
            }
            var value2 = linkedList.get((int) newpos);
            if (value2 instanceof LinkedList linkedList2) {
                if ((int) newpos > linkedList.size() - 1 || (int) newpos < 0) {
                    return new Errores("Semantico", "La posicion no es valida", this.getLine(), this.getCol());
                }
                linkedList2.set((int) newpos2, newValue);
                return null;
            }
        }
        return new Errores("Semantico", "La variable no es un vector", this.getLine(), this.getCol());
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoVACCES = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoCORA = "n" + tree.getContAST();
        String nodoPOS = "n" + tree.getContAST();
        String nodoCORC = "n" + tree.getContAST();
        String nodoCORA2 = "n" + tree.getContAST();
        String nodoPOS2 = "n" + tree.getContAST();
        String nodoCORC2 = "n" + tree.getContAST();
        String nodoEQUAL = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();

        String result = nodoVACCES + "[label=\"VECTOR ACCES\"];\n";
        result += previous + " -> " + nodoVACCES + ";\n";

        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoCORA + "[label=\"\\[\"];\n";
        result += nodoPOS + "[label=\"EXPRESION\"];\n";
        result += nodoCORC + "[label=\"\\]\"];\n";
        result += nodoCORA2 + "[label=\"\\[\"];\n";
        result += nodoPOS2 + "[label=\"EXPRESION\"];\n";
        result += nodoCORC2 + "[label=\"\\]\"];\n";
        result += nodoEQUAL + "[label=\"=\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";

        result += nodoVACCES + " -> " + nodoID + ";\n";
        result += nodoVACCES + " -> " + nodoCORA + ";\n";
        result += nodoVACCES + " -> " + nodoPOS + ";\n";
        result += nodoVACCES + " -> " + nodoCORC + ";\n";
        result += nodoVACCES + " -> " + nodoCORA2 + ";\n";
        result += nodoVACCES + " -> " + nodoPOS2 + ";\n";
        result += nodoVACCES + " -> " + nodoCORC2 + ";\n";
        result += nodoVACCES + " -> " + nodoEQUAL + ";\n";
        result += nodoVACCES + " -> " + nodoEXP + ";\n";

        result += this.pos.createAST(tree, nodoPOS);
        result += this.pos2.createAST(tree, nodoPOS2);
        result += this.expression.createAST(tree, nodoEXP);

        return result;
    }

}
