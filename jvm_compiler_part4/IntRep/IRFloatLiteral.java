package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRFloatLiteral extends IRInstruction{

	public Temp tempLeft;
	public float value;


	public IRFloatLiteral(Temp l, float val) {
		tempLeft = l;
		value = val;
	}

	public String toString(){
		String output = tempLeft.toString() + " := " + value + ";\n" ;
		//System.out.println(output);
		return output;
	}

	public String toStringJVMRep(){
		int tempLeftNumber = tempLeft.number;
		String output =  "ldc " + value  + "\nfstore " +  tempLeftNumber+ "\n";
		return output;
	}
}