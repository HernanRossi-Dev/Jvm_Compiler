package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class MultIRExpression extends IRInstruction{

	public Temp left;
	public Temp right;
	public Temp multResult;
	public String typeOfIRExpr; // either I, F, C, U, Z

	public MultIRExpression(Temp l, Temp r, Temp res) {
		Type subType = l.type.getType();

		typeOfIRExpr = subType.getIRTag();
		left = l;
		right = r;
		multResult = res;
	}

	public String toString(){
		String output =  multResult.toString() + " := " + left.toString() +" "+ typeOfIRExpr +"* " + right.toString() + ";\n";
		return output;
	}
}