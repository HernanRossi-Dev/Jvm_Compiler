package AST;
import Semantic.*;
import Type.*;

public class AddExpression extends Expression {
    
    public Expression exprOne;
    public Expression exprTwo;
    public int line;
    public int offset;
        
    public AddExpression (Expression e1, Expression e2, int l, int p) {
        exprOne = e1;
        exprTwo = e2;
        line = l;
        offset = p;
    }

     public String typeOfExpression(){
        return "AddExpression";
    }
     
    public void accept (Visitor v) {
        v.visit(this);
    }
    
    public int getLine() {
        return line;
    }

    public int getOffset(){
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