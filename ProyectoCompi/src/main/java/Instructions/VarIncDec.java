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
public class VarIncDec extends Instruction{
    private String id;
    private boolean inc;

    public VarIncDec(String id, boolean inc, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
        this.inc = inc;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var value = table.getVariable(this.id);
        if (value == null) {
            return new Errores("Semantico", "La variable no existe", this.getLine(), this.getCol());
        }
        if (!value.isMutable()) {
            return new Errores("Semantico", "La variable no se puede modificar", this.getLine(), this.getCol());
        }

        if (value.getType().getType() != TipoDato.INTEGER && value.getType().getType() != TipoDato.DOUBLE) {
            return new Errores("Semantico", "El tipo de la variable no permite incremento o decremeto", this.getLine(), this.getCol());
        }
        var newValue = value.getValue();
        if (value.getType().getType() == TipoDato.INTEGER){
            if(inc){
                newValue = ((int) newValue) + 1;
            }else{
                newValue = ((int) newValue) - 1;
            }
        }else if (value.getType().getType() == TipoDato.DOUBLE){
            if(inc){
                newValue = ((double) newValue) + 1.0;
            }else{
                newValue = ((double) newValue) - 1.0;
            }
        }
        
        value.setValue(newValue);
        return null;
    }
    
    
    
}
