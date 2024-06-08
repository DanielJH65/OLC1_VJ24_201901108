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
public class Print extends Instruction{
    private Instruction expression;

    public Print(Instruction expression, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.expression = expression;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var result = this.expression.interpretar(tree, table);
        if (result instanceof Errores){
            return result;
        }
        tree.print(result.toString());
        return null;
    }
    
    
}
