package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRGoto extends IRInstruction{
	public IRLabel irLable;

	public IRGoto(IRLabel i) {
        irLable = i;
	}

	public String toString(){
		String output = "GOTO " + irLable.toString() + ";\n";
		return output;
	}
}