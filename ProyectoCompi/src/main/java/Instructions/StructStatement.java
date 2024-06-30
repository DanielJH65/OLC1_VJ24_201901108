/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.Symbol;
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
public class StructStatement extends Instruction {

    private boolean isMutable;
    private String id;
    private String struct;
    private LinkedList<HashMap> data;

    public StructStatement(boolean isMutable, String id, String struct, LinkedList<HashMap> data, int line, int col) {
        super(new Type(TipoDato.STRUCT), line, col);
        this.isMutable = isMutable;
        this.id = id;
        this.struct = struct;
        this.data = data;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var search = tree.getStruct(this.struct);
        if (search == null) {
            return new Errores("Semantico", "El struct no existe", this.getLine(), this.getCol());
        }
        var struct = (Struct) search;
        if (this.data.size() != struct.getFields().size()) {
            return new Errores("Semantico", "EL número de campos no coincide", this.getLine(), this.getCol());
        }

        var finalvalue = new HashMap();
        finalvalue.put("id", this.id);
        var data = new LinkedList();

        for (int i = 0; i < this.data.size(); i++) {
            var id = (String) struct.getFields().get(i).get("id");
            var type2 = (Type) struct.getFields().get(i).get("type");
            var id2 = (String) this.data.get(i).get("id");
            if (!id.equalsIgnoreCase(id2)) {
                return new Errores("Semantico", "Los campos no coincide con el nombre", this.getLine(), this.getCol());
            }
            var value = ((Instruction) this.data.get(i).get("value"));
            var interValue = value.interpretar(tree, table);
            if (interValue instanceof Errores) {
                return interValue;
            }
            if (type2.getType() != value.getType().getType()) {
                return new Errores("Semantico", "Los campos no coincide con el tipo", this.getLine(), this.getCol());
            }
            var hash = new HashMap<String, Object>();
            hash.put("id", id);
            hash.put("type", type2);
            hash.put("value", interValue);
            data.addLast(hash);
        }

        finalvalue.put("data", data);

        Symbol sym = new Symbol(this.getType(), this.id, data, this.isMutable, this.getLine(), this.getCol());

        boolean isCreated = table.setVariable(sym);
        if (!isCreated) {
            return new Errores("Sintactico", "Identificador de variable existente", this.getLine(), this.getCol());
        }
        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoLA = "n" + tree.getContAST();
        String nodoMUT = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoDOSP = "n" + tree.getContAST();
        String nodoID2 = "n" + tree.getContAST();
        String nodoEQUAL = "n" + tree.getContAST();

        String nodoMUT2 = "n" + tree.getContAST();

        String result = nodoLA + "[label=\"STRUCT STATEMENT\"];\n";
        result += previous + " -> " + nodoLA + ";\n";

        result += nodoMUT + "[label=\"MUTABLE\"];\n";
        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoDOSP + "[label=\":\"];\n";
        result += nodoID2 + "[label=\"" + this.struct + "\"];\n";
        result += nodoEQUAL + "[label=\"=\"];\n";
        result += nodoLA + " -> " + nodoMUT + ";\n";
        result += nodoLA + " -> " + nodoID + ";\n";
        result += nodoLA + " -> " + nodoDOSP + ";\n";
        result += nodoLA + " -> " + nodoID2 + ";\n";
        result += nodoLA + " -> " + nodoEQUAL + ";\n";

        result += nodoMUT2 + "[label=\"" + (this.isMutable ? "var" : "const") + "\"];\n";
        result += nodoMUT + " -> " + nodoMUT2 + ";\n";

        String nodoLLA = "n" + tree.getContAST();
        result += nodoLLA + "[label=\"{\"];\n";
        result += nodoLA + " -> " + nodoLLA + ";\n";

        String nodoIN = "n" + tree.getContAST();
        result += nodoIN + "[label=\"LISTA STRUCT\"];\n";
        result += nodoLA + " -> " + nodoIN + ";\n";

        String insprev = nodoIN;
        for (var param : this.data) {
            String nodoIN2 = "n" + tree.getContAST();
            result += nodoIN2 + "[label=\"LISTA STRUCT\"];\n";
            result += insprev + " -> " + nodoIN2 + ";\n";
            String nodoIN3 = "n" + tree.getContAST();
            result += nodoIN3 + "[label=\"FIELD STRUCT\"];\n";
            result += insprev + " -> " + nodoIN3 + ";\n";
            String nodoID3 = "n" + tree.getContAST();
            result += nodoID3 + "[label=\"" + param.get("id") + "\"];\n";
            result += nodoIN3 + " -> " + nodoID3 + ";\n";
            String nodoTYPE = "n" + tree.getContAST();
            result += nodoTYPE + "[label=\"EXPRESSION\"];\n";
            result += nodoIN3 + " -> " + nodoTYPE + ";\n";
            result += ((Instruction) param.get("value")).createAST(tree, nodoTYPE);
            insprev = nodoIN2;
        }

        String nodoPARC = "n" + tree.getContAST();
        result += nodoPARC + "[label=\"}\"];\n";
        result += nodoLA + " -> " + nodoPARC + ";\n";

        return result;
    }

}
