package Type;
import AST.*;
import Semantic.*;
import IntRep.*;

public class FloatType extends Type {
	public int line;
    public int offset;
	
    public FloatType (int l, int p) {
    	line = l;
        offset =p;
	}

    public String toString() {
            return "float";
	}

    public String getIRTag() {
            return "F";
    }

    public int getLine() {
        return line;
    }

    public int getOffset() {
        return offset;
    }
    
    public Type getType() {
        return new FloatType(0,0);
    }

    public boolean equals (Object o) {
        if (o instanceof FloatType)
			return true;
		else
			return false;
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