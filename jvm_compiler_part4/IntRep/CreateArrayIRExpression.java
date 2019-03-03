package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class CreateArrayIRExpression extends IRInstruction{

	public Temp array;
	public int size;
	public String typeIR;

	public CreateArrayIRExpression(Temp a) {
		ArrayType ar = (ArrayType) a.type;
		array = a;
		size = ar.size;;
		typeIR = a.type.getType().getIRTag();
	}

	public String toString(){
		String output = array.toString() + " := NEWARRAY "+typeIR+" "+ size +";\n";
		return output;
	}

	public String toStringJVMRep(){
		int tempNumber = array.number;
		String output = "";

		if(typeIR.equals("F")){
			typeIR = "float";
			output = "ldc " + size  + "\nnewarray " +  typeIR+ "\nastore " + tempNumber+ "\n\n";
		}else if(typeIR.equals("C")) {
			typeIR = "char";
			output = "ldc " + size  + "\nnewarray " +  typeIR+ "\nastore " + tempNumber+ "\n\n";
		}else if(typeIR.equals("I")) {
			typeIR = "int";
			output = "ldc " + size  + "\nnewarray " +  typeIR+ "\nastore " + tempNumber+ "\n\n";
		}else if(typeIR.equals("Z")) {
			typeIR = "boolean";
			output = "ldc " + size  + "\nnewarray " +  typeIR+ "\nastore " + tempNumber+ "\n\n";
		}
		else if(typeIR.equals("U")) {
			output = "ldc " + size  + "\nanewarray java/lang/String\nastore " + tempNumber+ "\n\n";
		}
		return output;
	}
}