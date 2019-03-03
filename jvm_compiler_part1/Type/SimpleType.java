package Type;
import AST.*;

// not an array
public class SimpleType extends Type {

	public Type type;

    public SimpleType (Type newType) {
    	this.type = newType;
	}

	public boolean equals (Object o) {
        if (o instanceof SimpleType)
			return true;
		else
			return false;
	}
	//TODO
	public String toString() {
	 	return "sType";
	}

	public Type getType(){
		return this.type;
	}

    public void accept (Visitor v) {
        v.visit(this);
	}
}