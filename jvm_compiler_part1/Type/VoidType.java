package Type;
import AST.*;

public class VoidType extends Type {

    public VoidType () { }

    public String toString() {
            return "void";
	}
	
    public boolean equals (Object o) {
            if (o instanceof VoidType)
				return true;
			else
				return false;
	}

	//TODO
	public String toShortString() {
	 	return "v";
	}

    public void accept (Visitor v) {
            v.visit(this);
	}
}