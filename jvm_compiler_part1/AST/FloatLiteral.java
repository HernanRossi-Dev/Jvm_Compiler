package AST;

public class FloatLiteral extends Expression {
    
    public float value;
    public String eType;
        
    public FloatLiteral (String newValue) {
        float temp = Float.parseFloat(newValue);
        this.value = temp;
    }


    public void accept (Visitor v) {
        v.visit(this);
    }

    public String typeOfExpression(){
        return "FloatLiteral";
    }
}