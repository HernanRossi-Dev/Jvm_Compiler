package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRIntegerLiteral extends IRInstruction{

	public Temp tempLeft;
	public int value;


	public IRIntegerLiteral(Temp l, int val) {
		tempLeft = l;
		value = val;
	}

	public String toString(){
		String output = tempLeft.toString() + " := " + value + ";\n" ;
		//System.out.println(output);
		return output;
	}
}