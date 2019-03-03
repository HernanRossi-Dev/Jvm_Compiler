package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRLabel extends IRInstruction{
	public static int lableCount = 0;
	public static int jvmLabelsUsed =0;
	public int labelNumber;

	public IRLabel() {
		labelNumber = lableCount++;
	}
	public IRLabel(boolean dontincrement) {

	}

	public String toString(){
		String labelOutput = "L" + labelNumber;
		return labelOutput;
	}

	public String toStringJVMRep(){
		String output =  "L"+labelNumber+":\n";
		return output;
	}

}