package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRArrayAssignmentStatement extends IRInstruction{

	public Temp leftTemp;
	public Temp rightTemp;
	public Temp indexTemp;


	public IRArrayAssignmentStatement(Temp l, Temp r, Temp i) {
		leftTemp = l;
		rightTemp = r;
		indexTemp = i;
	}

	public String toString(){
		String output = leftTemp.toString() +"[" + indexTemp.toString() +"] := " + rightTemp.toString() + ";\n" ;
		return output;
	}
}