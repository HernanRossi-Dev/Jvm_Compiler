package AST;

public class PrintLnStatement extends Statement{
       
    public Expression expr;

    public PrintLnStatement(Expression e) {
        this.expr = e;
	}

	public String typeOfStatement(){
		return "PrintLnStatement";
	}

    public void accept(Visitor v) {
        v.visit(this);
	}
}