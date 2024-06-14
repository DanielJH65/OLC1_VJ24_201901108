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
public class If extends Instruction {

    private Instruction condition;
    private LinkedList<Instruction> instructions;
    private LinkedList<Instruction> instructionsElse;

    public If(Instruction condition, LinkedList<Instruction> instructions, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.condition = condition;
        this.instructions = instructions;
        this.instructionsElse = null;
    }

    public If(Instruction condition, LinkedList<Instruction> instructions, LinkedList<Instruction> instructionsElse, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.condition = condition;
        this.instructions = instructions;
        this.instructionsElse = instructionsElse;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var exp = this.condition.interpretar(tree, table);
        if (exp instanceof Errores) {
            return exp;
        }
        if (this.condition.getType().getType() != TipoDato.BOOLEAN) {
            return new Errores("Semantico", "La expresion no es valida", this.getLine(), this.getCol());
        }

        var newTable = new SymbolsTable(table);
        if (Boolean.parseBoolean(exp.toString())) {
            for (var ins : this.instructions) {
                var result = ins.interpretar(tree, newTable);
                if (result instanceof Errores) {
                    tree.getErrores().add((Errores) result);
                }
            }
        } else {
            if (this.instructionsElse != null) {
                for (var ins : this.instructionsElse) {
                    var result = ins.interpretar(tree, newTable);
                    if (result instanceof Errores) {
                        tree.getErrores().add((Errores) result);
                    }
                }
            }
        }
        return null;
    }

}
