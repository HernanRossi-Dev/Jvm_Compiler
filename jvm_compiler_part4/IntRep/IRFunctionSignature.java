package IntRep;

import AST.*;
import Type.*;
import Semantic.*;
import java.util.*;
import java.io.*;

public class IRFunctionSignature extends IRInstruction{
	public String name;
	public TempFactory tempFactory;
	public ArrayList<IRInstruction> functionInstructionList;
	public Type returnType;
	public Function function;
	public int numberOfLabelsInFunction = 0;
	public String programName;

	public IRFunctionSignature(String n, Function f) {
		tempFactory = new TempFactory();
        functionInstructionList = new ArrayList<IRInstruction> ();
		name = n;
		function = f;
	}

	public void addInstruction(IRInstruction ir) {
		functionInstructionList.add(ir);
	}

	public String toString(){
		return null;
	}
	public String toStringJVMRep(){
		return null;
	}

	public String createIRFile(BufferedWriter writer){
		try{

			String funcIRsigOutput = "FUNC " + name + "(";
			FunctionDeclaration funcDec = function.funcDecl;
			ArrayList<FormalParameter> cureList = funcDec.formalParameterList.formalParameterList;
			Iterator itr = cureList.iterator();
			while(itr.hasNext()) {
				FormalParameter fp = (FormalParameter)itr.next();
				Type type = fp.type;
				funcIRsigOutput += type.getIRTag();
			}
			funcIRsigOutput += ")" + funcDec.type.getIRTag() + "\n";
			funcIRsigOutput += "{\n";
			funcIRsigOutput += tempFactory.toString();
			funcIRsigOutput += "\n";
			funcIRsigOutput += printFunctionInstructionListIRRepresentation();
			funcIRsigOutput += "RETURN;\n";
			funcIRsigOutput += "}\n\n";
			writer.write(funcIRsigOutput);
		}catch(IOException e) {
			System.out.println("Error in signature.");
			System.out.println(e);
		}
		return null;
	}


	public String createJFile(BufferedWriter writer){
		try{
			IRLabel irLable = new IRLabel(false);


			if(name.equals("main")){
				name = "__main";
			}
			String funcIRsigOutput = ".method public static " + name + "(";
			FunctionDeclaration funcDec = function.funcDecl;
			ArrayList<FormalParameter> cureList = funcDec.formalParameterList.formalParameterList;
			Iterator itr = cureList.iterator();
			String stringArgType = "Ljava/lang/String;";
			while(itr.hasNext()) {
				FormalParameter fp = (FormalParameter)itr.next();
				Type type = fp.type;
				String tag = type.getType().getIRTag();
				if(type.getType().equals(new StringType(0,0))){
					tag = stringArgType;
				}
				if(type.toString().equals("ArrayType")){
					funcIRsigOutput += "["+tag;
				} else{
					funcIRsigOutput += tag;
				}

//				if(type.toString().equals("ArrayType")){
//					funcIRsigOutput += "[" + type.getType().getIRTag();
//				}else{
//					funcIRsigOutput += type.getIRTag();
//				}
			}
			String returnTag = funcDec.type.getIRTag() ;

			//TODO need to make returning an array work

//			if(returnTag.equals("U")){
//				returnTag = stringArgType
//			}
			if(returnTag.charAt(0) == 'A'){
				if(returnTag.charAt(1) == 'U'){
					funcIRsigOutput += ")" +"["+stringArgType+ "\n";
				}else{
					funcIRsigOutput += ")" +"["+returnTag.charAt(1)+ "\n";
				}

			} else{
				if(returnTag == "U"){
					funcIRsigOutput += ")" +stringArgType+ "\n";
				}else{
					funcIRsigOutput += ")" +returnTag+ "\n";
				}
			}
			//funcIRsigOutput += ")" + returnTag+ "\n";
			funcIRsigOutput += ".limit locals " + tempFactory.tempCount + "\n";
			funcIRsigOutput += ".limit stack 20 \n\n";
			funcIRsigOutput += "L_" + irLable.jvmLabelsUsed+":\n";
			int endOfFuncLabel =  irLable.jvmLabelsUsed;
			irLable.jvmLabelsUsed+=2;
			//TODO
			funcIRsigOutput += tempFactory.toStringJfile();

			funcIRsigOutput += "\n";

			//TODO
			funcIRsigOutput += printFunctionInstructionListJFile();

			funcIRsigOutput += "return\n";
			endOfFuncLabel++;
			funcIRsigOutput += "L_" + endOfFuncLabel+":\n";
			funcIRsigOutput += ".end method\n\n";
			writer.write(funcIRsigOutput);
		}catch(IOException e) {
			System.out.println("Error in signature.");
			System.out.println(e);
		}
		return null;
	}


	//TODO all instructions implement the toStringJVMRep method
	public String printFunctionInstructionListJFile() {
		String instructionListOutput = "";
		int numberOfInstruction = functionInstructionList.size();
		for(int i = 0; i< numberOfInstruction; i++) {
			IRInstruction currentInstruction = (IRInstruction)functionInstructionList.get(i);
			if(currentInstruction instanceof FunctionCallIrExpression){
				FunctionDeclaration funcDec = function.funcDecl;
				FunctionCallIrExpression currcall = (FunctionCallIrExpression)currentInstruction;
				instructionListOutput += currcall.toStringJVMRep(funcDec.type.getIRTag(), programName);
			} else{
				instructionListOutput += currentInstruction.toStringJVMRep();
			}
		}
		return instructionListOutput;
	}


	public String printFunctionInstructionListIRRepresentation() {
		String instructionListOutput = "";
		int numberOfInstruction = functionInstructionList.size();
		for(int i = 0; i< numberOfInstruction; i++) {
			IRInstruction currentInstruction = (IRInstruction)functionInstructionList.get(i);
			instructionListOutput += currentInstruction.toString();
		}
		return instructionListOutput;
	}
}