package AST;

public class MultExpression extends Expression {
    
    public Expression exprOne;
    public Expression exprTwo;
        
    public MultExpression (Expression e1, Expression e2) {
        this.exprOne = e1;
        this.exprTwo = e2;
    }

    public String typeOfExpression(){
        return "MultExpression";
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}