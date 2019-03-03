package Type;
import AST.*;

public class FloatType extends Type {

    public FloatType () {
	}

    public String toString() {
            return "float";
	}

    public boolean equals (Object o) {
        if (o instanceof FloatType)
			return true;
		else
			return false;
	}

	//TODO
	public String toShortString() {
	 	return "f";
	}

    public void accept (Visitor v) {
            v.visit(this);
	}
}