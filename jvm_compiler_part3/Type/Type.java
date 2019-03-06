package Type;
import AST.*;
import Semantic.*;
public abstract class Type {
    public abstract void accept (Visitor v );
    public abstract Type accept (TypeVisitor v);
    public abstract String toString();
    public abstract boolean equals (Object o);
    public abstract int getLine();
    public abstract int getOffset();
    public abstract Type getType();
    public abstract String getIRTag();
}