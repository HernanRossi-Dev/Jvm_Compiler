package AST;
import Type.*;

public class FunctionDeclaration extends ASTNode{
    Type type;
    Identifier id;
    FormalParameterList formalParameterList;
        
    public FunctionDeclaration (Type type, Identifier id, FormalParameterList fpList) {
        this.type = type;
        this.id = id;
        this.formalParameterList = fpList;
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}