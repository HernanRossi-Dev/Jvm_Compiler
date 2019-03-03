package AST;

public class IntegerLiteral extends Expression {
    
    public int value;
        
    public IntegerLiteral (int newValue) {
        this.value = newValue;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String typeOfExpression(){
        return "IntegerLiteral";
    }
}