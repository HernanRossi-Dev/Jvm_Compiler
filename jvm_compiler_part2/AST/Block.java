package AST;

import java.util.Vector;
import Semantic.*;
import Type.*;

public class Block extends Statement{

	public StatementList statementList;

    public Block() {
	}

	public void addStatementList(StatementList s) {
        this.statementList = s;
    }

    public String typeOfStatement(){
		return "Block";
	}

    public void accept (Visitor v) {
        v.visit(this);
	}
    
	public Type accept (TypeVisitor v) {
        Type result =null;
        try{
            result = v.visit(this);
        } catch(SemanticException e){ 
            e.printStackTrace();
            System.exit(0);
        }
        return result;
    }
}
