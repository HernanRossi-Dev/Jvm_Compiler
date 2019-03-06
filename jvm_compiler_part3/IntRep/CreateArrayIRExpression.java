package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class CreateArrayIRExpression extends IRInstruction{

	public Temp array;
	public int size;
	public String typeIR;

	public CreateArrayIRExpression(Temp a) {
		ArrayType ar = (ArrayType) a.type;
		array = a;
		size = ar.size;;
		typeIR = a.type.getType().getIRTag();
	}

	public String toString(){
		String output = array.toString() + " := NEWARRAY "+typeIR+" "+ size +";\n";
		return output;
	}
}