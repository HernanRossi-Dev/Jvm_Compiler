package AST;

import Type.*;
import Semantic.*;
import IntRep.*;

public abstract class ASTNode
{
	 public abstract void accept (Visitor v );
	 public abstract Type accept (TypeVisitor v );
	 public abstract Temp accept(TempVisitor v);
}
