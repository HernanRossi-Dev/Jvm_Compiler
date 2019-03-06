package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRPrintlnStatement extends IRInstruction{

	public Temp outputTemp;
	public Temp holder;
	public String typeOfPrint; // either I, F, C, U, Z

	public IRPrintlnStatement(Temp out, Temp hldr) {
		Type printType = out.type.getType();

		typeOfPrint = printType.getIRTag();
		outputTemp = out;
		holder = hldr;
	}

	public String toString(){
		String output = holder.toString() + " := " + outputTemp.toString() + ";\n" + "PRINTLN" + typeOfPrint +" " + holder.toString() + ";\n" ;
		return output;
	}
}