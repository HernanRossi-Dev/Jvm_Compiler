package Type;
import AST.*;

public class CharType extends Type {
  
    public CharType () {
	}
    
    public String toString() {
            return "char";
	}
    
    public boolean equals (Object o) {
        if (o instanceof CharType)
			return true;
		else
			return false;
	}

	//TODO
	public String toShortString() {
	 	return "c";
	}
    
    public void accept (Visitor v) {
            v.visit(this);
	}
}