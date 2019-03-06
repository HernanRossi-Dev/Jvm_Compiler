package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRLabelInstruction extends IRInstruction{

    public IRLabel label;

    public IRLabelInstruction(IRLabel l) {
        label = l;
    }

    public String toString(){
        String output =  label.toString() + ":;\n";
        return output;
    }
}
