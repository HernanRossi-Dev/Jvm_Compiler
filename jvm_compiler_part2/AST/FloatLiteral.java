package AST;
import Semantic.*;
import Type.*;

public class FloatLiteral extends Expression {
    
    public float value;
    public int line;
    public int offset;
        
    public FloatLiteral (String newValue, int l, int p) {
        float temp = Float.parseFloat(newValue);
        line = l;
        value = temp;
        offset = p;
    }


    public void accept (Visitor v) {
        v.visit(this);
    }

    public String typeOfExpression(){
        return "FloatLiteral";
    }
    
    public int getLine(){
        return line;
    }

    public int getOffset() {
        return offset;
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
