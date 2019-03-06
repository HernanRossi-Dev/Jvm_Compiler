package AST;
import Type.*;
import java.util.*;
import Semantic.*;
import IntRep.*;

// Called FormalParameters in grammar
public class FormalParameterList extends ASTNode{
    private Type type;
    private Identifier id;
    public ArrayList<FormalParameter> formalParameterList;
    public boolean noParams = true;

    public FormalParameterList () {
        formalParameterList = new ArrayList<FormalParameter>();
    }

    public void addElement (FormalParameter f) {
        formalParameterList.add(f);
    }

    public void addType (Type t){
        this.type = t;
    }

    public void addID (Identifier i) {
        this.id = i;
        formalParameterList = new ArrayList<FormalParameter>();
        FormalParameter firstParam = new FormalParameter(type, id);
        formalParameterList.add(firstParam);
    }

    public void setEmpty(boolean b){
        this.noParams = b;
    }

    public FormalParameter elementAt(int index) {
        return (FormalParameter)this.formalParameterList.remove(index);
    }

    public FormalParameter getAt(int index) {
        return (FormalParameter)this.formalParameterList.get(index);
    }

    public int size () {
        return this.formalParameterList.size();
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