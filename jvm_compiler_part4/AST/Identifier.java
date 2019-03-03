package AST;
import Semantic.*;
import Type.*;
import IntRep.*;

public class Identifier extends Expression {


    public int line;
    public int offset;
    public String idName ="";
        
    public Identifier (String newID, int l, int p) {
        idName = newID;
        line = l;
        offset = p;
    }

    public int getLine() {
        return line;
    }

    public int getOffset(){
        return offset;
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

    public String typeOfExpression(){
        return "Identifier";
    }

    public Temp accept(TempVisitor v){
        return v.visit(this);
    }
}