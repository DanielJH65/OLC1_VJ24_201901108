/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instructions;

import Abstract.Instruction;
import Exceptions.Errores;
import Symbol.SymbolsTable;
import Symbol.TipoDato;
import Symbol.Tree;
import Symbol.Type;
import java.util.LinkedList;

/**
 *
 * @author daniel
 */
public class StartWith extends Instruction{
    private String id;
    private LinkedList<Instruction> params;

    public StartWith(String id, LinkedList<Instruction> params, int line, int col) {
        super(new Type(TipoDato.VOID), line, col);
        this.id = id;
        this.params = params;
    }

    @Override
    public Object interpretar(Tree tree, SymbolsTable table) {
        var search = tree.getFunction(this.id);
        if(search == null){
            return new Errores("Semantico", "La función no existe", this.getLine(), this.getCol());
        }
        if(search instanceof Function function){
            var newTable = new SymbolsTable(tree.getGlobalTable());
            newTable.setName("Start");
            
            if(this.params.size() != function.getParams().size()){
                return new Errores("Semantico", "EL número de parametros no coincide", this.getLine(), this.getCol());
            }
            
            for(int i = 0; i< this.params.size(); i++){
                var id = (String) function.getParams().get(i).get("id");
                var value = this.params.get(i);
                var type2 = (Type) function.getParams().get(i).get("type");
                
                var paramStatement = new Statement(false, id, value, type2, this.getLine(), this.getCol());
                var resultStatement = paramStatement.interpretar(tree, newTable);
                
                if(resultStatement  instanceof Errores){
                    return resultStatement;
                }
            }
            
            var functionResult = function.interpretar(tree, newTable);
            if(functionResult instanceof Errores){
                return functionResult;
            }
        }
        return null;
    }

    @Override
    public String createAST(Tree tree, String previous) {
        String nodoFCALL = "n" + tree.getContAST();
        String nodoFCALL2 = "n" + tree.getContAST();
        String nodoID = "n" + tree.getContAST();
        String nodoPARA = "n" + tree.getContAST();

        String result = nodoFCALL + "[label=\"Start With\"];\n";
        result += previous + " -> " + nodoFCALL+ ";\n";

        result += nodoFCALL2 + "[label=\"Start_With\"];\n";
        result += nodoID + "[label=\"\\" + this.id + "\"];\n";
        result += nodoPARA + "[label=\"\\(\"];\n";

        result += nodoFCALL + " -> " + nodoFCALL2+ ";\n";
        result += nodoFCALL + " -> " + nodoID+ ";\n";
        result += nodoFCALL + " -> " + nodoPARA+ ";\n";

        for (var param : params) {
            String nodoEXP = "n" + tree.getContAST();
            result += nodoEXP + "[label = \"EXPRESION\"];\n";
            result += nodoFCALL + " -> " + nodoEXP + ";\n";

            result += param.createAST(tree, nodoEXP);
        }

        String nodoPARC = "n" + tree.getContAST();
        result += nodoPARC + "[label=\"\\)\"];\n";
        result += nodoFCALL + " -> " + nodoPARC + ";\n";

        return result;
    }
    
    
    
    
}
