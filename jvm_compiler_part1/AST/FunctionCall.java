package AST;

import java.util.*;

public class FunctionCall extends Expression{

	public Identifier id;
    public ExpressionList exprList;

    public FunctionCall(Identifier i, ExpressionList expl) {
        this.id = i;
        this.exprList = expl;
	}

    public String typeOfExpression(){
        return "FunctionCall";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
	}
}