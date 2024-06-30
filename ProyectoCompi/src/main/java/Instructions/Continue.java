/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;

/**
 *
 * @author daniel
 */
public class Continue extends Instruction{
    public Continue(int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoBR = "n" + tree.getContAST();
        String nodoRBR = "n" + tree.getContAST();
        
        String result = nodoBR + "[label=\"CONTINUE\"];\n";
        result += previous + " -> " + nodoBR + ";\n";
        
        result += nodoRBR + "[label=\"continue\"];\n";
        result += nodoBR + " -> " + nodoRBR + ";\n";
        return result;
    }
    
    
}
