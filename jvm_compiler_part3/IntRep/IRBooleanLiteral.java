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
}