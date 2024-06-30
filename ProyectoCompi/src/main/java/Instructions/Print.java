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
        String result2 = result.toString().replace("\\\"", "\"");
        result2 = result2.replace("\\\'", "\'");
        result2 = result2.replace("\\t", "\t");
        result2 = result2.replace("\\n", "\n");
        result2 = result2.replace("\\\\", "\\");
        tree.print(result2);
        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoROUND = "n" + tree.getContAST();
        String nodoRR = "n" + tree.getContAST();
        String nodoPA = "n" + tree.getContAST();
        String nodoEXP = "n" + tree.getContAST();
        String nodoPC = "n" + tree.getContAST();

        String result = nodoROUND + "[label=\"PRINT\"];\n";
        result += previous + " -> " + nodoROUND + ";\n";
        
        result += nodoRR + "[label=\"println\"];\n";
        result += nodoPA + "[label=\"(\"];\n";
        result += nodoEXP + "[label=\"EXPRESION\"];\n";
        result += nodoPC + "[label=\")\"];\n";
        
        result += nodoROUND + " -> " + nodoRR + ";\n";
        result += nodoROUND + " -> " + nodoPA + ";\n";
        result += nodoROUND + " -> " + nodoEXP + ";\n";
        result += nodoROUND + " -> " + nodoPC + ";\n";
        
        result += this.expression.createAST(tree, nodoEXP);

        return result;
    }
}
