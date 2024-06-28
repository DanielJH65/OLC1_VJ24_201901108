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

/**
 *
 * @author daniel
 */
public class Return extends Instruction{
    private Instruction value;
    private Object finalValue;

    public Return(Instruction value, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.value = value;
    }

    public Object getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Object finalValue) {
        this.finalValue = finalValue;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        if(value != null){
            var interValue = this.value.interpretar(tree, table);
            if(interValue instanceof Errores){
                return interValue;
            }
            this.getType().setType(this.value.getType().getType());
            this.finalValue = interValue;
            return this;
        }
        return this;
    }
    
}
