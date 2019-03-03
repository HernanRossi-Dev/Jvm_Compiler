package AST;
import Semantic.*;
import Type.*;

public class CharacterLiteral extends Expression {
    
    public char value;
    public int line;
    public int offset;
        
    public CharacterLiteral (String newValue, int l, int p) {
        line =l;
        offset = p;
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
    
    public int getLine(){
        return line;
    }

    public int getOffset() {
        return offset;
    }
    
    public Type accept (TypeVisitor v) {
        Type result =null;
        try{
            result = v.visit(this);
        } catch(SemanticException e){ 
            System.out.println(e);
            System.exit(0);
        }
        return result;
    }
}
