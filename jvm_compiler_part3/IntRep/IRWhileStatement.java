package IntRep;
import AST.*;
import Type.*;
import Semantic.*;

public class IRWhileStatement extends IRInstruction{


    public Temp temp;
    public IRLabel irLable;


    public IRWhileStatement(Temp t, IRLabel l) {
        temp = t;
        irLable = l;

    }

    public String toString(){
        String output = "IF " + temp.toString() + " GOTO " + irLable.toString() +";\n" ;
        return output;
    }


}
