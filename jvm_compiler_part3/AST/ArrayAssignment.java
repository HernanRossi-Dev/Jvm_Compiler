package AST;
import Semantic.*;
import Type.*;
import IntRep.*;

public class ArrayAssignment extends Statement{

	public Identifier id;
	public Expression index;
    public Expression expr;
    public int indexOffset;
    public int line;

    public ArrayAssignment(Identifier i, Expression e1, Expression e2, int l, int p) {
        index = e1;
        expr = e2;
        id = i;
        indexOffset = p;
        line =l;
	}

    public String typeOfStatement(){
        return "ArrayAssignment";
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
