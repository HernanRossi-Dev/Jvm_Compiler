package AST;
import Type.*;
import Semantic.*;
import IntRep.*;

public class SubstractExpression extends Expression {
    
    public Expression exprOne;
    public Expression exprTwo;
    public int line;
    public int offset;
        
    public SubstractExpression (Expression e1, Expression e2, int l, int p) {
        exprOne = e1;
        exprTwo = e2;
        line = l;
        offset = p;
    }

    public String typeOfExpression(){
        return "SubstractExpression";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
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

    public Temp accept(TempVisitor v){
       return v.visit(this);
    }
}
