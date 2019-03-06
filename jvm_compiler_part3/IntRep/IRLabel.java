package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRLabel extends IRInstruction{
	public static int lableCount = 0;
	public int labelNumber;

	public IRLabel() {
		labelNumber = lableCount++;
	}

	public String toString(){
		String labelOutput = "L" + labelNumber;
		return labelOutput;
	}

}