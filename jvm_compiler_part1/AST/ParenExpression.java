package AST;

public class ParenExpression extends Expression {
    
    public Expression expr;
        
    public ParenExpression (Expression indexExpr ) {
        this.expr = indexExpr;
    }

	public String typeOfExpression(){
        return "ParenExpression";
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}