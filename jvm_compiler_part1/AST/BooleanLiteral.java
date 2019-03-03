package AST;

public class BooleanLiteral extends Expression {
    
    public boolean value;
        
    public BooleanLiteral (String newValue) {
        if(newValue.equals("true")){
            this.value = true;
        } else if(newValue.equals("false")){
            this.value = false;
        } else {
            System.out.println("Error parsing program, incorrect boolean declaration");
        }
    }

    public String typeOfExpression(){
        return "BooleanLiteral";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
    }
}