package AST;
import Semantic.*;
import Type.*;

public class ReturnStatement extends Statement{

	public Expression expr;
	public int line;
	public int offset;

	public ReturnStatement () {
        expr = null;

	}

	public void addExpression(Expression e, int l, int p) {
		line =l;
		expr = e;
		offset = p;
	}

	public void addLineOffset(int l, int p) {
		line = l;
		offset =p;
	}
	
	public int getOffset(){
		return offset;
	}

	public String typeOfStatement(){
		return "ReturnStatement";
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
