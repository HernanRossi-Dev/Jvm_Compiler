package AST;

import java.util.*;
import Semantic.*;
import Type.*;

public class StatementList {
        
    public ArrayList<Statement> listOfStatements;

    public StatementList () {
        listOfStatements = new ArrayList<Statement>();
    }

     public void addStatement (Statement s) {
            listOfStatements.add(s);
    }

     public Statement elementAt (int index) {
        return (Statement)listOfStatements.remove(index);
    }

    public Statement getAt(int index) {
        return (Statement) listOfStatements.get(index);
    }

    public int size () {
        return listOfStatements.size();
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
