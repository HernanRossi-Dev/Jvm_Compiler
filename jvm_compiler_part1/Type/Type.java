package Type;
import AST.*;
public abstract class Type {
        public abstract void accept (Visitor v );
        public abstract String toString();
        public abstract boolean equals (Object o);
}