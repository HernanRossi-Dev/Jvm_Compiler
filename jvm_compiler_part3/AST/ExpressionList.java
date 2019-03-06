package AST;

import java.util.*;
import Semantic.*;
import Type.*;
import IntRep.*;

public class ExpressionList {

	public ArrayList<Expression> eList;
    //public Expression expr;

    public void ExpressionList () {
		this.eList = new ArrayList<Expression>();
        //this.expr = null;
	}

    public void addFirstExpression(Expression e){
        //this.expr = e;
        if(this.eList == null) {
            this.eList = new ArrayList<Expression>();
        }
        this.eList.add(e);
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

    public Expression getAt(int i) {
         if(this.eList == null){return new Expression();}
        return (Expression)eList.get(i);
    }

    public int size () {
        if(this.eList == null){return 0;}
        return eList.size();
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
