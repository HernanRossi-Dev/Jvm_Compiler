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

	public String toStringJVMRep(){
		String output = "";
		IRLabel irLable = new IRLabel(false);
		int labelNums = irLable.jvmLabelsUsed;
		int nextLable = labelNums+1;
		Type tempCurrentType = left.type;
		if (tempCurrentType.getType().equals(new StringType(0, 0))) {
			output += "aload " + left.number + "\n";
			output += "aload " + right.number + "\n";
			output += "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n";
			output += "iflt L_"+labelNums +"\n";
			output += "ldc 0\n";
			output += "goto L_"+nextLable+"\n";
			output += "L_"+labelNums +":\n";
			output += "ldc 1\n";

			output += "L_"+nextLable+":\n";
			output += "istore "+boolResult.number +"\n";
		}
		else if (tempCurrentType.getType().equals(new CharType(0, 0))) {
			output += "iload " + left.number + "\n";
			output += "iload " + right.number + "\n";
			output += "isub\n";
			output += "iflt L_"+labelNums +"\n";
			output += "ldc 0\n";
			output += "goto L_"+nextLable +"\n";
			output += "L_"+labelNums +":\n";
			output += "ldc 1\n";
			output += "L_"+nextLable+":\n";
			output += "istore "+boolResult.number +"\n";
		}
		else if (tempCurrentType.getType().equals(new FloatType(0, 0))) {
			output += "fload " + left.number + "\n";
			output += "fload " + right.number + "\n";
			output += "fcmpg\n";
			output += "iflt L_"+labelNums +"\n";
			output += "ldc 0\n";
			output += "goto L_"+nextLable+"\n";
			output += "L_"+labelNums +":\n";
			output += "ldc 1\n";

			output += "L_"+nextLable+":\n";
			output += "istore "+boolResult.number +"\n";
		} else {
			output += "iload " + left.number + "\n";
			output += "iload " + right.number + "\n";
			output += "isub\n";
			output += "iflt L_"+labelNums +"\n";
			output += "ldc 0\n";
			output += "goto L_"+nextLable +"\n";
			output += "L_"+labelNums +":\n";
			output += "ldc 1\n";
			output += "L_"+nextLable+":\n";
			output += "istore "+boolResult.number +"\n";
		}
		irLable.jvmLabelsUsed = labelNums +2;
		return output;
	}
}