package IntRep;

import Type.Type;

import java.util.ArrayList;

public class FunctionCallIrExpression extends IRInstruction {
    public ArrayList<Temp> listOFArgs;
    public Temp resultTemp = null;
    public String functionName;

    public FunctionCallIrExpression(ArrayList<Temp> list, String fName) {
        listOFArgs = list;
        functionName = fName;
    }

    public FunctionCallIrExpression(ArrayList<Temp> list, String fName, Temp rTemp) {
        listOFArgs = list;
        resultTemp = rTemp;
        functionName = fName;
    }

    public String toString(){
        String output = "";
        if(resultTemp == null) {
            // void function call
            output =  "CALL " + functionName+"(";
            int numberOFArgs = listOFArgs.size();
            for(int i =0; i < numberOFArgs; i ++) {
                output += " " + listOFArgs.get(i).toString() + " ";
            }
            output += ");\n";
        } else {
            // put result in a Temp
            output =  resultTemp.toString() + ":= CALL " + functionName+"(";
            int numberOFArgs = listOFArgs.size();
            for(int i =0; i < numberOFArgs; i ++) {
                output += " " + listOFArgs.get(i).toString() + " ";
            }
            output += ");\n";
        }

        return output;
    }
}
