package Type;
import AST.*;
// Not sure if this is how this type should be set up
public class ArrayType extends Type 
{

	public Type type;
	public int size;

    public ArrayType (Type type, String size) 
    {
    	this.size = Integer.parseInt(size);
    	this.type = type;
	}

	 public boolean equals (Object o) {
        if (o instanceof ArrayType)
			return true;
		else
			return false;
	}

	public Type getType(){
		return this.type;
	}

	public int getSize(){
		return this.size;
	}
	//TODO
	 public String toString() {
	 	return "array";
	 }

    public void accept (Visitor v) {
            v.visit(this);
	}
}