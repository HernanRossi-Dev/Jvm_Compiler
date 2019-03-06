package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class AddIRExpression extends IRInstruction{

	public Temp left;
	public Temp right;
	public Temp addResult;
	public String typeOfIRExpr; // either I, F, C, U, Z

	public AddIRExpression(Temp l, Temp r, Temp res) {
		Type addType = l.type.getType();

		typeOfIRExpr = addType.getIRTag();
		left = l;
		right = r;
		addResult = res;
	}

	public String toString(){
		String output =  addResult.toString() + " := " + left.toString() +" "+ typeOfIRExpr +"+ " + right.toString() + ";\n";
		return output;
	}
}