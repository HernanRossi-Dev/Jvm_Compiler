package AST;

public class CharacterLiteral extends Expression {
    
    public char value;
        
    public CharacterLiteral (String newValue) {
        // Char cannot be empty it will cause an error
        if( newValue.length() > 2 ){
            char temp = newValue.charAt(1);
        } else{
            System.out.println("Parser error, incorrect character literal declaration.");
        }
        char temp = newValue.charAt(1);
        this.value = temp;
    }

    public String typeOfExpression(){
        return "CharacterLiteral";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
    }
}