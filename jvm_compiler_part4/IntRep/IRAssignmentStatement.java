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
	public String toStringJVMRep(){
		String output =  "";
		Type tempCurrentType = leftTemp.type;
		if(rightTemp.irTag == null){
			if(tempCurrentType.getType().equals(new StringType(0,0))){
				output =  "aload " + rightTemp.number+ "\nastore " + leftTemp.number+ "\n";
			}else if(tempCurrentType.getType().equals(new FloatType(0,0))){
				output =  "fload " + rightTemp.number+ "\nfstore " + leftTemp.number+ "\n";
			}else{
				output =  "iload " + rightTemp.number+ "\nistore " + leftTemp.number+ "\n";
			}
		} else{
			if(rightTemp.irTag.charAt(0) == 'A'){
				output =  "aload " + rightTemp.number+ "\nastore " + leftTemp.number+ "\n";
			}else if(tempCurrentType.getType().equals(new StringType(0,0))){
				output =  "aload " + rightTemp.number+ "\nastore " + leftTemp.number+ "\n";
			}else if(tempCurrentType.getType().equals(new FloatType(0,0))){
				output =  "fload " + rightTemp.number+ "\nfstore " + leftTemp.number+ "\n";
			}else{
				output =  "iload " + rightTemp.number+ "\nistore " + leftTemp.number+ "\n";
			}
		}


		return output;
	}
}