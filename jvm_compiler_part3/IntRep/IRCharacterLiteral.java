package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRCharacterLiteral extends IRInstruction{

	public Temp tempLeft;
	public char value;


	public IRCharacterLiteral(Temp l, char val) {
		tempLeft = l;
		value = val;
	}

	public String toString(){
		String output = tempLeft.toString() + " :=\'" + value + "\';\n" ;
		//System.out.println(output);
		return output;
	}
}