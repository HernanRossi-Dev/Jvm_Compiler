package AST;

public class IfStatement extends Statement {

    public Expression expr;
    public Block block1;
    public Block block2;

    public IfStatement(Expression e, Block b1) {
        this.expr = e;
        this.block1 = b1;
        this.block2 = null;
    }

    public IfStatement(Expression e, Block b1, Block b2) {
        this.expr = e;
        this.block1 = b1;
        this.block2 = b2;
    }

    public String typeOfStatement() {
        return "IfStatement";
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}