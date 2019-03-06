package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRStringLiteral extends IRInstruction{

	public Temp tempLeft;
	public String value;


	public IRStringLiteral(Temp l, String val) {
		tempLeft = l;
		value = val;
	}

	public String toString(){
		String output = tempLeft.toString() + " := \"" + value + "\";\n" ;
		//System.out.println(output);
		return output;
	}
}