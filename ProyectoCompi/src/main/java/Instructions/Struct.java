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
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class Struct extends Instruction{
    private String id;
    private LinkedList<HashMap> fields;

    public Struct(String id, LinkedList<HashMap> fields, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<HashMap> getFields() {
        return fields;
    }

    public void setFields(LinkedList<HashMap> fields) {
        this.fields = fields;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        return null;
    }
    
    
}
