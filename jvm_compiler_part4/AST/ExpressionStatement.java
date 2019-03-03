package AST;
import Type.*;
import Semantic.*;
import IntRep.*;

public class ExpressionStatement extends Statement{

	public Expression expr;

 	public ExpressionStatement(Expression e) {
    	this.expr = e;
	}

	public String typeOfStatement(){
		return "ExpressionStatement";
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