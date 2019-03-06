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
			funcIRsigOutput += printFunctionInstructionList();
			funcIRsigOutput += "RETURN;\n";
			funcIRsigOutput += "}\n\n";
			writer.write(funcIRsigOutput);
		}catch(IOException e) {
			System.out.println("Error in signature.");
			System.out.println(e);
		}
		return null;
	}

	public String printFunctionInstructionList() {
		String instructionListOutput = "";
		int numberOfInstruction = functionInstructionList.size();
		for(int i = 0; i< numberOfInstruction; i++) {
			IRInstruction currentInstruction = (IRInstruction)functionInstructionList.get(i);
			instructionListOutput += currentInstruction.toString();
		}
		return instructionListOutput;
	}
}