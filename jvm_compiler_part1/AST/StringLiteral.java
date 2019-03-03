package AST;

public class StringLiteral extends Expression {
    
    public String value;
        
    public StringLiteral (String newValue) {
        //string the parens
        String noParens = newValue.replaceAll("\"", "");
        this.value = noParens;
    }

    public String typeOfExpression(){
        return "StringLiteral";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
    }
}