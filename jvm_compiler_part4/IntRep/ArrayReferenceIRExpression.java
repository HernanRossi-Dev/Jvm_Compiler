package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class ArrayReferenceIRExpression extends IRInstruction{

	public Temp arrayRef;
	public Temp resultTemp;
	public Temp indexTemp;


	public ArrayReferenceIRExpression(Temp l, Temp r, Temp i) {
		arrayRef = l;
		resultTemp = r;
		indexTemp = i;
	}

	public String toString(){
		String output = resultTemp.toString() + " := " + arrayRef.toString() +"[" + indexTemp.toString() +"];\n";
		return output;
	}

	public String toStringJVMRep(){
		String output = "";
		Type tempCurrentType = arrayRef.type;
		if (tempCurrentType.getType().equals(new StringType(0, 0))) {
			output += "aload " + arrayRef.number + "\n";
			output += "iload " + indexTemp.number + "\n";
			output += "aaload\n";
			output += "astore " + resultTemp.number + "\n";
		} else if (tempCurrentType.getType().equals(new FloatType(0, 0))) {
			output += "aload " + arrayRef.number + "\n";
			output += "iload " + indexTemp.number + "\n";
			output += "faload\n";
			output += "fstore " + resultTemp.number + "\n";
		}else if (tempCurrentType.getType().equals(new BooleanType(0, 0))) {
			output += "aload " + arrayRef.number + "\n";
			output += "iload " + indexTemp.number + "\n";
			output += "baload\n";
			output += "istore " + resultTemp.number + "\n";
		} else if (tempCurrentType.getType().equals(new CharType(0, 0))) {
			output += "aload " + arrayRef.number + "\n";
			output += "iload " + indexTemp.number + "\n";
			output += "caload\n";
			output += "istore " + resultTemp.number + "\n";
		} else {
			output += "aload " + arrayRef.number + "\n";
			output += "iload " + indexTemp.number + "\n";
			output += "iaload\n";
			output += "istore " + resultTemp.number + "\n";
		}
		return output;
	}


}