package AST;

public class SubstractExpression extends Expression {
    
    public Expression exprOne;
    public Expression exprTwo;
        
    public SubstractExpression (Expression e1, Expression e2) {
        this.exprOne = e1;
        this.exprTwo = e2;
    }

    public String typeOfExpression(){
        return "SubstractExpression";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
    }
}