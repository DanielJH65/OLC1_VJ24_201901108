/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Symbol;

import Abstract.Instruction;
import Exceptions.Errores;
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

    public Tree(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
        this.console = "";
        this.globalTable = new SymbolsTable();
        this.errores = new LinkedList<>();
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
    
}
