package AST;
import Type.*;

public class VariableDeclaration extends ASTNode {
    Type type;
    Identifier id;

    public VariableDeclaration (Type type, Identifier id) {
        this.type = type;
        this.id = id;
	}

    public void accept (Visitor v) {
            v.visit(this);
	}
}