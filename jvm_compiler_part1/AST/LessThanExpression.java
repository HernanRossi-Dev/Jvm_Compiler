package AST;

public class LessThanExpression extends Expression {
    
    public Expression exprOne;
    public Expression exprTwo;
        
    public LessThanExpression (Expression e1, Expression e2) {
        this.exprOne = e1;
        this.exprTwo = e2;
    }

    public String typeOfExpression(){
        return "LessThanExpression";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
    }
}