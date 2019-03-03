package AST;
import Semantic.*;
import Type.*;

public class ArrayReference extends Expression {
    
    public Identifier id;
    public Expression index;
        
    public ArrayReference (Identifier i, Expression indexExpr ) {
        id = i;
        index = indexExpr;
    }

    public String typeOfExpression(){
        return "ArrayReference";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
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
