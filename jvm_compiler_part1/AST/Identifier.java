package AST;

public class Identifier extends Expression {
    
    public String idName;
        
    public Identifier (String newID) {
        this.idName = newID;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }

    public String typeOfExpression(){
        return "Identifier";
    }
}