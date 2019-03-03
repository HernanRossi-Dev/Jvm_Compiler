package AST;
import Semantic.*;
import Type.*;

public class PrintLnStatement extends Statement{
       
    public Expression expr;
    public int line;

    public PrintLnStatement(Expression e, int l) {
        expr = e;
        line = l;
	}

	public String typeOfStatement(){
		return "PrintLnStatement";
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
