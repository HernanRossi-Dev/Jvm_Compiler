package AST;
import Type.*;

public class EmptyStatement extends Statement{

	public EmptyStatement () {
	}

	public String typeOfStatement(){
		return "EmptyStatement";
	}

    public void accept (Visitor v) {
        v.visit(this);
	}
}