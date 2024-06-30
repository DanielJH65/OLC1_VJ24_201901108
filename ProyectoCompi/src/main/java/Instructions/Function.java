/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.SymbolsTable;
import Symbol.Tree;
import Symbol.Type;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class Function extends Instruction {

    private String id;
    private LinkedList<Instruction> instructions;
    private LinkedList<HashMap> params;

    public Function(String id, LinkedList<HashMap> params, LinkedList<Instruction> instructions, Type type, int line, int col) {
        super(type, line, col);
        this.id = id;
        this.instructions = instructions;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public LinkedList<HashMap> getParams() {
        return params;
    }

    public void setParams(LinkedList<HashMap> params) {
        this.params = params;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        for (var ins : this.instructions) {
            var result = ins.interpretar(tree, table);
            if (result instanceof Errores errores) {
                tree.getErrores().add(errores);
            }
            if (result instanceof Break || result instanceof Continue) {
                return new Errores("Semantico", "Instrucción no valida", ins.getLine(), ins.getCol());
            }
            if (result instanceof Return aReturn) {
                if (aReturn.getType().getType() != this.getType().getType()) {
                    return new Errores("Semantico", "El retorno no es del mismo tipo de la función", ins.getLine(), ins.getCol());
                }
                return aReturn.getFinalValue();
            }
        }
        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoFCALL = "n" + tree.getContAST();
        String nodoTYPE = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoPARA = "n" + tree.getContAST();
        String nodoTYPE2 = "n" + tree.getContAST();

        String result = nodoFCALL + "[label=\"FUNCTION\"];\n";
        result += previous + " -> " + nodoFCALL + ";\n";

        result += nodoTYPE + "[label=\"TYPE\"];\n";
        result += nodoTYPE2 + "[label=\"" + this.getType().getType() + "\"];\n";
        result += nodoID + "[label=\"" + this.id + "\"];\n";
        result += nodoPARA + "[label=\"(\"];\n";

        result += nodoFCALL + " -> " + nodoTYPE + ";\n";
        result += nodoTYPE + " -> " + nodoTYPE2 + ";\n";
        result += nodoFCALL + " -> " + nodoID + ";\n";
        result += nodoFCALL + " -> " + nodoPARA + ";\n";

        for (var param : params) {
            String nodoID2 = "n" + tree.getContAST();
            String nodoTYPE3 = "n" + tree.getContAST();
            String nodoCOMA = "n" + tree.getContAST();
            result += nodoID2 + "[label=\"" + param.get("id") + "\"];\n";
            result += nodoTYPE3 + "[label=\"" + ((Type) param.get("type")).getType() + "\"];\n";
            result += nodoCOMA + "[label=\",\"];\n";
            result += nodoFCALL + " -> " + nodoID2 + ";\n";
            result += nodoFCALL + " -> " + nodoTYPE3 + ";\n";
            result += nodoFCALL + " -> " + nodoCOMA + ";\n";
        }

        String nodoPARC = "n" + tree.getContAST();
        result += nodoPARC + "[label=\")\"];\n";
        result += nodoFCALL + " -> " + nodoPARC + ";\n";
        
        String nodoLLA = "n" + tree.getContAST();
        result += nodoLLA + "[label=\"{\"];\n";
        result += nodoFCALL + " -> " + nodoLLA + ";\n";

        String nodoIN = "n" + tree.getContAST();
        result += nodoIN + "[label=\"INSTRUCTIONS\"];\n";
        result += nodoFCALL + " -> " + nodoIN + ";\n";

        String insprev = nodoIN;
        for (var ins : this.instructions) {
            String nodoIN2 = "n" + tree.getContAST();
            result += nodoIN2 + "[label=\"INSTRUCTIONS\"];\n";
            result += insprev + " -> " + nodoIN2 + ";\n";
            String nodoIN3 = "n" + tree.getContAST();
            result += nodoIN3 + "[label=\"INSTRUCTION\"];\n";
            result += insprev + " -> " + nodoIN3 + ";\n";
            result += ins.createAST(tree, nodoIN3);
            insprev = nodoIN2;
        }

        String nodoLLC = "n" + tree.getContAST();
        result += nodoLLC + "[label=\"}\"];\n";
        result += nodoFCALL + " -> " + nodoLLC + ";\n";

        return result;
    }

}
