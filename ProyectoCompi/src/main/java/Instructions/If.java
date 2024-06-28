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

        Break break1 = new Break(0, 0);
        Continue continue1 = new Continue(0, 0);
        Return return1 = null;
        boolean isBreak = false;
        boolean isContinue = false;
        boolean isReturn = false;
        if (Boolean.parseBoolean(exp.toString())) {
            for (var ins : this.instructions) {
                if (ins instanceof Break) {
                    break1 = (Break) ins;
                    isBreak = true;
                }
                if (ins instanceof Continue) {
                    continue1 = (Continue) ins;
                    isContinue = true;
                }
                var result = ins.interpretar(tree, newTable);
                if (result instanceof Errores) {
                    tree.getErrores().add((Errores) result);
                }
                if (result instanceof Break) {
                    break1 = (Break) result;
                    isBreak = true;
                }
                if (result instanceof Continue) {
                    continue1 = (Continue) result;
                    isContinue = true;
                }
                if (result instanceof Return aReturn1) {
                    isReturn = true;
                    return1 = aReturn1;
                }
            }
        } else {
            if (this.instructionsElse != null) {
                for (var ins : this.instructionsElse) {
                    if (ins instanceof Break) {
                        break1 = (Break) ins;
                        isBreak = true;
                    }
                    if (ins instanceof Continue) {
                        continue1 = (Continue) ins;
                        isContinue = true;
                    }
                    var result = ins.interpretar(tree, newTable);
                    if (result instanceof Errores) {
                        tree.getErrores().add((Errores) result);
                    }
                    if (result instanceof Break) {
                        break1 = (Break) result;
                        isBreak = true;
                    }
                    if (result instanceof Continue) {
                        continue1 = (Continue) result;
                        isContinue = true;
                    }
                    if (result instanceof Return aReturn1) {
                        isReturn = true;
                        return1 = aReturn1;
                    }
                }
            }
        }
        LinkedList<Simbolo> newList = newTable.getSimbolos();
        for (var sym : newList) {
            sym.setScope("If " + this.getLine());
        }
        table.getSymbols().addAll(newTable.getSymbols());
        if (newList != null) {
            table.getSymbols().addAll(newList);
        }
        if (isBreak) {
            return break1;
        }
        if (isContinue) {
            return continue1;
        }
        if (isReturn) {
            return return1;
        }
        return null;
    }

}
