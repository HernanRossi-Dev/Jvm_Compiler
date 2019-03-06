package IntRep;

import AST.*;
import Type.*;
import Semantic.*;

public class Temp{
    public int number; // The number of the temporary i.e. T0, T1 ...
    public Type type; // Temporaries can have only one type this is explicitly defined on creation of the temporary
    public char tempClass; // U for unknown, P for Parameter, L for Label, T for instruction temporary
    public boolean inUse;
    public String name;


    Temp(int n, Type t, char tc){
        number = n;
        type =t;
        tempClass = tc;
        inUse = true;
    }

    Temp(int n, Type t, char tc, String na){
        number = n;
        type =t;
        tempClass = tc;
        inUse = true;
        name = na;
    }

    public char getTempClass(){
    	return tempClass;
    }

    public String toString() {
    	return "T" + number;
    }
}