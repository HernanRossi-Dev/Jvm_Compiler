package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IREqualityExpression extends IRInstruction{

	public Temp left;
	public Temp right;
	public Temp boolResult;
	public String typeOfIRExpr; // either I, F, C, U, Z

	public IREqualityExpression(Temp l, Temp r, Temp res) {
		Type eqType = l.type.getType();

		typeOfIRExpr = eqType.getIRTag();
		left = l;
		right = r;
		boolResult = res;
	}

	public String toString(){
		String output =  boolResult.toString() + " := " + left.toString() +" "+ typeOfIRExpr +"== " + right.toString() + ";\n";
		return output;
	}
}