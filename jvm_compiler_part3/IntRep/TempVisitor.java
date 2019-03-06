package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public interface TempVisitor 
{
    public Temp visit (ArrayType a);
	public Temp visit (FormalParameterList p);
	public Temp visit (Function f);
	public Temp visit (FunctionBody f);
	public Temp visit (FunctionDeclaration f);
	public Temp visit (Program p);
	public Temp visit (Type t);
	public Temp visit (SimpleType t);
	public Temp visit (VariableDeclaration v);
	public Temp visit (FormalParameter c);
	public Temp visit (VariableDeclarationList c);
	public Temp visit (Statement c);
	public Temp visit (StatementList c);
	public Temp visit (Identifier c);
	public Temp visit (Expression c);
	public Temp visit (IfStatement i);
	public Temp visit (ExpressionList c);
	public Temp visit (EmptyStatement s);
	public Temp visit (ExpressionStatement s);
	public Temp visit (WhileStatement w);
	public Temp visit (PrintLnStatement pl);
	public Temp visit (PrintStatement p);
	public Temp visit (ReturnStatement r);
	public Temp visit (AssignmentStatement a);
	public Temp visit (ArrayAssignment a); 
	public Temp visit (IntegerLiteral i);
	public Temp visit (ArrayReference a);
	public Temp visit (CharacterLiteral c);
	public Temp visit (FloatLiteral f);
	public Temp visit (BooleanLiteral b);  
	public Temp visit (StringLiteral b); 
	public Temp visit (Block b);
	public Temp visit (ParenExpression e);
	public Temp visit (FunctionCall f);
	public Temp visit (EqualityExpression e);
	public Temp visit (LessThanExpression e);
	public Temp visit (AddExpression e);
	public Temp visit (SubstractExpression e);
	public Temp visit (MultExpression e);
	public void setOutputFileName(String name);
}