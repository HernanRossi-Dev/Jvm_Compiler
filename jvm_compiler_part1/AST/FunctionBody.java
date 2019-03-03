package AST;

public class FunctionBody extends ASTNode {
	
    public VariableDeclarationList variableDeclarationList;
    public StatementList statementList;

    public void FunctionBody () {
           
	}
	
	public void addVariableList(VariableDeclarationList v) {
		this.variableDeclarationList = v;
	}

	public void addStatementList(StatementList s) {
		this.statementList = s;
	}

    public void accept (Visitor v) {
            v.visit(this);
	}
}