/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expressions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.Symbol;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class ToString extends Instruction {
    private Object expression;

    public ToString(Object expression, int line, int col) {
        super(new Type(TipoDato.STRING), line, col);
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        if (expression instanceof Instruction nativ) {
            nativ.interpretar(tree, table);
            if ((nativ.getType().getType() != TipoDato.STRING && nativ.getType().getType() != TipoDato.STRUCT)) {
                return nativ.interpretar(tree, table).toString();
            }
        }
        Symbol value = null;
        if(this.expression instanceof VarAcces){
            value = table.getVariable(((VarAcces)this.expression).getId());
        }
        if(this.expression instanceof Vector1Acces){
            value = table.getVariable(((Vector1Acces)this.expression).getId());
        }
        if(this.expression instanceof Vector2Acces){
            value = table.getVariable(((Vector2Acces)this.expression).getId());
        }
        if (value == null) {
            return new Errores("Semantico", "El struct no existe", this.getLine(), this.getCol());
        }
        if (value.getValue() instanceof LinkedList list && value.getType().getType() == TipoDato.STRUCT) {
            String result = value.getId() + " { ";
            for (var field : list) {
                var hash = (HashMap) field;
                result += hash.get("id") + ": " + hash.get("value") + ", ";
            }
            result += "}";
            return result;
        } else {
            return new Errores("Semantico", "La variable no es un struct, numerico, char o booleanp", this.getLine(), this.getCol());
        }
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoTOSTRING = "n" + tree.getContAST();
        String nodoRR = "n" + tree.getContAST();
        String nodoPA = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();
        String nodoPC = "n" + tree.getContAST();

        String result = nodoTOSTRING + "[label=\"TOSTRING\"];\n";
        result += previous + " -> " + nodoTOSTRING + ";\n";
        
        result += nodoRR + "[label=\"toString\"];\n";
        result += nodoPA + "[label=\"(\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";
        result += nodoPC + "[label=\")\"];\n";
        
        result += nodoTOSTRING + " -> " + nodoRR + ";\n";
        result += nodoTOSTRING + " -> " + nodoPA + ";\n";
        result += nodoTOSTRING + " -> " + nodoEXP + ";\n";
        result += nodoTOSTRING + " -> " + nodoPC + ";\n";
        
        result += ((Instruction) this.expression).createAST(tree, nodoEXP);

        return result;
    }
    
    
}
