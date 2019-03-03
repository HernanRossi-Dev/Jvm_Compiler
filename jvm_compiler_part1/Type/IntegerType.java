package Type;
import AST.*;

public class IntegerType extends Type {
   
    public IntegerType () {
	}
   
    public String toString() {
            return "int";
	}
    
    public boolean equals (Object o) {
        if (o instanceof IntegerType)
			return true;
		else
			return false;
	}

	//TODO
	public String toShortString() {
		 return "i";
	}

    public void accept (Visitor v) {
        v.visit(this);
	}
}