package AST;
import Type.*;

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
}