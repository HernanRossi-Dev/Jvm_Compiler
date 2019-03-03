package AST;

import java.util.*;

public class ExpressionList {

	public ArrayList<Expression> eList;
    public Expression expr;

    public void ExpressionList () {
		this.eList = new ArrayList<Expression>();
        this.expr = null;
	}

    public void addFirstExpression(Expression e){
        this.expr = e;
        if(this.eList == null) {
            this.eList = new ArrayList<Expression>();
        }
    }

    public void addToExpressionList (Expression e) {    
        if(this.eList == null) {
            this.eList = new ArrayList<Expression>();
            this.eList.add(e);
        } else {
            this.eList.add(e);
        }
    }

     public Expression elementAt (int index) {
        if(this.eList == null){return new Expression();}
        return (Expression)eList.remove(index);
    }

    public int size () {
        if(this.eList == null){return 0;}
        return eList.size();
    }

    public void accept (Visitor v) {
        v.visit(this);
	}
}