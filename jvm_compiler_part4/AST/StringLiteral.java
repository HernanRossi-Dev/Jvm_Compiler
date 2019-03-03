package AST;

import Semantic.*;
import Type.*;
import IntRep.*;

public class StringLiteral extends Expression {
    
    public String value;
    public int line;
    public int offset;
        
    public StringLiteral (String newValue, int l, int p) {
        //string the parens
        line = l;
        String noParens = newValue.replaceAll("\"", "");
        value = noParens;
        offset = p;
    }

    public String typeOfExpression(){
        return "StringLiteral";
    }
    
    public void accept (Visitor v) {
        v.visit(this);
    }
    
    public int getLine(){
        return line;
    }

    public int getOffset() {
        return offset;
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
