/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Symbol;

import java.util.HashMap;

/**
 *
 * @author daniel
 */
public class SymbolsTable {

    private SymbolsTable previousTable;
    private HashMap<String, Object> table;
    private String name;

    public SymbolsTable() {
        this.table = new HashMap<>();
        this.name = "";
    }

    public SymbolsTable(SymbolsTable previousTable) {
        this.previousTable = previousTable;
        this.table = new HashMap<>();
        this.name = "";
    }

    public SymbolsTable getPreviousTable() {
        return previousTable;
    }

    public void setPreviousTable(SymbolsTable previousTable) {
        this.previousTable = previousTable;
    }

    public HashMap<String, Object> getTable() {
        return table;
    }

    public void setTable(HashMap<String, Object> table) {
        this.table = table;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean setVariable(Symbol variable) {
        Symbol match = (Symbol) this.table.get(variable.getId().toLowerCase());

        if (match == null) {
            this.table.put(variable.getId().toLowerCase(), variable);
            return true;
        }
        return false;
    }
    
    public Symbol getVariable(String id){
        Symbol match = (Symbol) this.table.get(id.toLowerCase());
        
        if(match != null){
            return match;
        }
        return null;
    }

}
