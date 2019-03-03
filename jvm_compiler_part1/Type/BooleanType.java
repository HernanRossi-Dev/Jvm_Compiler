package Type;
import AST.*;

public class BooleanType extends Type {

    public BooleanType () {
	}

    public String toString() {
            return "boolean";
	}

    public boolean equals (Object o) {
        if (o instanceof BooleanType)
			return true;
		else
			return false;
	}

	//TODO
	 public String toShortString() {
	 	return "b";
	 }

    public void accept (Visitor v) {
            v.visit(this);
	}
}