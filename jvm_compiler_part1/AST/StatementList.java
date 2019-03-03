package AST;

import java.util.*;

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

    public int size () {
        return listOfStatements.size();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}