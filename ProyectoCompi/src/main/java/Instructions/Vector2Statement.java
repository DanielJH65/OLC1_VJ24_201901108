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
public class Vector2Statement extends Instruction {

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

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoLA = "n" + tree.getContAST();
        String nodoMUT = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoDOSP = "n" + tree.getContAST();
        String nodoTYPE = "n" + tree.getContAST();
        String nodoCORA = "n" + tree.getContAST();
        String nodoCORC = "n" + tree.getContAST();
        String nodoEQUAL = "n" + tree.getContAST();
        String nodoCORA2 = "n" + tree.getContAST();

        String nodoMUT2 = "n" + tree.getContAST();
        String nodoTYPE2 = "n" + tree.getContAST();

        String result = nodoLA + "[label=\"VECTOR STATEMENT\"];\n";
        result += previous + " -> " + nodoLA + ";\n";

        result += nodoMUT + "[label=\"MUTABLE\"];\n";
        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoDOSP + "[label=\":\"];\n";
        result += nodoTYPE + "[label=\"TYPE\"];\n";
        result += nodoCORA + "[label=\"\\[\"];\n";
        result += nodoCORC + "[label=\"\\]\"];\n";
        result += nodoEQUAL + "[label=\"=\"];\n";
        result += nodoCORA2 + "[label=\"\\[\"];\n";
        result += nodoLA + " -> " + nodoMUT + ";\n";
        result += nodoLA + " -> " + nodoID + ";\n";
        result += nodoLA + " -> " + nodoDOSP + ";\n";
        result += nodoLA + " -> " + nodoTYPE + ";\n";
        result += nodoLA + " -> " + nodoCORA + ";\n";
        result += nodoLA + " -> " + nodoCORC + ";\n";
        result += nodoLA + " -> " + nodoEQUAL + ";\n";
        result += nodoLA + " -> " + nodoCORA2 + ";\n";

        result += nodoMUT2 + "[label=\"" + (this.mutable ? "var" : "const") + "\"];\n";
        result += nodoMUT + " -> " + nodoMUT2 + ";\n";

        result += nodoTYPE2 + "[label=\"" + this.getType().getType() + "\"];\n";
        result += nodoTYPE + " -> " + nodoTYPE2 + ";\n";

        for (var param : this.expression) {
            String nodoPARA = "n" + tree.getContAST();
            result += nodoPARA + "[label=\"\\[\"];\n";
            result += nodoLA + " -> " + nodoPARA + ";\n";
            for (var ins : param) {
                String nodoEXP = "n" + tree.getContAST();
                String nodoCOMA = "n" + tree.getContAST();
                result += nodoEXP + "[label = \"EXPRESION\"];\n";
                result += nodoCOMA + "[label = \",\"];\n";
                result += nodoLA + " -> " + nodoEXP + ";\n";
                result += nodoLA + " -> " + nodoCOMA + ";\n";

                result += ((Instruction) ins).createAST(tree, nodoEXP);
            }
            String nodoPARC = "n" + tree.getContAST();
            result += nodoPARC + "[label=\"\\]\"];\n";
            result += nodoLA + " -> " + nodoPARC + ";\n";
        }

        String nodoPARC = "n" + tree.getContAST();
        result += nodoPARC + "[label=\"\\]\"];\n";
        result += nodoLA + " -> " + nodoPARC + ";\n";

        return result;
    }
}
