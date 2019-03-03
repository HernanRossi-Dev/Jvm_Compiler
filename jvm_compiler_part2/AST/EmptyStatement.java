package AST;
import Type.*;
import Semantic.*;

public class EmptyStatement extends Statement{

	public EmptyStatement () {
	}

	public String typeOfStatement(){
		return "EmptyStatement";
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