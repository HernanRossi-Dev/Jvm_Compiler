package AST;

import java.util.*;
import Semantic.*;
import Type.*;

public class FunctionCall extends Expression{

	public Identifier id;
    public ExpressionList exprList;
    public int line;
    public int offset;

    public FunctionCall(Identifier i, ExpressionList expl, int l) {
        id = i;
        exprList = expl;
        line = l;
        offset = i.getOffset();
	}

    public String typeOfExpression(){
        return "FunctionCall";
    }

    public int getLine(){
        return line;
    }
    
    public int getOffset() {
        return offset;
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
