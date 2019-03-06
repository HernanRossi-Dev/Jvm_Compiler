package Type;
import AST.*;
import Semantic.*;
import IntRep.*;

public class ArrayType extends Type 
{
	public Type type;
	public int size;
	public int line;
	public int offset;

    public ArrayType (Type t, String s) 
    {
    	size = Integer.parseInt(s);
    	type = t;
    	line = t.getLine();
    	offset = t.getOffset();
	}

	public int getLine(){
		return line;
	}

	public int getOffset(){
		return offset;
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

	public String toString() {
	 	return "ArrayType";
	}

	public String getIRTag() {
            return "A" + type.getIRTag();
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
	
	public Temp accept(TempVisitor v){
		 return v.visit(this);
	}
}