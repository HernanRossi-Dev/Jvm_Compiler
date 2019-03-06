package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class SubIRExpression extends IRInstruction{

	public Temp left;
	public Temp right;
	public Temp subResult;
	public String typeOfIRExpr; // either I, F, C, U, Z

	public SubIRExpression(Temp l, Temp r, Temp res) {
		Type subType = l.type.getType();

		typeOfIRExpr = subType.getIRTag();
		left = l;
		right = r;
		subResult = res;
	}

	public String toString(){
		String output =  subResult.toString() + " := " + left.toString() +" "+ typeOfIRExpr +"- " + right.toString() + ";\n";
		return output;
	}
}