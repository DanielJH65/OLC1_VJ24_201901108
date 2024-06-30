/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.Symbol;
import Symbol.SymbolsTable;
import Symbol.Tree;
import Symbol.Type;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class ListStatement extends Instruction {

    private String id;
    private LinkedList<Object> expressions;

    public ListStatement(String id, Type type, int line, int col) {
        super(type, line, col);
        this.id = id;
        this.expressions = new LinkedList();
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        Symbol sym = new Symbol(this.getType(), this.id, this.expressions, true, this.getLine(), this.getCol());

        boolean isCreated = table.setVariable(sym);
        if (!isCreated) {
            return new Errores("Sintactico", "Identificador de variable existente", this.getLine(), this.getCol());
        }
        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoLA = "n" + tree.getContAST();
        String nodoLIST = "n" + tree.getContAST();
        String nodoMN = "n" + tree.getContAST();
        String nodoTYPE = "n" + tree.getContAST();
        String nodoMY = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoEQUAL = "n" + tree.getContAST();
        String nodoNEW = "n" + tree.getContAST();
        String nodoLISR2 = "n" + tree.getContAST();
        String nodoPARA = "n" + tree.getContAST();
        String nodoPARC = "n" + tree.getContAST();
        String nodoTYPE2 = "n" + tree.getContAST();

        String result = nodoLA + "[label=\"LIST STATEMENT\"];\n";
        result += previous + " -> " + nodoLA + ";\n";

        result += nodoLIST + "[label=\"List\"];\n";
        result += nodoMN + "[label=\"\\<\"];\n";
        result += nodoTYPE + "[label=\"TYPE\"];\n";
        result += nodoMY + "[label=\"\\>\"];\n";
        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoEQUAL + "[label=\"=\"];\n";
        result += nodoNEW + "[label=\"new\"];\n";
        result += nodoLISR2 + "[label=\"List\"];\n";
        result += nodoPARA + "[label=\"(\"];\n";
        result += nodoPARC + "[label=\")\"];\n";
        result += nodoLA + " -> " + nodoLIST + ";\n";
        result += nodoLA + " -> " + nodoMN + ";\n";
        result += nodoLA + " -> " + nodoTYPE + ";\n";
        result += nodoLA + " -> " + nodoMY + ";\n";
        result += nodoLA + " -> " + nodoID + ";\n";
        result += nodoLA + " -> " + nodoEQUAL + ";\n";
        result += nodoLA + " -> " + nodoLISR2 + ";\n";
        result += nodoLA + " -> " + nodoEQUAL + ";\n";
        result += nodoLA + " -> " + nodoPARA + ";\n";
        result += nodoLA + " -> " + nodoPARC + ";\n";
        
        result += nodoTYPE2 + "[label=\"" + this.getType().getType() + "\"];\n";
        
        result += nodoTYPE + " -> " + nodoTYPE2 + ";\n";

        return result;
    }

}
