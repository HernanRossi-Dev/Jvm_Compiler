package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class LessThanIRExpression extends IRInstruction{

	public Temp left;
	public Temp right;
	public Temp boolResult;
	public String typeOfIRExpr; // either I, F, C, U, Z

	public LessThanIRExpression(Temp l, Temp r, Temp res) {
		Type lessthanType = l.type.getType();

		typeOfIRExpr = lessthanType.getIRTag();
		left = l;
		right = r;
		boolResult = res;
	}

	public String toString(){
		String output =  boolResult.toString() + " := " + left.toString() +" "+ typeOfIRExpr +"< " + right.toString() + ";\n";
		return output;
	}
}