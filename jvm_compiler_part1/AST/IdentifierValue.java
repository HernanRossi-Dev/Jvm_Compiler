package AST;

public class IdentifierValue extends Expression {
    public Expression value;
        
    public IdentifierValue (Expression e) {
        this.value = e;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String typeOfExpression(){
        return "IdentifierValue";
    }
}