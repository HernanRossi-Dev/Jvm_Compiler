package AST;

public class Function extends ASTNode {

    FunctionDeclaration funcDecl;
    FunctionBody funcBody;
    
    public Function (FunctionDeclaration fd, FunctionBody fb) {
        this.funcDecl = fd;
        this.funcBody = fb;
	}

    public void accept (Visitor v) {
        v.visit(this);
	}
}