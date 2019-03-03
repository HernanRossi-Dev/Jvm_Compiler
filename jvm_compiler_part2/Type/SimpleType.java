package Type;
import AST.*;
import Semantic.*;

// not an array
public class SimpleType extends Type {

	public int line;
	public Type type;
	public int offset;

    public SimpleType (Type newType) {
    	line = newType.getLine();
    	offset = newType.getOffset();
    	this.type = newType;
	}

	public int getLine() {
		return line;
	}

	public int getOffset() {
		return offset;
	}

	public boolean equals (Object o) {
        if (o instanceof SimpleType)
			return true;
		else
			return false;
	}

	public String toString() {
	 	return "SimpleType";
	}

	public Type getType(){
		return this.type;
	}

    public void accept (Visitor v) {
        v.visit(this);
	}
	
	public Type accept (TypeVisitor v) {
        Type result =null;
        try{
            result = v.visit(this);
        } catch(SemanticException e){ 
            System.out.println(e);
            System.exit(0);
        }
        return result;
    }
}