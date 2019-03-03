package AST;
import Semantic.*;
import Type.*;

public class PrintStatement extends Statement{

	public Expression expr;
    public int line;

    public PrintStatement (Expression e, int l) {
    	this.expr = e;    
        line=l;
	}

	public String typeOfStatement(){
		return "PrintStatement";
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
