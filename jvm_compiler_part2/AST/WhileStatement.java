package AST;
import Semantic.*;
import Type.*;

public class WhileStatement extends Statement {

    public Expression expr;
    public Block block;
    public int line;

    public WhileStatement(Expression e, Block b, int l) {
        this.expr = e;
        this.block = b;
        line = l;
	}

    public void accept(Visitor v) {
            v.visit(this);
	}

    public int getLine() {
        return line;
    }
    
	public String typeOfStatement(){
        return "WhileStatement";
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
