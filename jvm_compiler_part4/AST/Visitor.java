package AST;

import Type.*;

public interface Visitor
{
	public void visit (ArrayType a);
	public void visit (FormalParameterList p);
	public void visit (Function f);
	public void visit (FunctionBody f);
	public void visit (FunctionDeclaration f);
	public void visit (Program p);
	public void visit (Type t);
	public void visit (SimpleType t);
	public void visit (VariableDeclaration v);
	public void visit(FormalParameter c);
	public void visit(VariableDeclarationList c);
	public void visit(Statement c);
	public void visit(StatementList c);
	public void visit(Identifier c);
	public void visit(Expression c);
	public void visit(IfStatement i);
	public void visit(ExpressionList c);
	public void visit(EmptyStatement s);
	public void visit(ExpressionStatement s);
	public void visit(WhileStatement w);
	public void visit(PrintLnStatement pl);
	public void visit(PrintStatement p);
	public void visit(ReturnStatement r);
	public void visit(AssignmentStatement a);
	public void visit(ArrayAssignment a);
	public void visit(IntegerLiteral i);
	public void visit(StringLiteral s);
	public void visit(ArrayReference a);
	public void visit(CharacterLiteral c);
	public void visit(FloatLiteral f);
	public void visit(BooleanLiteral b); 
	public void visit(Block b);
	public void visit(ParenExpression e);
	public void visit(FunctionCall f);
	public void visit(EqualityExpression e);
	public void visit(LessThanExpression e);
	public void visit(AddExpression e);
	public void visit(SubstractExpression e);
	public void visit(MultExpression e);
}
