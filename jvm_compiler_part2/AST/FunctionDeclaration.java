package AST;
import Type.*;
import Semantic.*;

public class FunctionDeclaration extends ASTNode{
    public Type type;
    public Identifier id;
    public FormalParameterList formalParameterList;
        
    public FunctionDeclaration (Type type, Identifier id, FormalParameterList fpList) {
        this.type = type;
        this.id = id;
        this.formalParameterList = fpList;
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