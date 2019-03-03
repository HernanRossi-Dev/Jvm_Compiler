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

	public String toStringJVMRep(){
		int tempLeftNumber = left.number;
		int tempRightNumber = right.number;
		int tempResultNumber = multResult.number;
		if(typeOfIRExpr.equals("F")){
			typeOfIRExpr = "f";
		}else {
			typeOfIRExpr = "i";
		}
		String output =  typeOfIRExpr+"load " + tempLeftNumber  + "\n" +typeOfIRExpr + "load " +  tempRightNumber+
				"\n" +typeOfIRExpr +"mul\n" + typeOfIRExpr+ "store "+tempResultNumber + "\n\n";
		return output;
	}


}