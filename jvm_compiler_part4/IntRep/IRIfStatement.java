package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRIfStatement extends IRInstruction{
	public boolean condition;
	public Temp temp;
	public IRLabel irLable;

	public IRIfStatement(Temp t, IRLabel l) {
		temp = t;
		irLable = l;
	}

	public String toString(){
		String output = "IF " + temp.toString() + " GOTO " + irLable.toString() +";\n" ;
		return output;
	}

	public String toStringJVMRep(){
		String output = "iload " + temp.number + "\n";
		output += "ifne " + irLable.toString() + "\n";
		return output;
	}
}