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
public class ListStatement extends Instruction{
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
    
}
