package AST;
import Type.*;

//TODO
public class Expression extends ASTNode{

	public Expression expr;
    public void Expression () {
	}

	public void Expression (Expression e) {
		this.expr = e;
	}

	public String typeOfExpression(){
        return "Expression";
    }

    public void accept (Visitor v) {
        v.visit(this);
	}
}