package AST;
import Semantic.*;
import Type.*;
import IntRep.*;

public class ParenExpression extends Expression {
    
    public Expression expr;
    public int line;
        
    public ParenExpression (Expression indexExpr, int l) {
        this.expr = indexExpr;
        line = l;
    }

	public String typeOfExpression(){
        return "ParenExpression";
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

    public Temp accept(TempVisitor v){
        return v.visit(this);
    }
}
