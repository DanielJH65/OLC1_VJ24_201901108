/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Symbol;

/**
 *
 * @author daniel
 */
public class Symbol {
    private Type type;
    private String id;
    private Object value;
    private boolean mutable;
    private int line;
    private int column;

    public Symbol(Type type, String id, Object value, boolean mutable, int line, int column) {
        this.type = type;
        this.id = id;
        this.value = value;
        this.mutable = mutable;
        this.line = line;
        this.column = column;
    }

    public boolean isMutable() {
        return mutable;
    }

    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    
}
