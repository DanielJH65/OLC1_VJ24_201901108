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
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class Match extends Instruction {

    private Instruction condition;
    private LinkedList<Case> cases;
    private Case def;

    public Match(Instruction condition, LinkedList<Case> cases, Case def, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.condition = condition;
        this.cases = cases;
        this.def = def;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var cond = this.condition.interpretar(tree, table);
        if (cond instanceof Errores) {
            return cond;
        }

        var newTable = new SymbolsTable(table);
        for (var case1 : this.cases) {
            var exp = case1.getExpression(tree, table);
            if (exp instanceof Errores) {
                return exp;
            }
            if(this.condition.getType().getType() != case1.getTypeExpression()){
                return new Errores("Semantico", "La expresion no es valida", case1.getLine(), case1.getCol());
            }
            if(exp == cond){
                case1.interpretar(tree, newTable);
                return null;
            }
        }
        if(this.def != null){
            this.def.interpretar(tree, newTable);
        }

        return null;
    }

}
