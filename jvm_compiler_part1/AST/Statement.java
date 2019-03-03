package AST;
import Type.*;

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
}