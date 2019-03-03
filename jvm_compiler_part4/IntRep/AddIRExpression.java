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

	public String toStringJVMRep(){
		int tempLeftNumber = left.number;
		int tempRightNumber = right.number;
		int tempResultNumber = addResult.number;
		String output = "";
		if(typeOfIRExpr.equals("F")){
			output += "fload " + tempLeftNumber  + "\nfload " +  tempRightNumber+
					"\nfadd\nfstore "+tempResultNumber + "\n\n";
		}else if(typeOfIRExpr.equals("C")){
			output +=  "iload " + tempLeftNumber  + "\niload " +  tempRightNumber+
					"\niadd\ni2c\nistore "+tempResultNumber + "\n\n";
		}else if(typeOfIRExpr.equals("U")){
			output +=  "new java/lang/StringBuffer\ndup\ninvokenonvirtual java/lang/StringBuffer/<init>()V\n"
					+ "aload " + tempLeftNumber  + "\ninvokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;"
					+ "\naload " +  tempRightNumber+ "\ninvokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;"
					+ "\ninvokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;\nastore " + tempResultNumber+ "\n\n";
		}else {
			output += "iload " + tempLeftNumber  + "\niload " +  tempRightNumber+
					"\niadd\nistore "+tempResultNumber + "\n\n";
		}

		return output;
	}
}