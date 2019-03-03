package AST;

import Type.*;

public abstract class ASTNode
{
	 public abstract void accept (Visitor v );
}
