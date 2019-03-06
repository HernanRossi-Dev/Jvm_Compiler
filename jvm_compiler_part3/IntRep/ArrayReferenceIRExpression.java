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
}