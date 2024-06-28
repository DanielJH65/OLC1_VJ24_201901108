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
            if (result instanceof Errores) {
                return result;
            }
            if (result instanceof Break || result instanceof Continue) {
                return new Errores("Semantico", "Instrucción no valida", ins.getLine(), ins.getCol());
            }
        }
        return null;
    }

}
