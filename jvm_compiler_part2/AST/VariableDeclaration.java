package AST;
import Type.*;
import Semantic.*;

public class VariableDeclaration extends ASTNode {
    public Type type;
    public Identifier id;

    public VariableDeclaration (Type t, Identifier i) {
        this.type = t;
        this.id = i;
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