package AST;
import Semantic.*;
import Type.*;

public class IfStatement extends Statement {

    public Expression expr;
    public Block block1;
    public Block block2;
    public int line;

    public IfStatement(Expression e, Block b1, int l) {
        this.expr = e;
        this.block1 = b1;
        this.block2 = null;
        line = l;
    }

    public IfStatement(Expression e, Block b1, Block b2, int l) {
        this.expr = e;
        this.block1 = b1;
        this.block2 = b2;
        line = l;
    }

    public int getLine() {
        return line;
    }

    public String typeOfStatement() {
        return "IfStatement";
    }

    public void accept(Visitor v) {
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
