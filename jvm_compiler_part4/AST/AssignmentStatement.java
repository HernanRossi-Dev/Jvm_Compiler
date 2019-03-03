package AST;
import Semantic.*;
import Type.*;
import IntRep.*;

public class AssignmentStatement extends Statement{

	public Identifier id;
	public Expression expr;

    public AssignmentStatement(Identifier idNew, Expression e) {
        expr = e;
        id = idNew;
    }

	public String typeOfStatement(){
		return "AssignmentStatement";
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

    public Temp accept(TempVisitor v){
       return v.visit(this);
    }
}
