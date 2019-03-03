package AST;
import java.util.*;
import Semantic.*;
import Type.*;
import IntRep.*;

public class VariableDeclarationList extends ASTNode{
        
    public ArrayList<VariableDeclaration> listOfVariables;

    public VariableDeclarationList () {
        this.listOfVariables = new  ArrayList<VariableDeclaration>();
    }

    public void addDeclaration (VariableDeclaration v) {
        
        if(this.listOfVariables==null) {
            this.listOfVariables = new ArrayList<VariableDeclaration>();
            this.listOfVariables.add(v);
        } else {
            this.listOfVariables.add(v);
        }
    }

    public VariableDeclaration elementAt (int index) {
        return (VariableDeclaration)this.listOfVariables.remove(index);
    }

    public VariableDeclaration getAt(int index) {
        return (VariableDeclaration)this.listOfVariables.get(index);
    }

    public int size () {
        return this.listOfVariables.size();
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
