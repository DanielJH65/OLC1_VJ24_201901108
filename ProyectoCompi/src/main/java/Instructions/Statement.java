/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Expressions.Native;
import Symbol.Symbol;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;

/**
 *
 * @author daniel
 */
public class Statement extends Instruction{
    private boolean mutable;
    private String id;
    private Instruction expression;

    public Statement(boolean mutable, String id, Instruction expression, Type type, int line, int col) {
        super(type, line, col);
        this.mutable = mutable;
        this.id = id;
        this.expression = expression;
    }

    public Statement(boolean mutable, String id, Type type, int line, int col) {
        super(type, line, col);
        this.mutable = mutable;
        this.id = id;
        switch (type.getType()){
            case INTEGER ->
                this.expression = new Native(0, new Type(TipoDato.INTEGER), line, col);
            case DOUBLE ->
                this.expression = new Native(0.0, new Type(TipoDato.DOUBLE), line, col);
            case STRING ->
                this.expression = new Native("", new Type(TipoDato.STRING), line, col);
            case CHAR ->
                this.expression = new Native('\u0000', new Type(TipoDato.CHAR), line, col);
            case BOOLEAN ->
                this.expression = new Native(true, new Type(TipoDato.BOOLEAN), line, col);
        }
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var expression = this.expression.interpretar(tree, table);
        
        if(expression instanceof Errores){
            return expression;
        }
        
        if(this.expression.getType().getType() != this.getType().getType()){
            return new Errores("Sintactico", "Variable de distinto tipo", this.getLine(), this.getCol());
        }
        
        Symbol sym = new Symbol(this.getType(), this.id, expression, this.mutable, this.getLine(), this.getCol());
        
        boolean created = table.setVariable(sym);
        
        if(!created){
            return new Errores("Sintactico", "Identificador de variable existente", this.getLine(), this.getCol());
        }
        
        return null;
    }
    
    
}
