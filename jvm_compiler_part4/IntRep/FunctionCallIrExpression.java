package IntRep;

import AST.*;
import Type.*;
import Semantic.*;
import java.util.*;
import java.io.*;

import java.util.ArrayList;

public class FunctionCallIrExpression extends IRInstruction {
    public ArrayList<Temp> listOFArgs;
    public Temp resultTemp = null;
    public String functionName;
    public String programName;
    public FunctionDeclaration funcDec;
    public String returnIrTag;

    public FunctionCallIrExpression(ArrayList<Temp> list, String fName) {
        listOFArgs = list;
        functionName = fName;
    }

    public FunctionCallIrExpression(ArrayList<Temp> list, String fName, Temp rTemp) {
        listOFArgs = list;
        resultTemp = rTemp;
        functionName = fName;
    }
    public void setFunctdecl(FunctionDeclaration s)
    {
        funcDec = s;
    }

    public void setIrTag(String tag){
        returnIrTag = tag;
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

    public String toStringJVMRep(String returnTagcall, String progName){
        Temp curTemp = null;
        Type currTempType = null;
        String output =  "";
        String stringArgType = "Ljava/lang/String;";

        int numberOFArgs = listOFArgs.size();
        for(int i =0; i < numberOFArgs; i ++) {
             curTemp = listOFArgs.get(i);
            currTempType = curTemp.type;
            if(currTempType.toString().equals("ArrayType")){
                output +=  "aload " + curTemp.number+ "\n";
            } else if(currTempType.getType().equals(new StringType(0,0))){
                output +=  "aload " + curTemp.number+ "\n";
            }else if(currTempType.getType().equals(new FloatType(0,0))){
                output +=  "fload " + curTemp.number+ "\n";
            }else{
                output +=  "iload " + curTemp.number+ "\n";
            }
        }
        output += "invokestatic " + progName + "/" + functionName + "(";
        numberOFArgs = listOFArgs.size();
        for(int i =0; i < numberOFArgs; i ++) {
            Type curType = listOFArgs.get(i).type;
            String tag = curType.getType().getIRTag();
            if(curType.getType().equals(new StringType(0,0))){
                tag = stringArgType;
            }
            if(curType.toString().equals("ArrayType")){
                output += "["+tag;
            } else{
                output += tag;
            }
        }

        if(returnIrTag.charAt(0) == 'A'){
            if(returnIrTag.charAt(1) == 'U'){
                output += ")" +"["+stringArgType+ "\n";
			}else{
                output += ")" +"["+returnIrTag.charAt(1)+ "\n";
            }

        } else{
            if(returnIrTag == "U"){
                output += ")" +stringArgType+ "\n";
            }else{
                output += ")" +returnIrTag+ "\n";
            }
        }

        if(resultTemp ==null){

        }else{
            Type resultTempType = resultTemp.type;
            if(resultTempType.toString().equals("ArrayType")){
                output +=  "astore " + resultTemp.number+ "\n";
            } else if(resultTempType.getType().equals(new StringType(0,0))){
                output +=  "astore " + resultTemp.number+ "\n";
            }else if(resultTempType.getType().equals(new FloatType(0,0))){
                output +=  "fstore " + resultTemp.number+ "\n";
            }else{
                output +=  "istore " + resultTemp.number+ "\n";
            }
        }
        return output;
    }

    public String toStringJVMRep(){return"";}
}
