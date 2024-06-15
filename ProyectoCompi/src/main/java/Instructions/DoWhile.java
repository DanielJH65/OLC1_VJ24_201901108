package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Reportes.Simbolo;
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
public class DoWhile extends Instruction {

    private Instruction condition;
    private LinkedList<Instruction> instructions;

    public DoWhile(Instruction condition, LinkedList<Instruction> instructions, int line, int col) {
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

        var newTable = new SymbolsTable(table);

        boolean break1 = false;
        do {
            for (var ins : this.instructions) {
                if(ins instanceof Break){
                    break1 = true;
                }
                if(ins instanceof Continue){
                    break;
                }
                var result = ins.interpretar(tree, newTable);
                if (result instanceof Errores) {
                    tree.getErrores().add((Errores) result);
                }
                if(result instanceof Break){
                    break1 = true;
                }
                if(result instanceof Continue){
                    break;
                }
            }
            if(break1){
                break;
            }
        } while (Boolean.parseBoolean(this.condition.interpretar(tree, table).toString()));

        LinkedList<Simbolo> newList = newTable.getSimbolos();
        for (var sym : newList) {
            sym.setScope("Do-While " + this.getLine());
        }
        table.getSymbols().addAll(newTable.getSymbols());
        if (newList != null) {
            table.getSymbols().addAll(newList);
        }
        return null;
    }

}
