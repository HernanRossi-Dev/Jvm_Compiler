package AST;

public class AddExpression extends Expression {
    
    public Expression exprOne;
    public Expression exprTwo;
        
    public AddExpression (Expression e1, Expression e2) {
        this.exprOne = e1;
        this.exprTwo = e2;
    }

     public String typeOfExpression(){
        return "AddExpression";
    }
     
    public void accept (Visitor v) {
        v.visit(this);
    }
}