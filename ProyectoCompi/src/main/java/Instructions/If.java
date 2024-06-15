/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Reportes.Simbolo;
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
                if (ins instanceof Break || ins instanceof Continue) {
                    return ins;
                }
                var result = ins.interpretar(tree, newTable);
                if (result instanceof Errores) {
                    tree.getErrores().add((Errores) result);
                }
                if (result instanceof Break || ins instanceof Continue) {
                    return ins;
                }
            }
        } else {
            if (this.instructionsElse != null) {
                for (var ins : this.instructionsElse) {
                    if (ins instanceof Break || ins instanceof Continue) {
                        return ins;
                    }
                    var result = ins.interpretar(tree, newTable);
                    if (result instanceof Errores) {
                        tree.getErrores().add((Errores) result);
                    }
                    if (result instanceof Break || ins instanceof Continue) {
                        return ins;
                    }
                }
            }
        }
        LinkedList<Simbolo> newList = newTable.getSimbolos();
        for (var sym : newList) {
            sym.setScope("If " + this.getLine());
        }
        if (newList != null) {
            table.getSymbols().addAll(newList);
        }
        return null;
    }

}
