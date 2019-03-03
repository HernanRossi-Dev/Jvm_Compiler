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

	public String toStringJVMRep(){
		int tempLeftNumber = left.number;
		int tempRightNumber = right.number;
		int tempResultNumber = subResult.number;
		String output =  "";
		if(typeOfIRExpr.equals("F")){
			typeOfIRExpr = "f";
			output =  typeOfIRExpr+"load " + tempLeftNumber  + "\n" +typeOfIRExpr + "load " +  tempRightNumber+
					"\n" +typeOfIRExpr +"sub\n" + typeOfIRExpr+ "store "+tempResultNumber + "\n\n";
		}else if(typeOfIRExpr.equals("C")){
			output +=  "iload " + tempLeftNumber  + "\niload " +  tempRightNumber+
					"\nisub\ni2c\nistore "+tempResultNumber + "\n\n";
		}else {
			typeOfIRExpr = "i";
			output =  typeOfIRExpr+"load " + tempLeftNumber  + "\n" +typeOfIRExpr + "load " +  tempRightNumber+
					"\n" +typeOfIRExpr +"sub\n" + typeOfIRExpr+ "store "+tempResultNumber + "\n\n";
		}

		return output;
	}
}