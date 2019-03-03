package Semantic;

import AST.*;
import Type.*;

public interface TypeVisitor
{
	public Type visit (ArrayType a) throws SemanticException;
	public Type visit (FormalParameterList p) throws SemanticException;
	public Type visit (Function f) throws SemanticException;
	public Type visit (FunctionBody f) throws SemanticException;
	public Type visit (FunctionDeclaration f) throws SemanticException;
	public Type visit (Program p) throws SemanticException;
	public Type visit (Type t) throws SemanticException;
	public Type visit (SimpleType t) throws SemanticException;
	public Type visit (VariableDeclaration v) throws SemanticException;
	public Type visit (FormalParameter c) throws SemanticException;
	public Type visit (VariableDeclarationList c) throws SemanticException;
	public Type visit (Statement c) throws SemanticException;
	public Type visit (StatementList c) throws SemanticException;
	public Type visit (Identifier c) throws SemanticException;
	public Type visit (Expression c) throws SemanticException;
	public Type visit (IfStatement i) throws SemanticException;
	public Type visit (ExpressionList c) throws SemanticException;
	public Type visit (EmptyStatement s) throws SemanticException;
	public Type visit (ExpressionStatement s) throws SemanticException;
	public Type visit (WhileStatement w) throws SemanticException;
	public Type visit (PrintLnStatement pl) throws SemanticException;
	public Type visit (PrintStatement p) throws SemanticException;
	public Type visit (ReturnStatement r) throws SemanticException;
	public Type visit (AssignmentStatement a) throws SemanticException;
	public Type visit (ArrayAssignment a) throws SemanticException; 
	public Type visit (IntegerLiteral i) throws SemanticException;
	public Type visit (StringLiteral s) throws SemanticException;
	public Type visit (ArrayReference a) throws SemanticException;
	public Type visit (CharacterLiteral c) throws SemanticException;
	public Type visit (FloatLiteral f) throws SemanticException;
	public Type visit (BooleanLiteral b) throws SemanticException;  
	public Type visit (Block b) throws SemanticException;
	public Type visit (ParenExpression e) throws SemanticException;
	public Type visit (FunctionCall f) throws SemanticException;
	public Type visit (EqualityExpression e) throws SemanticException;
	public Type visit (LessThanExpression e) throws SemanticException;
	public Type visit (AddExpression e) throws SemanticException;
	public Type visit (SubstractExpression e) throws SemanticException;
	public Type visit (MultExpression e) throws SemanticException;
}
