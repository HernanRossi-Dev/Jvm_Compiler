package Type;
import AST.*;
import Semantic.*;
import IntRep.*;

public class BooleanType extends Type {
	public int line;
    public int offset;
    
    public BooleanType (int l, int p) {
        line =l;
        offset = p;   
    }

    public String toString() {
            return "boolean";
	}

    public String getIRTag() {
            return "Z";
    }

    public int getLine() {
        return line;
    }

    public int getOffset() {
        return offset;
    }
    
    public Type getType() {
        return new BooleanType(0,0);
    }

    public boolean equals (Object o) {
        if (o instanceof BooleanType)
			return true;
		else
			return false;
	}

    public int getSize(){
        return 1;
    }
	 public String toShortString() {
	 	return "b";
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