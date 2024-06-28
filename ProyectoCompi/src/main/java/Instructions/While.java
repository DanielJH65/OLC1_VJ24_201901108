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
public class While extends Instruction {

    private Instruction condition;
    private LinkedList<Instruction> instructions;

    public While(Instruction condition, LinkedList<Instruction> instructions, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.condition = condition;
        this.instructions = instructions;
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

        boolean break1 = false;
        boolean return1 = false;
        Return aReturn = null;
        while (Boolean.parseBoolean(this.condition.interpretar(tree, table).toString())) {
            var newTable = new SymbolsTable(table);
            for (var ins : this.instructions) {
                if (ins instanceof Break) {
                    break1 = true;
                    break;
                }
                if (ins instanceof Continue) {
                    break;
                }
                var result = ins.interpretar(tree, newTable);
                if (result instanceof Errores errores) {
                    tree.getErrores().add(errores);
                }
                if (result instanceof Break) {
                    break1 = true;
                    break;
                }
                if (result instanceof Continue) {
                    break;
                }
                if(result instanceof Return aReturn1){
                    return1 = true;
                    aReturn = aReturn1;
                }
            }
            if (break1) {
                break;
            }
            if (return1){
                return aReturn;
            }
            LinkedList<Simbolo> newList = newTable.getSimbolos();
            for (var sym : newList) {
                sym.setScope("While " + this.getLine());
            }
            table.getSymbols().addAll(newTable.getSymbols());
            if (newList != null) {
                table.getSymbols().addAll(newList);
            }
        }

        return null;
    }

}
