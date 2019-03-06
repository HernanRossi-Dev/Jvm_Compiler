package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRAssignmentStatement extends IRInstruction{

	public Temp leftTemp;
	public Temp rightTemp;


	public IRAssignmentStatement(Temp l, Temp r) {
		leftTemp = l;
		rightTemp = r;
	}

	public String toString(){
		String output = leftTemp.toString() + " := " + rightTemp.toString() + ";\n" ;
		return output;
	}
}