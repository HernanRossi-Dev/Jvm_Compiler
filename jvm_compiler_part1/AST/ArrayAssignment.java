package AST;

public class ArrayAssignment extends Statement{

	public Identifier id;
	public Expression index;
    public Expression expr;

    public ArrayAssignment(Identifier id, Expression e1, Expression e2) {
        this.index = e1;
        this.expr = e2;
        this.id = id;
	}

    public String typeOfStatement(){
        return "ArrayAssignment";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
	}
}