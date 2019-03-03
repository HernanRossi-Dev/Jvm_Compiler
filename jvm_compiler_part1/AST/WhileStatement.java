package AST;

public class WhileStatement extends Statement {

    public Expression expr;
    public Block block;

    public WhileStatement(Expression e, Block b) {
        this.expr = e;
        this.block = b;
	}

    public void accept(Visitor v) {
            v.visit(this);
	}

	public String typeOfExpression(){
        return "StringLiteral";
    }
}