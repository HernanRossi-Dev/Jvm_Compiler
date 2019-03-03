package IntRep;

import AST.*;
import Type.*;
import Semantic.*;
import java.util.*;
import java.io.*;

public class IntermedRepVisitor implements TempVisitor{
    public TempFactory currentFunctionTempFactory;
    public IRFunctionSignature currentFunctionSignature; //This also hold the tempfactory and instruction list references
    public HashMap<String, IRFunctionSignature> functionsSignaturesMap;
    public ArrayList<Temp> currentFunctionTemporaryList; //first params then locals 
    public String currentFunction;
    public HashMap<String, Type> functionTypeSymbolTable;
    public FunctionDeclaration currentFuncDec;

    public BufferedWriter writer;
    public BufferedWriter writerIR;
    public String outputFileName = "";


    public void setOutputFileName(String name) {
        outputFileName = name;
    }

    public Temp visit (Program p) {
        try{
            functionTypeSymbolTable = new HashMap<String, Type>();
            /** Old part to print IR File**/
            writerIR = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(outputFileName+ ".ir"), "utf-8"));
            functionsSignaturesMap = new HashMap<String, IRFunctionSignature>();
            writerIR.write("PROG " + outputFileName + "\n\n");


            /**
             * New part to print a .j file instead
             */
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFileName+ ".j"), "utf-8"));
            functionsSignaturesMap = new HashMap<String, IRFunctionSignature>();
            writer.write(".class public " + outputFileName + "\n.super java/lang/Object\n\n");

            if (p != null) {
                int numberOfFunction = p.size();
                ArrayList<Function> fList = p.functionList;
                for(int i =0; i < numberOfFunction; i++) {
                    Function funct = fList.get(i);
                    FunctionDeclaration functDecl = funct.funcDecl;
                    currentFunction = functDecl.id.idName;
                    Type currentFunctionReturnType = functDecl.type;
                    functionTypeSymbolTable.put(currentFunction, currentFunctionReturnType);
                }
            }
            if (p != null) {

                int numberOfFunction = p.size();
                ArrayList<Function> fList = p.functionList;
                for(int i =0; i < numberOfFunction; i++) {
                    IRLabel temp = new IRLabel();
                    temp.lableCount = 0;
                    Function funct = fList.get(i);
                    FunctionDeclaration functDecl = funct.funcDecl;

                    currentFunction = functDecl.id.idName;
                    currentFuncDec = functDecl;
                    currentFunctionTempFactory = new TempFactory();
                    currentFunctionSignature = new IRFunctionSignature(currentFunction, funct);
                    currentFunctionSignature.programName = outputFileName;
                    funct.accept(this);
                    currentFunctionSignature.tempFactory = currentFunctionTempFactory;
                    functionsSignaturesMap.put(currentFunction, currentFunctionSignature);


                    currentFunctionSignature.createIRFile(writerIR);
                    currentFunctionSignature.createJFile(writer);



                }
                printBoilerPlate(writer);
            }
            writer.close();
            writerIR.close();
        }catch(IOException e) {
            System.out.println("Error in visitor.");
            System.out.println(e);
        }
        return null;
    }

    public void printBoilerPlate(BufferedWriter writer) {
        try{
            String jvmRepresentationBoilerplate = "";
            jvmRepresentationBoilerplate += "\n\n\n.method public static main([Ljava/lang/String;)V\n";
            jvmRepresentationBoilerplate += "   .limit locals 1 \n";
            jvmRepresentationBoilerplate += "   .limit stack 16 \n";
            jvmRepresentationBoilerplate += "   invokestatic "+ outputFileName+"/__main()V \n";
            jvmRepresentationBoilerplate += "   return\n";
            jvmRepresentationBoilerplate += ".end method\n\n";
            jvmRepresentationBoilerplate += ".method public <init>()V\n";
            jvmRepresentationBoilerplate += "   aload_0\n";
            jvmRepresentationBoilerplate += "   invokenonvirtual java/lang/Object/<init>()V\n";
            jvmRepresentationBoilerplate += "   return\n";
            jvmRepresentationBoilerplate += ".end method\n";
            writer.write(jvmRepresentationBoilerplate);
        }catch(IOException e) {
            System.out.println("Error printing boilerplate for j file");
            System.out.println(e);
        }
    }




    public Temp visit (Function f){
        FunctionDeclaration functDecl = f.funcDecl;
        functDecl.accept(this);
        FunctionBody funBod = f.funcBody;
        funBod.accept(this);

        return null;
    }

    public Temp visit (FunctionDeclaration f) {
      
        // add parameters to temp factory
        ArrayList<FormalParameter> cureList = f.formalParameterList.formalParameterList;
        Iterator itr = cureList.iterator();
        while(itr.hasNext()) {
            FormalParameter fp = (FormalParameter)itr.next();
            Type paramType = fp.type;
        
            Temp param = currentFunctionTempFactory.getTemp(paramType, 'P', fp.id.idName);
        }
      return null;
    }

    public Temp visit (FunctionBody fb) {
                //go to var decl
        fb.variableDeclarationList.accept(this);
        fb.statementList.accept(this);
        return null;
    }
    
    public Temp visit(VariableDeclarationList varDeclList)  {
        //add all vardeclarations to to temp factory
        ArrayList<VariableDeclaration> listOfVariables = varDeclList.listOfVariables;
        int numberOfLocals = varDeclList.size();
        for(int i =0; i < numberOfLocals; i++) {
            VariableDeclaration currrentLocal = listOfVariables.get(i);
            Type localType = currrentLocal.type;

            Temp localTemp = currentFunctionTempFactory.getTemp(localType, 'L', currrentLocal.id.idName);
            if(localType.toString().equals("ArrayType")){
                IRInstruction in = new CreateArrayIRExpression(localTemp);
                currentFunctionSignature.addInstruction(in);
            }
        }

        return null;
    }

    public Temp visit(VariableDeclaration vd) {
        return null;
    }

    public Temp visit (Identifier idVal) {
        Temp id = (Temp)currentFunctionTempFactory.findTemp(idVal.idName);
        return id;
    }

    public Temp visit(FormalParameterList fpl) {
        return null;
    }

/**
Statements Visited this section
**/
    public Temp visit (StatementList stmtList) {
        int listSize = stmtList.size();
        for(int i = 0; i < listSize; i++) {
            Statement stcurrent = stmtList.getAt(i);
            stcurrent.accept(this);
        }
        return null;
    }

    public Temp visit (Block b) {
        return b.statementList.accept(this);
    }

    public Temp visit (Statement s) {
        return null;
    }

    public Temp visit (EmptyStatement s) {
     return null;
    }

    public Temp visit (ExpressionStatement s) {
     return s.expr.accept(this);
    }

    public Temp visit (ParenExpression e) {
        Temp result = e.expr.accept(this);
        return result;
    }

    
    public Temp visit(AssignmentStatement a)  {
        IRInstruction in;
        String varName = a.id.idName;
        Temp tempLeft = currentFunctionTempFactory.findTemp(varName);
        Temp tempRight = a.expr.accept(this);
        in = new IRAssignmentStatement(tempLeft, tempRight);
        currentFunctionSignature.addInstruction(in);
        return tempLeft;
    }

    public Temp visit (PrintLnStatement pl)  {
        IRInstruction in;
        Temp expressionTemp = pl.expr.accept(this);
        Temp holderTemp = currentFunctionTempFactory.getTemp(expressionTemp.type.getType(), 'T'); 

        in = new IRPrintlnStatement(expressionTemp, holderTemp);
        currentFunctionSignature.addInstruction(in);
        return expressionTemp;
    }

    public Temp visit (PrintStatement p) {
        IRInstruction in;
        Temp expressionTemp = p.expr.accept(this);
        Temp holderTemp = currentFunctionTempFactory.getTemp(expressionTemp.type.getType(), 'T'); 

        in = new IRPrintStatement(expressionTemp, holderTemp);
        currentFunctionSignature.addInstruction(in);
        return expressionTemp;
    }
    
  

    public Temp visit(ReturnStatement r) {
        IRInstruction in;
        Temp returnTemp = null;

        if(r.expr == null){

        }else{
            returnTemp = r.expr.accept(this);
        }


        in = new ReturnIRStatement(returnTemp);
        currentFunctionSignature.addInstruction(in);
        return returnTemp;
    }

   
  public Temp visit (WhileStatement w) {
        IRInstruction in;
        IRLabel l1 = new IRLabel();
        IRLabel l2 = new IRLabel();
      Temp t2 =null;

      in = new IRLabelInstruction(l1);
      currentFunctionSignature.addInstruction(in);
      Temp t = w.expr.accept(this); // We are guaranteed this will be boolean by our typecheck
      if(currentFunctionTempFactory.isParamOrLocal(t)) {
          t2 = currentFunctionTempFactory.getTemp(new BooleanType(0,0), 'T');
          in = new IRAssignmentStatement(t2, t);
          currentFunctionSignature.addInstruction(in);
          //t = t2;
      }
      if(t2 == null){
          in = new IRUnaryOp(t, t, IRUnaryOp.UnaryOp.Invert);
          currentFunctionSignature.addInstruction(in);
          in = new IRWhileStatement(t, l2);
      } else{
          in = new IRUnaryOp(t2, t2, IRUnaryOp.UnaryOp.Invert);
          currentFunctionSignature.addInstruction(in);
          in = new IRWhileStatement(t2, l2);
      }

      currentFunctionSignature.addInstruction(in);
      w.block.accept(this);
      in = new IRGoto(l1);
      currentFunctionSignature.addInstruction(in);
      in = new IRLabelInstruction(l2);
      currentFunctionSignature.addInstruction(in);
      return null;
    }


    public Temp visit(IfStatement i)   {
        IRInstruction in;
        IRLabel l1 = new IRLabel();
        IRLabel l2 = new IRLabel();

         Temp t = i.expr.accept(this); // We are guaranteed this will be boolean by our typecheck
         if(currentFunctionTempFactory.isParamOrLocal(t)) {
             Temp t2 = currentFunctionTempFactory.getTemp(new BooleanType(0,0), 'T');
             in = new IRAssignmentStatement(t2, t);
             currentFunctionSignature.addInstruction(in);
             t = t2;
         }

         in = new IRUnaryOp(t, t, IRUnaryOp.UnaryOp.Invert);
         currentFunctionSignature.addInstruction(in);
         in = new IRIfStatement(t, l1);
         currentFunctionSignature.addInstruction(in);
        // temps.returnTemp(t);

        i.block1.accept(this);
        in = new IRGoto(l2);
        currentFunctionSignature.addInstruction(in);
        in = new IRLabelInstruction(l1);
        currentFunctionSignature.addInstruction(in);
         if(i.block2 != null) {
             i.block2.accept(this);
         }
        in = new IRLabelInstruction(l2);
        currentFunctionSignature.addInstruction(in);
        return null;
    }

/**
Expressions Visited this section
**/
    public Temp visit(ArrayAssignment a) {
        IRInstruction in;
        String varName = a.id.idName;
        Temp tempLeft = currentFunctionTempFactory.findTemp(varName);
        Temp tempRight = a.expr.accept(this);
        Temp indexTemp = a.index.accept(this);
        in = new IRArrayAssignmentStatement(tempLeft, tempRight, indexTemp);
        currentFunctionSignature.addInstruction(in);
        return tempLeft;
    }
    
    public Temp visit(ArrayReference a) {
        IRInstruction in;
        String varName = a.id.idName;
        Temp tempLeft = currentFunctionTempFactory.findTemp(varName);
        Temp indexTemp = a.index.accept(this);
        Temp resultTemp = currentFunctionTempFactory.getTemp(tempLeft.type.getType(), 'T');
        in = new ArrayReferenceIRExpression(tempLeft, resultTemp, indexTemp);
        currentFunctionSignature.addInstruction(in);
        return resultTemp;
    }

    public Temp visit(Expression e) {
        return null;
    }

    public Temp visit(FunctionCall f)  {
        IRInstruction in;

        Type functionReturnType = functionTypeSymbolTable.get(f.id.idName);

        String functReturnTag = functionReturnType.getIRTag();
        Temp resultTemp = null;

        ArrayList<Temp> callArgumentTempList = new ArrayList<Temp>();
        int numberOfArguments = f.exprList.size();

        // Create a list of all the temporaries in the funtion call
        for( int i = 0; i< numberOfArguments; i++) {

            Temp argTemp = f.exprList.getAt(i).accept(this);
           // System.out.println(argTemp.type.getType().toString());
            callArgumentTempList.add(argTemp);
        }
        if(functionReturnType.getType().equals(new VoidType(0,0))){
            // dont use a temporary to hold the result of the function call
            FunctionCallIrExpression fe = new FunctionCallIrExpression(callArgumentTempList, f.id.idName);
            fe.programName = outputFileName;
            fe.returnIrTag = functReturnTag;
            in = fe;
        } else {
            // use a function to hold the result even if we dont use it
            if(functionReturnType.toString().equals("ArrayType")){
                resultTemp = currentFunctionTempFactory.getTemp(new ArrayType(functionReturnType.getType(), functionReturnType.getSize()), 'T');
                resultTemp.irTag = functReturnTag;
                IRInstruction in2 = new CreateArrayIRExpression(resultTemp);
                currentFunctionSignature.addInstruction(in2);
            } else{
                resultTemp = currentFunctionTempFactory.getTemp(functionReturnType.getType(), 'T');
            }

            FunctionCallIrExpression fe = new FunctionCallIrExpression(callArgumentTempList, f.id.idName, resultTemp);
            fe.programName = outputFileName;
//            System.out.println(f.id.idName);
//            System.out.println(functionReturnType.getType());
            fe.returnIrTag = functReturnTag;
            in = fe;

        }
        currentFunctionSignature.addInstruction(in);

        return resultTemp;
    }

    public Temp visit(ExpressionList eList)  {
        // create a temporary for each argument in the expression lis
        return null;
    }

    public Temp visit(EqualityExpression e) {
        IRInstruction in;
        Temp leftSide = e.exprOne.accept(this);
        Temp rightSide = e.exprTwo.accept(this);

        Temp boolResult = currentFunctionTempFactory.getTemp(new BooleanType(0,0), 'T');
        in = new IREqualityExpression(leftSide, rightSide, boolResult);
        currentFunctionSignature.addInstruction(in);
    
        return boolResult;
    }
    
    public Temp visit(LessThanExpression e) {
        IRInstruction in;
        Temp leftSide = e.exprOne.accept(this);
        Temp rightSide = e.exprTwo.accept(this);

        Temp boolResult = currentFunctionTempFactory.getTemp(new BooleanType(0,0), 'T');
        in = new LessThanIRExpression(leftSide, rightSide, boolResult);
        currentFunctionSignature.addInstruction(in);
        
        return boolResult;
    }

    public Temp visit(AddExpression e) {
        IRInstruction in;
        Temp leftSide = e.exprOne.accept(this);
        Temp rightSide = e.exprTwo.accept(this);

        Type addResultType = leftSide.type.getType();
        Temp addResult = currentFunctionTempFactory.getTemp(addResultType, 'T');
        in = new AddIRExpression(leftSide, rightSide, addResult);
        currentFunctionSignature.addInstruction(in);
        
        return addResult;
    }

    public Temp visit(SubstractExpression e) {
        IRInstruction in;
        Temp leftSide = e.exprOne.accept(this);
        Temp rightSide = e.exprTwo.accept(this);

        Type subResultType = leftSide.type.getType();
        Temp subResult = currentFunctionTempFactory.getTemp(subResultType, 'T');
        in = new SubIRExpression(leftSide, rightSide, subResult);
        currentFunctionSignature.addInstruction(in);
        
        return subResult;
    }

    public Temp visit(MultExpression e) {
        IRInstruction in;
        Temp leftSide = e.exprOne.accept(this);
        Temp rightSide = e.exprTwo.accept(this);

        Type multResultType = leftSide.type.getType();
        Temp multResult = currentFunctionTempFactory.getTemp(multResultType, 'T');
        in = new MultIRExpression(leftSide, rightSide, multResult);
        currentFunctionSignature.addInstruction(in);
        
        return multResult;
    }

/**
Literals visited this section
**/

    public Temp visit(IntegerLiteral i) {
        IRInstruction in;
        Temp integerTemp = currentFunctionTempFactory.getTemp(new IntegerType(0,0), 'T');
        int value = i.value;
        in = new IRIntegerLiteral(integerTemp, value); 
        currentFunctionSignature.addInstruction(in);
        return integerTemp;
    }

    public Temp visit(FloatLiteral f) {
        IRInstruction in;
        Temp floatTemp = currentFunctionTempFactory.getTemp(new FloatType(0,0), 'T');
        float value = f.value;
        in = new IRFloatLiteral(floatTemp, value); 
        currentFunctionSignature.addInstruction(in);
        return floatTemp;
    }

    public Temp visit(BooleanLiteral b) {
        IRInstruction in;
        Temp boolTemp = currentFunctionTempFactory.getTemp(new BooleanType(0,0), 'T');
        boolean value = b.value;
        in = new IRBooleanLiteral(boolTemp, value); 
        currentFunctionSignature.addInstruction(in);
        return boolTemp;
    }
    
    public Temp visit(StringLiteral s) {
        IRInstruction in;
        Temp stringTemp = currentFunctionTempFactory.getTemp(new StringType(0,0), 'T');
        String value = s.value;
        in = new IRStringLiteral(stringTemp, value); 
        currentFunctionSignature.addInstruction(in);
        return stringTemp;
    }

    public Temp visit(CharacterLiteral c) {
        IRInstruction in;
        Temp charTemp = currentFunctionTempFactory.getTemp(new CharType(0,0), 'T');
        char value = c.value;
        in = new IRCharacterLiteral(charTemp, value); 
        currentFunctionSignature.addInstruction(in);
        return charTemp;
    }

/**
Types visited this section
**/
    public Temp visit(Type ty){
return null;
    }
    
    public Temp visit(CharType c) {
 return null;
    }

    public Temp visit(ArrayType c) {
   return null;
    }

    public Temp visit(IntegerType c) {
 return null;
    }

    public Temp visit(FloatType c) {
return null;
    }

    public Temp visit(BooleanType c) {
   return null;
    }

    public Temp visit(VoidType c) {
      return null;
    }

    public Temp visit(StringType c) {
     return null;
    }

    public Temp visit(SimpleType c) {
     return null;
    }

    public Temp visit(FormalParameter c) {
        return null;
    }
}