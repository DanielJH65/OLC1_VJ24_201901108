/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Reportes.Simbolo;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class For extends Instruction {

    private Instruction assignement;
    private Instruction condition;
    private Instruction increment;
    private LinkedList<Instruction> instructions;

    public For(Instruction assignement, Instruction condition, Instruction increment, LinkedList<Instruction> instructions, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.assignement = assignement;
        this.condition = condition;
        this.increment = increment;
        this.instructions = instructions;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {

        var res1 = this.assignement.interpretar(tree, table);
        if (res1 instanceof Errores) {
            return res1;
        }

        var cond = this.condition.interpretar(tree, table);
        if (cond instanceof Errores) {
            return cond;
        }
        if (this.condition.getType().getType() != TipoDato.BOOLEAN) {
            return new Errores("Semantico", "Expresion no valida, debe ser bool", this.condition.getLine(), this.condition.getCol());
        }

        boolean break1 = false;
        boolean return1 = false;
        Return aReturn = null;
        while (Boolean.parseBoolean(this.condition.interpretar(tree, table).toString())) {
            var newTable = new SymbolsTable(table);
            for (var ins : this.instructions) {
                if (ins instanceof Break) {
                    break1 = true;
                    break;
                }
                if (ins instanceof Continue) {
                    break;
                }
                var result = ins.interpretar(tree, newTable);
                if (result instanceof Errores errores) {
                    tree.getErrores().add(errores);
                }
                if (result instanceof Break) {
                    break1 = true;
                    break;
                }
                if (result instanceof Continue) {
                    break;
                }
                if (result instanceof Return aReturn1) {
                    return1 = true;
                    aReturn = aReturn1;
                }
            }
            var inc = this.increment.interpretar(tree, table);
            if (inc instanceof Errores) {
                return inc;
            }
            if (break1) {
                break;
            }
            if (return1) {
                return aReturn;
            }
            LinkedList<Simbolo> newList = newTable.getSimbolos();
            for (var sym : newList) {
                sym.setScope("For " + this.getLine());
            }
            table.getSymbols().addAll(newTable.getSymbols());
            if (newList != null) {
                table.getSymbols().addAll(newList);
            }
        }

        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoLA = "n" + tree.getContAST();

        String result = nodoLA + "[label=\"FOR\"];\n";
        result += previous + " -> " + nodoLA + ";\n";

        String nodoWHILE = "n" + tree.getContAST();
        result += nodoWHILE + "[label=\"while\"];\n";
        result += nodoLA + " -> " + nodoWHILE + ";\n";

        String nodoPARA = "n" + tree.getContAST();
        result += nodoPARA + "[label=\"(\"];\n";
        result += nodoLA + " -> " + nodoPARA + ";\n";

        String nodoEXP = "n" + tree.getContAST();
        result += nodoEXP + "[label=\"STATEMENT\"];\n";
        result += nodoLA + " -> " + nodoEXP + ";\n";

        result += this.assignement.createAST(tree, nodoEXP);
        
        String nodoPYC1 = "n" + tree.getContAST();
        result += nodoPYC1 + "[label=\";\"];\n";
        result += nodoLA + " -> " + nodoPYC1 + ";\n";
        
        String nodoEXP2 = "n" + tree.getContAST();
        result += nodoEXP2 + "[label=\"CONDITION\"];\n";
        result += nodoLA + " -> " + nodoEXP2 + ";\n";

        result += this.condition.createAST(tree, nodoEXP2);
        
        String nodoPYC2 = "n" + tree.getContAST();
        result += nodoPYC2 + "[label=\";\"];\n";
        result += nodoLA + " -> " + nodoPYC2 + ";\n";
        
        String nodoEXP3 = "n" + tree.getContAST();
        result += nodoEXP3 + "[label=\"ASIGNEMENT\"];\n";
        result += nodoLA + " -> " + nodoEXP3 + ";\n";

        result += this.increment.createAST(tree, nodoEXP3);

        String nodoPARC = "n" + tree.getContAST();
        result += nodoPARC + "[label=\")\"];\n";
        result += nodoLA + " -> " + nodoPARC + ";\n";

        String nodoLLA = "n" + tree.getContAST();
        result += nodoLLA + "[label=\"{\"];\n";
        result += nodoLA + " -> " + nodoLLA + ";\n";

        String nodoIN = "n" + tree.getContAST();
        result += nodoIN + "[label=\"INSTRUCTIONS\"];\n";
        result += nodoLA + " -> " + nodoIN + ";\n";

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
        result += nodoLA + " -> " + nodoLLC + ";\n";

        result += this.condition.createAST(tree, nodoEXP);

        return result;
    }
}
