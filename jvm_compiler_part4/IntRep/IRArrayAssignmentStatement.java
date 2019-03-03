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
	public String toStringJVMRep(){
		int tempLeftNumber = leftTemp.number;
		int tempRightNumber = rightTemp.number;
		int indexTempNumber = indexTemp.number;
		String output =  "aload " + tempLeftNumber;
		output +=  "\niload " + indexTempNumber + "\n";

		Type tempCurrentType = leftTemp.type;
		if(tempCurrentType.getType().equals(new StringType(0,0))){
			output +=  "aload " + rightTemp.number+ "\naastore\n";
		}else if(tempCurrentType.getType().equals(new FloatType(0,0))){
			output +=  "fload " + rightTemp.number+ "\nfastore\n";
		}else if(tempCurrentType.getType().equals(new CharType(0,0))){
			output +=  "iload " + rightTemp.number+ "\ncastore\n";
		}
		else if(tempCurrentType.getType().equals(new BooleanType(0,0))){
			output +=  "iload " + rightTemp.number+ "\nbastore\n";
		}
		else{
			output +=  "iload " + rightTemp.number+ "\niastore\n";
		}

		return output;
	}
}