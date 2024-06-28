/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Symbol;

import Reportes.Simbolo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author daniel
 */
public class SymbolsTable {

    private SymbolsTable previousTable;
    private HashMap<String, Object> table;
    private String name;
    private LinkedList<Simbolo> symbols;
    

    public SymbolsTable() {
        this.table = new HashMap<>();
        this.name = "";
        this.symbols = new LinkedList<>();
    }

    public SymbolsTable(SymbolsTable previousTable) {
        this.previousTable = previousTable;
        this.table = new HashMap<>();
        this.name = "";
        this.symbols = new LinkedList<>();
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

    public LinkedList<Simbolo> getSymbols() {
        return symbols;
    }

    public void setSymbols(LinkedList<Simbolo> symbols) {
        this.symbols = symbols;
    }

    public boolean setVariable(Symbol variable) {
        Symbol match = (Symbol) this.table.get(variable.getId().toLowerCase());

        if (match == null) {
            this.table.put(variable.getId().toLowerCase(), variable);
            return true;
        }
        return false;
    }

    public Symbol getVariable(String id) {
        for (SymbolsTable i = this; i != null; i = i.getPreviousTable()) {
            Symbol match = (Symbol) i.getTable().get(id.toLowerCase());

            if (match != null) {
                return match;
            }
        }
        return null;

    }
    
    public LinkedList<Simbolo> getSimbolos(){
        Iterator<Map.Entry<String, Object>> iterator = this.getTable().entrySet().iterator();
        LinkedList<Simbolo> list = new LinkedList<>();
        while(iterator.hasNext()){
            Map.Entry<String, Object> symbol = iterator.next();
            Symbol sym = (Symbol) symbol.getValue();
            String mutability = sym.isMutable() ? "Variable" : "Constante";
            list.add(new Simbolo(symbol.getKey(), sym.getType().getType(), mutability, 
                    "Global", sym.getValue(), sym.getLine(), sym.getColumn()));
        }
        return list;
    }
}
