package AST;

import Type.*;
import Semantic.*;

public abstract class ASTNode
{
	 public abstract void accept (Visitor v );
	 public abstract Type accept (TypeVisitor v );
}
