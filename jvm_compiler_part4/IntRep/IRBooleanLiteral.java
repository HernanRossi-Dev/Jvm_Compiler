package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRBooleanLiteral extends IRInstruction{

	public Temp tempLeft;
	public boolean value;


	public IRBooleanLiteral(Temp l, boolean val) {
		tempLeft = l;
		value = val;
	}

	public String toString(){
		String irBoolVal;
		if(value){
			irBoolVal = "TRUE";
		} else{
			irBoolVal ="FALSE";
		}
		String output = tempLeft.toString() + " := " + irBoolVal + ";\n" ;
		return output;
	}

	public String toStringJVMRep(){
		int pushVal = 0;
		if(value){
			pushVal = 1;
		}
		int tempLeftNumber = tempLeft.number;
		String output =  "ldc " + pushVal  + "\nistore " +  tempLeftNumber+ "\n";
		return output;
	}
}