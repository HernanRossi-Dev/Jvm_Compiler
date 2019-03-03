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

	public String toStringJVMRep(){
		String output = "";
		IRLabel irLable = new IRLabel(false);
		int labelNums = irLable.jvmLabelsUsed;
		int nextLable = labelNums+1;
		Type tempCurrentType = left.type;
		if(tempCurrentType.getType().equals(new BooleanType(0, 0))) {
			output += "iload " + left.number + "\n";
			output += "iload " + right.number + "\n";
			output += "ixor\n";
			output += "ldc 1\n";
			output += "ixor\n";
			output += "istore " + boolResult.number + "\n";
		}else if (tempCurrentType.getType().equals(new FloatType(0, 0))) {
			output += "fload " + left.number + "\n";
			output += "fload " + right.number + "\n";
			output += "fcmpg\n";
			output += "ifeq L_"+labelNums +"\n";
			output += "ldc 0\n";
			output += "goto L_"+nextLable+"\n";
			output += "L_"+labelNums +":\n";
			output += "ldc 1\n";

			output += "L_"+nextLable+":\n";
			output += "istore "+boolResult.number +"\n";
			irLable.jvmLabelsUsed = labelNums +2;
		}else if (tempCurrentType.getType().equals(new StringType(0, 0))) {
			output += "aload " + left.number + "\n";
			output += "aload " + right.number + "\n";
			output += "invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n";
			output += "ifeq L_"+labelNums +"\n";
			output += "ldc 0\n";
			output += "goto L_"+nextLable+"\n";
			output += "L_"+labelNums +":\n";
			output += "ldc 1\n";

			output += "L_"+nextLable+":\n";
			output += "istore "+boolResult.number +"\n";
			irLable.jvmLabelsUsed = labelNums +2;
		} else {
			output += "iload " + left.number + "\n";
			output += "iload " + right.number + "\n";
			output += "isub\n";
			output += "ifeq L_"+labelNums +"\n";
			output += "ldc 0\n";
			output += "goto L_"+nextLable +"\n";
			output += "L_"+labelNums +":\n";
			output += "ldc 1\n";
			output += "L_"+nextLable+":\n";
			output += "istore "+boolResult.number +"\n";
			irLable.jvmLabelsUsed = labelNums +2;
		}

		return output;
	}
}