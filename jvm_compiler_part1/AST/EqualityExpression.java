package AST;

public class EqualityExpression extends Expression {
    
    public Expression exprOne;
    public Expression exprTwo;
        
    public EqualityExpression (Expression e1, Expression e2) {
        this.exprOne = e1;
        this.exprTwo = e2;
    }

    public String typeOfExpression(){
        return "EqualityExpression";
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}