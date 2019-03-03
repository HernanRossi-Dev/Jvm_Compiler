package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class IRUnaryOp extends IRInstruction{
    public enum UnaryOp { Invert }
    public UnaryOp unaryOp;
    public Temp left;
    public Temp right;
    public String typeOfIRExpr; // either I, F, C, U, Z
    public String binOp = "";
    public char typeOfUnaryOp; // N = negate, I : invert = Z!

    public IRUnaryOp(Temp l, Temp r, UnaryOp u) {
        Type addType = l.type.getType();
        typeOfIRExpr = addType.getIRTag();
        left = l;
        right = r;
        unaryOp = u;
    }


    public String toString(){
        if(unaryOp == UnaryOp.Invert){
            binOp = "Z!";
        } else {
            binOp = "-";
        }
        String output =  left.toString() + " := " + binOp +" "+ right.toString() + ";\n";
        return output;
    }

    public String toStringJVMRep(){
        if(unaryOp == UnaryOp.Invert){
            binOp = "Z!";
        } else {
            binOp = "-";
        }
        String output =  "iload "+ right.number +"\n";
        output +=  "ldc 1\n";
        output +=  "ixor\n";
        output +=  "istore "+ left.number+"\n";
        return output;
    }
}
