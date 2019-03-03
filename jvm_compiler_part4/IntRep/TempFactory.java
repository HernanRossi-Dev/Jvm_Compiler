package IntRep;

import AST.*;
import Type.*;
import Semantic.*;
import java.util.*;

public class TempFactory {

    int MAX_TEMP_NUMBER = 65535;
    public int tempCount = 0;
    public ArrayList<Temp> functionListOfTemporaries; // list off all of the temps in a function
    public Temp[] trackArrayOfTempEntries; // array of all entries in the function
    public enum TempClass { UNKNOWN, PARAMETER, LOCAL, TEMP };

    TempFactory() {
        functionListOfTemporaries = new ArrayList<Temp>();
        trackArrayOfTempEntries = new Temp[MAX_TEMP_NUMBER];
    }

    public Temp getTemp(Type t, char tc){
        if(tempCount == MAX_TEMP_NUMBER){
            System.out.println("Max number of temporaries exceeded, program too large. Exiting.");
            System.exit(0);
        }
        Temp newTemp = new Temp(tempCount, t, tc);
        trackArrayOfTempEntries[tempCount] = newTemp;
        tempCount++;
        functionListOfTemporaries.add(newTemp);
        return newTemp;
    }


    public Temp getTemp(Type t, char tc, String nam){
        Temp newTemp = new Temp(tempCount, t, tc, nam);
        trackArrayOfTempEntries[tempCount] = newTemp;
        tempCount++;
        functionListOfTemporaries.add(newTemp);
        return newTemp;
    }

    public void returnTemp(Temp t) {
        int tempNumber = t.number;
        trackArrayOfTempEntries[tempNumber] = null;
    }

    public boolean isParamOrLocal(Temp t){
        if(t.getTempClass() == 'L' || t.getTempClass() == 'P'  ) {
            return true;
        } else {
            return false;
        }
    }

    public Temp findTemp(String name) {
        for(Temp t : functionListOfTemporaries){
            if(name.equals(t.name)) {
                return t;
            }
        }
        return null;
    }

    public String toString() {
        String temporaryOutputString = "";
        Iterator itr = functionListOfTemporaries.iterator();
        while(itr.hasNext()) {

            Temp curTemp = (Temp)itr.next();
            char  tempClass = curTemp.tempClass;
            if(tempClass == 'P' || tempClass == 'L'){
                String name = curTemp.name;
                temporaryOutputString += "TEMP " + curTemp.number + ":" + curTemp.type.getIRTag() + "   [" + tempClass + "(\"" + name+ "\")];\n";
            } else{
                temporaryOutputString += "TEMP " + curTemp.number + ":" + curTemp.type.getIRTag() + ";\n";
            }

        }

        return temporaryOutputString;
    }

    public String toStringJfile(){
        String temporaryOutputString = "";
        Iterator itr = functionListOfTemporaries.iterator();
        while(itr.hasNext()) {

            Temp curTemp = (Temp)itr.next();
            if(curTemp.tempClass == 'P'){

            } else {
                Type tempCurrentType = curTemp.type;
                if(tempCurrentType.toString().equals("ArrayType")){
                    temporaryOutputString += "aconst_null\nastore " + curTemp.number + "\n";
                } else if (tempCurrentType.getType().equals(new StringType(0, 0))) {
                    temporaryOutputString += "aconst_null\nastore " + curTemp.number + "\n";
                } else if (tempCurrentType.getType().equals(new CharType(0, 0))) {
                    temporaryOutputString += "ldc 0\nistore " + curTemp.number + "\n";
                } else if (tempCurrentType.getType().equals(new BooleanType(0, 0))) {
                    temporaryOutputString += "ldc 0\nistore " + curTemp.number + "\n";
                } else if (tempCurrentType.getType().equals(new FloatType(0, 0))) {
                    temporaryOutputString += "ldc 0.0\nfstore " + curTemp.number + "\n";
                }else if (tempCurrentType.getType().equals(new IntegerType(0, 0))) {
                    temporaryOutputString += "ldc 0\nistore " + curTemp.number + "\n";
                }else {
                    temporaryOutputString += "aconst_null\nastore " + curTemp.number + "\n";
                }
            }

        }

        return temporaryOutputString;

    }
}