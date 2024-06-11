/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Expressions.Native;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;

/**
 *
 * @author daniel
 */
public class Variable extends Instruction{
    private String modifier;
    private String id;
    private Instruction expression;

    public Variable(String modifier, String id, Instruction expression, Type type, int line, int col) {
        super(type, line, col);
        this.modifier = modifier;
        this.id = id;
        this.expression = expression;
    }

    public Variable(String modifier, String id, Type type, int line, int col) {
        super(type, line, col);
        this.modifier = modifier;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
