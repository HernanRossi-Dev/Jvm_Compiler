package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class ReturnIRStatement extends IRInstruction{

	public Temp returnTemp;

	public ReturnIRStatement(Temp r) {
		returnTemp = r;
	}

	public String toString(){
		String output = null;
		if(returnTemp ==null){
			output = "RETURN;\n";
		}else{
			output = "RETURN " + returnTemp +";\n";
		}

		return output;
	}
}