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

	public String toStringJVMRep(){
		String output = "getstatic java/lang/System/out Ljava/io/PrintStream;\n";
		if(outputTemp ==null){
			output = "return\n";
		}else {
			Type tempCurrentType = outputTemp.type;
			if (tempCurrentType.getType().equals(new StringType(0, 0))) {
				output += "aload " + outputTemp.number + "\n";
				output += "invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n";
			} else if (tempCurrentType.getType().equals(new FloatType(0, 0))) {
				output += "fload " + outputTemp.number + "\n";
				output += "invokevirtual java/io/PrintStream/println(F)V\n";
			}else if (tempCurrentType.getType().equals(new CharType(0, 0))) {
				output += "iload " + outputTemp.number + "\n";
				output += "invokevirtual java/io/PrintStream/println(C)V\n";
			} else if (tempCurrentType.getType().equals(new BooleanType(0, 0))) {
				output += "iload " + outputTemp.number + "\n";
				output += "invokevirtual java/io/PrintStream/println(Z)V\n";
			} else {
				output += "iload " + outputTemp.number + "\n";
				output += "invokevirtual java/io/PrintStream/println(I)V\n";
			}
		}
		return output;
	}
}