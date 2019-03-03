package AST;

public class ArrayReference extends Expression {
    
    public Identifier id;
    public Expression index;
        
    public ArrayReference (Identifier i, Expression indexExpr ) {
        this.id = i;
        // This will need to get calculated and typechecked
        this.index = indexExpr;
    }

    public String typeOfExpression(){
        return "StringLiteral";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
    }
}