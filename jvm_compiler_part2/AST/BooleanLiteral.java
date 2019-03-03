package AST;
import Semantic.*;
import Type.*;

public class BooleanLiteral extends Expression {
    
    public boolean value;
    public int line;
    public int offset;
        
    public BooleanLiteral (String newValue, int l, int p) {
        line = l;
        offset = p;
        if(newValue.equals("true")){
            this.value = true;
        } else if(newValue.equals("false")){
            this.value = false;
        } else {
            System.out.println("Error parsing program, incorrect boolean declaration");
        }
    }

    public String typeOfExpression(){
        return "BooleanLiteral";
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
