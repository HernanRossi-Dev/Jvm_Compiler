package AST;
import Semantic.*;
import Type.*;

public class Function extends ASTNode {

    public FunctionDeclaration funcDecl;
    public FunctionBody funcBody;
    
    public Function (FunctionDeclaration fd, FunctionBody fb) {
        this.funcDecl = fd;
        this.funcBody = fb;
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