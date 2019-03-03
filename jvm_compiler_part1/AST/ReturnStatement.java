package AST;

public class ReturnStatement extends Statement{

	public Expression expr;

	public ReturnStatement () {
        this.expr = new Expression();;
	}

	public void addExpression(Expression e) {
		this.expr = e;
	}
	
	public String typeOfStatement(){
		return "ReturnStatement";
	}
	
    public void accept (Visitor v) {
        v.visit(this);
	}
}