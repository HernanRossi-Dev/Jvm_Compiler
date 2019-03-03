package AST;

public class AssignmentStatement extends Statement{

	public Identifier id;
	public Expression expr;

    public AssignmentStatement(Identifier idNew, Expression e) {
        this.expr = e;
        this.id = idNew;
	}

	public String typeOfStatement(){
		return "AssignmentStatement";
	}

    public void accept (Visitor v) {
        v.visit(this);
	}
}