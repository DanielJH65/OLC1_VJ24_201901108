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
public class Struct extends Instruction {

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

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoFCALL = "n" + tree.getContAST();

        String result = nodoFCALL + "[label=\"STRUCT\"];\n";
        result += previous + " -> " + nodoFCALL + ";\n";

        String nodoLLA1 = "n" + tree.getContAST();
        result += nodoLLA1 + "[label=\"struct\"];\n";
        result += nodoFCALL + " -> " + nodoLLA1 + ";\n";

        String nodoLLA = "n" + tree.getContAST();
        result += nodoLLA + "[label=\"{\"];\n";
        result += nodoFCALL + " -> " + nodoLLA + ";\n";

        String nodoIN = "n" + tree.getContAST();
        result += nodoIN + "[label=\"LISTA STRUCT\"];\n";
        result += nodoFCALL + " -> " + nodoIN + ";\n";

        String insprev = nodoIN;
        for (var ins : this.fields) {
            String nodoIN2 = "n" + tree.getContAST();
            result += nodoIN2 + "[label=\"LISTA STRUCT\"];\n";
            result += insprev + " -> " + nodoIN2 + ";\n";
            String nodoIN3 = "n" + tree.getContAST();
            result += nodoIN3 + "[label=\"FIELD STRUCT\"];\n";
            result += insprev + " -> " + nodoIN3 + ";\n";
            String nodoID = "n" + tree.getContAST();
            result += nodoID + "[label=\"" + ins.get("id") + "\"];\n";
            result += nodoIN3 + " -> " + nodoID + ";\n";
            String nodoTYPE = "n" + tree.getContAST();
            result += nodoTYPE + "[label=\"" + ins.get("type") + "\"];\n";
            result += nodoIN3 + " -> " + nodoTYPE + ";\n";
            insprev = nodoIN2;
        }

        String nodoLLC = "n" + tree.getContAST();
        result += nodoLLC + "[label=\"}\"];\n";
        result += nodoFCALL + " -> " + nodoLLC + ";\n";

        String nodoID10 = "n" + tree.getContAST();
        result += nodoID10 + "[label=\"" + this.id + "\"];\n";
        result += nodoFCALL + " -> " + nodoID10 + ";\n";

        return result;
    }

}
