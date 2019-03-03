package AST;
import Semantic.*;
import Type.*;

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
