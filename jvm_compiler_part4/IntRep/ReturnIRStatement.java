package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class ReturnIRStatement extends IRInstruction{

	public Temp returnTemp;

	public ReturnIRStatement(Temp r) {
		returnTemp = r;
	}

	public String toString(){
		String output = null;
		if(returnTemp ==null){
			output = "RETURN;\n";
		}else{
			output = "RETURN " + returnTemp +";\n";
		}

		return output;
	}

	public String toStringJVMRep(){
		String output = "";
		if(returnTemp ==null){
			output = "return\n";
		}else {
			Type tempCurrentType = returnTemp.type;
			if (tempCurrentType.toString().equals("ArrayType")) {
				output = "aload " + returnTemp.number + "\nareturn\n";
			} else if (tempCurrentType.getType().equals(new StringType(0, 0))) {
				output = "aload " + returnTemp.number + "\nareturn\n";
			} else if (tempCurrentType.getType().equals(new FloatType(0, 0))) {
				output = "fload " + returnTemp.number + "\nfreturn\n";
			} else {
				output = "iload " + returnTemp.number + "\nireturn\n";
			}
		}
		return output;
	}
}