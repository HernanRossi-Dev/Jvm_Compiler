package AST;
import Semantic.*;
import Type.*;

public class IntegerLiteral extends Expression {
    
    public int value;
    public int line;
    public int offset;
        
    public IntegerLiteral (int newValue, int l, int p) {
        line = l;
        value = newValue;
        offset = p;
    }

    public int getLine(){
        return line;
    }

    public int getOffset() {
        return offset;
    }


    public void accept (Visitor v) {
        v.visit(this);
    }

    public String typeOfExpression(){
        return "IntegerLiteral";
    }
    
    public Type accept (TypeVisitor v) {
        Type result =null;
        try{
            result = v.visit(this);
        } catch(SemanticException e){ 
            System.out.println(e);
            System.exit(0);
        }
        return result;
    }
}
