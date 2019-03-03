package AST;
import java.util.*;

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

    public int size () {
        return this.listOfVariables.size();
    }

    public void accept (Visitor v) {
        v.visit(this);
    }
}