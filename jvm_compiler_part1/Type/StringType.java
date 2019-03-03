package Type;
import AST.*;

public class StringType extends Type {

    public StringType () {	}

    public String toString() {
            return "string";
	}
    
    public boolean equals (Object o) {
        if (o instanceof StringType)
			return true;
		else
			return false;
	}

	//TODO
	public String toShortString() {
	 	return "s";
	}

    public void accept (Visitor v) {
            v.visit(this);
	}
}