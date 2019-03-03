package AST;
import Type.*;
import java.util.*;

// Called FormalParameters in grammar
public class FormalParameterList extends ASTNode{
    public Type type;
    public Identifier id;
    public ArrayList<FormalParameter> formalParameterList;
    public boolean noParams = true;

    public FormalParameterList () {
        this.formalParameterList = new ArrayList<FormalParameter>();
    }

    public void addElement (FormalParameter f) {
        this.formalParameterList.add(f);
    }

    public void addType (Type t){
        this.type = t;
    }

    public void addID (Identifier i) {
        this.id = i;
    }

    public void setEmpty(boolean b){
        this.noParams = b;
    }

    public FormalParameter elementAt (int index) {
        return (FormalParameter)this.formalParameterList.remove(index);
    }

    public int size () {
        return this.formalParameterList.size();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}