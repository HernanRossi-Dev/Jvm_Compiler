package AST;
import Type.*;
import Semantic.*;
import IntRep.*;

//TODO
public class Expression extends ASTNode{

	public Expression expr;
    public int line =0;
    public int offset =0;

    public void Expression () {
	}

	public void Expression (Expression e) {
		this.expr = e;
	}

	public String typeOfExpression(){
        return "Expression";
    }

    public void accept (Visitor v) {
        v.visit(this);
	}
	
    public int getLine(){
        return line;
    }

    public int getOffset() {
        return offset;
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

    public Temp accept(TempVisitor v){
        return v.visit(this);
    }
}