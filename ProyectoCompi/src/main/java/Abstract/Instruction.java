/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Abstract;
import Symbol.Tree;
import Symbol.SymbolsTable;
import Symbol.Type;
/**
 *
 * @author daniel
 */
public abstract class Instruction {
    private Type type;
    private int line;
    private int col;

    public Instruction(Type type, int line, int col) {
        this.type = type;
        this.line = line;
        this.col = col;
    }
    
    public abstract Object interpretar(Tree tree, SymbolsTable table);

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
    
}
