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
public class For extends Instruction{
    private Instruction assignement;
    private Instruction condition;
    private Instruction increment;
    private LinkedList<Instruction> instructions;

    public For(Instruction assignement, Instruction condition, Instruction increment, LinkedList<Instruction> instructions, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.assignement = assignement;
        this.condition = condition;
        this.increment = increment;
        this.instructions = instructions;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var newTable = new SymbolsTable(table);
        
        var res1 = this.assignement.interpretar(tree, table);
        if(res1 instanceof Errores){
            return res1;
        }
        
        var cond = this.condition.interpretar(tree, table);
        if(cond instanceof Errores){
            return cond;
        }
        if(this.condition.getType().getType() != TipoDato.BOOLEAN){
            return new Errores("Semantico", "Expresion no valida, debe ser bool", this.condition.getLine(), this.condition.getCol());
        }
        
        boolean break1 = false;
        while(Boolean.parseBoolean(this.condition.interpretar(tree, table).toString())){
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
            var inc = this.increment.interpretar(tree, table);
            if(inc instanceof Errores){
                return inc;
            }
            if(break1){
                break;
            }
        }
        LinkedList<Simbolo> newList = newTable.getSimbolos();
        for(var sym : newList){
            sym.setScope("For " + this.getLine());
        }
        table.getSymbols().addAll(newTable.getSymbols());
        if (newList != null) {
            table.getSymbols().addAll(newList);
        }
        return null;
    }
    
    
}
