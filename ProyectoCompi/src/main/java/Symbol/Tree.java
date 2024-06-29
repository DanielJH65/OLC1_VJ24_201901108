/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Symbol;

import Instructions.Struct;
import Abstract.Instruction;
import Exceptions.Errores;
import Instructions.Function;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class Tree {

    private LinkedList<Instruction> instructions;
    private String console;
    private SymbolsTable globalTable;
    private LinkedList<Errores> errores;
    private LinkedList<Instruction> functions;
    private LinkedList<Instruction> structs;

    public Tree(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
        this.console = "";
        this.globalTable = new SymbolsTable();
        this.errores = new LinkedList<>();
        this.functions = new LinkedList<>();
        this.structs = new LinkedList<>();
    }

    public LinkedList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public SymbolsTable getGlobalTable() {
        return globalTable;
    }

    public void setGlobalTable(SymbolsTable globalTable) {
        this.globalTable = globalTable;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }

    public void print(String value) {
        this.console += value + "\n";
    }

    public LinkedList<Instruction> getFunctions() {
        return functions;
    }

    public void setFunctions(LinkedList<Instruction> functions) {
        this.functions = functions;
    }

    public void addFunctions(Instruction function) {
        if (this.getFunction(((Function) function).getId()) == null) {
            this.functions.add(function);
        } else {
            this.errores.add(new Errores("Semantico", "La funcion ya existe", function.getLine(), function.getCol()));
        }
    }

    public Instruction getFunction(String id) {
        for (var ins : this.functions) {
            if (((Function) ins).getId().equalsIgnoreCase(id)) {
                return ins;
            }
        }
        return null;
    }

    public LinkedList<Instruction> getStructs() {
        return structs;
    }

    public void setStructs(LinkedList<Instruction> structs) {
        this.structs = structs;
    }

    public Instruction getStruct(String id) {
        for (var ins : this.structs) {
            if (((Struct) ins).getId().equalsIgnoreCase(id)) {
                return ins;
            }
        }
        return null;
    }
    
    public void addStruct(Instruction struct){
        if (this.getStruct(((Struct) struct).getId()) == null) {
            this.structs.add(struct);
        } else {
            this.errores.add(new Errores("Semantico", "El struct ya existe", struct.getLine(), struct.getCol()));
        }
    }
}
