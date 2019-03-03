package AST;
import Semantic.*;
import Type.*;

public class MultExpression extends Expression {
    
    public Expression exprOne;
    public Expression exprTwo;
    public int line;
    public int offset;
        
    public MultExpression (Expression e1, Expression e2, int l, int p) {
        exprOne = e1;
        exprTwo = e2;
        line = l;
        offset = p;
    }

    public String typeOfExpression(){
        return "MultExpression";
    }

    public int getOffset(){
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
