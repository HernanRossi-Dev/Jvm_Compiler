package AST;

import java.util.Vector;

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
}