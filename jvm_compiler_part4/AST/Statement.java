package AST;
import Type.*;
import Semantic.*;
import IntRep.*;

public class Statement extends ASTNode{

	public Expression expr;

 	public Statement(Expression e) {
    	this.expr = e;
	}

	public Statement () {
    	this.expr = null;
	}

	public String typeOfStatement(){
		return "baseStatement";
	}

    public void accept (Visitor v) {
        v.visit(this);
	}
	
	public Type accept (TypeVisitor v) {
        Type result =null;
        try{
            result = v.visit(this);
        } catch(SemanticException e){ 
            e.printStackTrace();
            System.exit(0);
        }
        return result;
    }

    public Temp accept(TempVisitor v){
        return v.visit(this);
    }
}