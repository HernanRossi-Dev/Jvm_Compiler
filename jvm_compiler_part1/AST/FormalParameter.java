package AST;
import Type.*;

// Called MoreFormals in grammar
public class FormalParameter extends ASTNode{
    public Type type;
    public Identifier id;

    public FormalParameter (Type type, Identifier id) {
        this.type = type;
        this.id = id;
	}
 // printing is done through the formalParameters list class
    public void accept (Visitor v) {
        v.visit(this);
	}
}