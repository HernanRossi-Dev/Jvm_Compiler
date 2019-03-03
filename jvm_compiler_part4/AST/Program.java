package AST;

import Semantic.*;
import java.util.*;
import Type.*;
import IntRep.*;

public class Program extends ASTNode{
    
    public ArrayList<Function> functionList;

    public void Program () {
        functionList = new ArrayList<Function>();
    }

    public void add (Function f) {
       
        if(functionList==null){
            functionList = new ArrayList<Function>();
            functionList.add(f);
        } else {
            functionList.add(f);
        }  
    }

    public Function elementAt (int index) {
        return (Function)functionList.remove(index);
    }

    public int size () {
        return functionList.size();
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
