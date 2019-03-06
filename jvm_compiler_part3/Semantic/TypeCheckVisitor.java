package Semantic;

import AST.*;
import Type.*;
import java.util.*;
import IntRep.*;

public class TypeCheckVisitor implements TypeVisitor {	

	Environment<String, FunctionDeclaration> FunctionEnv;
	//create the var envirnonments in each function
	Environment<String, Type> currentVariableEnv;
	String currentFunction;
	Type currentFunctionReturnType;
	//boolean functionHadReturn = false;
	// for each function add a map for its variableenv with a key of functname
	// All variable types are stores as superTYpe.subType where supertype is simpleType or ArrayType
	HashMap<String,Environment<String,Type>> functionVariableEnvMap;
	HashMap<String,HashMap<String,Type>> functionParameters; // [functname, (varName, varType)]
	// map of each function name and its return type
	HashMap<String, Type> nameCounts; 

	public Type visit (Program p) 
		throws SemanticException {
		FunctionEnv = new Environment<String, FunctionDeclaration>();
		functionVariableEnvMap = new HashMap<String,Environment<String,Type>>();
		ArrayList<Function> functionListClone = p.functionList;
		functionParameters = new HashMap<String,HashMap<String,Type>>(); 
		if( p!=null ) {
			int funtionCount = p.size();
			if(funtionCount < 1){
				throw new SemanticException("Program must contain at least 1 function, found 0.", 0,0);
			}
			nameCounts = new HashMap<String, Type>();
			for(int i = 0; i < funtionCount; i++){
				Function f = functionListClone.get(i);
				FunctionDeclaration funcDecl = f.funcDecl;
				Type fType = funcDecl.type;
				Identifier fID = funcDecl.id;
				String fName = fID.idName;
				if(nameCounts.containsKey(fName)) {
					throw new SemanticException("Program must contain only unique function names.", fID.getLine(), fID.getOffset());
				} else {
					nameCounts.put(fName, fType);
				}
				FormalParameterList fParamList = funcDecl.formalParameterList;
				if(fName.equals("main")){
					if(!fType.getType().equals(new VoidType(0,0))){
						throw new SemanticException("Main function must have return type void.",  fType.getType().getLine(), fType.getType().getOffset());	
					}
					if( fParamList.size() > 0 ){
						throw new SemanticException("Main function must not take any parameters.",  fID.getLine(), fID.getOffset() + 5);	
					}
				}
				FunctionEnv.add(fName, funcDecl);
			}
			if(!nameCounts.containsKey("main")){
				throw new SemanticException("Program must contain a main function.", 0, 0);
			} 
			if ( p!=null ) {
				int functionCount = p.size();
				for (int i=0;i<functionCount;i++) {
					Function f = functionListClone.get(i);
					FunctionDeclaration curFunctDecl = f.funcDecl;
					currentFunction = curFunctDecl.id.idName;
					currentFunctionReturnType = curFunctDecl.type;
					f.accept(this);
				}
			}		
		}
		return null;
	}

	public Type visit (Function f) throws SemanticException {
		Type returnTypeResult = new SimpleType(new VoidType(0,0));
		f.funcDecl.accept(this);
		returnTypeResult = f.funcBody.accept(this);
		// if(!currentFunctionReturnType.getType().equals(new VoidType(0,0)) && !functionHadReturn ) {
		// 	throw new SemanticException("Function with non-void return type must have a return statement.", f.funcDecl.id.getLine(), f.funcDecl.id.getOffset());
		// }
		// //functionHadReturn = false;
		return returnTypeResult;
	}

	public Type visit (FunctionDeclaration f) throws SemanticException {
		Type returnTypeResult = new SimpleType(new VoidType(0,0));
		FunctionDeclaration currFunctionDecl = FunctionEnv.getValue(currentFunction);
		ArrayList<FormalParameter> formalParamList = currFunctionDecl.formalParameterList.formalParameterList;
		HashMap<String,Type> functionParams = new HashMap<String, Type>();
		int numParams = formalParamList.size();
 		for(int j =0; j< numParams; j++) {
			FormalParameter curParam = formalParamList.get(j);
			if(curParam.type.getType().equals(new VoidType(0,0))) {
				throw new SemanticException("Type of Function paramaters cannot be void.", curParam.id.getLine(), curParam.type.getType().getOffset());
			}
			if(functionParams.containsKey(curParam.id.idName)) {
				throw new SemanticException("Function parameters cannot have duplicate names.", curParam.id.getLine(), curParam.id.getOffset());
			} else {
				functionParams.put(curParam.id.idName, curParam.type);
			}
		}
		functionParameters.put(currentFunction, functionParams);
		return returnTypeResult;
	}

	public Type visit (FunctionBody fb) {
		Type returnTypeResult = new SimpleType(new VoidType(0,0));
		fb.variableDeclarationList.accept(this);
		returnTypeResult = fb.statementList.accept(this);
		return returnTypeResult;
	}
	
	public Type visit(VariableDeclarationList varDeclList) throws SemanticException {
		currentVariableEnv = new Environment<String, Type>();	
		int sizeList = varDeclList.size();
		for(int i = 0; i < sizeList ; i++  ){
			VariableDeclaration curVarDecl = varDeclList.getAt(i);
			curVarDecl.accept(this);
		}
		functionVariableEnvMap.put(currentFunction, currentVariableEnv);
		return null;
	}

	public Type visit(VariableDeclaration vd) throws SemanticException {
		String name = vd.id.idName;
		Type varType = vd.type;
		HashMap<String,Type> functionParams = functionParameters.get(currentFunction);
		if(functionParams.containsKey(name)) {
			throw new SemanticException("Variables declared in function body cannot shadow function parameter names.", varType.getType().getLine(), vd.id.getOffset());
		} else if(currentVariableEnv.containsKey(name)) {
			throw new SemanticException("Function cannot declare variables with the same name.", varType.getType().getLine(), vd.id.getOffset());
		} else if(varType.getType().equals(new VoidType(0,0))){
			throw new SemanticException("Cannot declare variable of type void.", varType.getType().getLine(), varType.getType().getOffset());
		}else{
			currentVariableEnv.add(name, varType);
		}
		return null;
	}

	public Type visit (Identifier idVal) throws SemanticException {
		Environment<String,Type> currFunctVariableEnv  = functionVariableEnvMap.get(currentFunction);
		HashMap<String,Type> curFunctParams =  functionParameters.get(currentFunction); //  (varName, varType)]
		Type returnType = new SimpleType(new VoidType(0,0));

		if(currFunctVariableEnv.containsKey(idVal.idName)) {
			returnType = currFunctVariableEnv.getValue(idVal.idName);
		} else if(curFunctParams.containsKey(idVal.idName)) {
			returnType = curFunctParams.get(idVal.idName);
		} else{
			throw new SemanticException("Variable must be declared before being used.", idVal.line, idVal.offset);
		}
		return returnType;
	}

	public Type visit(FormalParameterList fpl) {
		return null;
	}

/**
Statements Visited this section
**/
	public Type visit (StatementList stmtList) throws SemanticException {
		Type returnType = new VoidType(0,0);
		int sizesOfStmtList = stmtList.size();
		for(int i = 0; i < sizesOfStmtList; i++) {
			Statement currStatement = stmtList.getAt(i);
			returnType = currStatement.accept(this);
		}
		return returnType;
	}

	public Type visit (Block b) {
		Type returnType = b.statementList.accept(this); 
		return returnType;
	}

	public Type visit (Statement s) {
		return null;
	}

	public Type visit (EmptyStatement s) {
		return new VoidType(0,0);
	}

	public Type visit (ExpressionStatement s) {
		return s.expr.accept(this);
	}

	public Type visit (ParenExpression e) {
		Type returnType = e.expr.accept(this);
		return returnType;
	}

	public Type visit (WhileStatement w) throws SemanticException {
		Type conditionalType = w.expr.accept(this);
		if(conditionalType!=null && !conditionalType.getType().equals(new BooleanType(0,0))) {
			throw new SemanticException("While conditional expression must be of type boolean.", w.getLine(), w.expr.getOffset());
		}
		if(conditionalType.toString().equals("ArrayType")){
			throw new SemanticException("Array variable cannot be used in conditional statement.", w.getLine(), w.expr.getOffset());
		}

		w.block.accept(this);
		return null;
	}

	public Type visit (PrintLnStatement pl) throws SemanticException {
		Type returnType = pl.expr.accept(this);
		Type subType = returnType.getType();
		if(returnType.toString().equals("ArrayType")) {
			throw new SemanticException("Array variable invalid println statement.", pl.line, pl.expr.getOffset());
		}
		if(subType.toString().equals("void")) {
			throw new SemanticException("Illegal argument for println statement.", pl.line, pl.expr.getOffset());
		}
		return returnType;
	}

	public Type visit (PrintStatement p) throws SemanticException{
		Type returnType = p.expr.accept(this);
		Type subType = returnType.getType();
		if(returnType.toString().equals("ArrayType")) {
			throw new SemanticException("Array variable invalid print statement.", p.line, p.expr.getOffset());
		}

		if(subType.toString().equals("void")) {
			throw new SemanticException("Illegal argument for print statement.", p.line, p.expr.getOffset());
		}
		return returnType;
	}

	public Type visit(ReturnStatement r) throws SemanticException {
		//functionHadReturn = true;
		Type returnType = null;
		Type returnTypeSubType = null;
		if(r.expr == null){
			returnType = new SimpleType( new VoidType(r.line, r.getOffset() + 7));
			returnTypeSubType = returnType.getType();
			//r.expr  = new IntegerLiteral(0, r.line, r.getOffset() + 7);
		} else {
			returnType = r.expr.accept(this);
			returnTypeSubType = returnType.getType();
		}
		Type curFuncRetSubType = currentFunctionReturnType.getType();
		if(!returnTypeSubType.equals(curFuncRetSubType)) {
			throw new SemanticException("Type of return statement must match declared function return type.", r.line, r.expr.getOffset());
		} 
		if(currentFunctionReturnType.toString().equals("ArrayType")) {
			ArrayType declType = (ArrayType)currentFunctionReturnType;
			if(returnType.toString().equals("ArrayType") ) {
				int returnTypeSize = ((ArrayType)returnType).size;
				int functionReturnTypeSize = declType.size;
				if(returnTypeSize != functionReturnTypeSize) {
					throw new SemanticException("Function declared to return array of size: " + functionReturnTypeSize + ", found size: "+ returnTypeSize, r.line, r.expr.getOffset());
				}
			} else{
				throw new SemanticException("Function declared to return array, return type match error.", r.line, r.expr.getOffset());
			}
		}
		
		return returnType;
	}

	public Type visit(AssignmentStatement a) throws SemanticException  {
		Type returnType = a.expr.accept(this);;
		Environment<String,Type> currentVariableEnvironment = functionVariableEnvMap.get(currentFunction);
		HashMap<String,Type> currentFunctionParameterList = functionParameters.get(currentFunction); 
		Identifier currentVariable = a.id;
		if(currentVariableEnvironment.containsKey(currentVariable.idName)  ) {
			Type currentVariableType = currentVariableEnvironment.getValue(currentVariable.idName);
			if(!currentVariableType.getType().equals(returnType.getType())) {
				throw new SemanticException("Illegal variable assignment, type missmatch." , currentVariable.line, a.expr.getOffset());
			} 
			if(currentVariableType.toString().equals("ArrayType") ) {
				if(returnType.toString().equals("ArrayType") ) {
					int rightSize = ((ArrayType)returnType).size;
					int leftSIze = ((ArrayType)currentVariableType).size;
					if(rightSize != leftSIze) {
						throw new SemanticException("Illegal array assignment, array sizes must match.", currentVariable.line, a.expr.getOffset());
					}
				} else {
					throw new SemanticException("Illegal array assignment statement.", currentVariable.line, a.expr.getOffset());
				}
			}
		} else if(currentFunctionParameterList.containsKey(currentVariable.idName)){
			Type currentVariableType = currentFunctionParameterList.get(currentVariable.idName);
			if(!currentVariableType.getType().equals(returnType.getType())) {
				throw new SemanticException("Illegal variable assignment, type missmatch." , currentVariable.line, a.expr.getOffset());
			} 
			if(currentVariableType.toString().equals("ArrayType") ) {
				if(returnType.toString().equals("ArrayType") ) {
					int rightSize = ((ArrayType)returnType).size;
					int leftSIze = ((ArrayType)currentVariableType).size;
					if(rightSize != leftSIze) {
						throw new SemanticException("Illegal array assignment, array sizes must match.", currentVariable.line, a.expr.getOffset());
					}
				} else {
					throw new SemanticException("Illegal array assignment statement.", currentVariable.line, a.expr.getOffset());
				}
			}
		} else {
			throw new SemanticException("Variable must be declared before being used.", currentVariable.line, currentVariable.getOffset());
		}
		return returnType;
	}

	public Type visit(IfStatement i) throws SemanticException   {
		Type conditionalType = i.expr.accept(this);

		if(!conditionalType.getType().equals(new BooleanType(0,0))){
			throw new SemanticException("If statement conditional expression must be of type boolean.", i.getLine(), i.expr.getOffset());
		}
		if(conditionalType.toString().equals("ArrayType")){
			throw new SemanticException("Array variable cannot be used in conditional statement.", i.getLine(), i.expr.getOffset());
		}
		if(i.block2 == null) {
			i.block1.accept(this);
		} else {
			i.block1.accept(this);
			i.block2.accept(this);
		}

		return null;
	}

/**
Expressions Visited this section
**/
	public Type visit(ArrayAssignment a) throws SemanticException {	
		Type leftType = a.id.accept(this);
		Type indexType = a.index.accept(this);
		Type rightType = a.expr.accept(this);

		if(!indexType.getType().equals(new IntegerType(0,0))) {
			throw new SemanticException("Index value must be of type integer for array assignment.", a.line, a.index.getOffset());
		}

		if(!leftType.getType().equals(rightType.getType())) {
			throw new SemanticException("Array type must be the same as type of value being assigned.", a.line, a.expr.getOffset() );
		}
		return leftType;
	}
	
	public Type visit(ArrayReference a) throws SemanticException{
		Type leftType = a.id.accept(this);
		Type indexType = a.index.accept(this);
		if(!indexType.getType().equals(new IntegerType(0,0))) {
			throw new SemanticException("Index value must be of type integer for array reference.", a.index.getLine(), a.index.getOffset());
		}
		if(!leftType.toString().equals("ArrayType")){
			throw new SemanticException("Array reference error, cannot reference non-array variable.", a.index.getLine(), a.index.getOffset());
		}
		return leftType.getType();
	}

	public Type visit(Expression e) {
		return null;
	}

	public Type visit(FunctionCall f) throws SemanticException  {
		String callName = f.id.idName;
		if(!FunctionEnv.containsKey(callName)){
			throw new SemanticException("Function does not exist.", f.line, f.id.getOffset());
		}
		FunctionDeclaration calledFunctionDecl = FunctionEnv.getValue(callName);	
		Type calledFunctionReturnType = calledFunctionDecl.type;
		FormalParameterList fpl = calledFunctionDecl.formalParameterList;
		ArrayList<FormalParameter> calledFunctionParams = fpl.formalParameterList;
		ArrayList<Expression> callingExprParamList = f.exprList.eList;
		int callingParamsNum =0;
		int calledFunctParamNum = fpl.size();
		if(callingExprParamList == null) {
			callingParamsNum = 0;
		}else{
			callingParamsNum = callingExprParamList.size();
		}	
		if(calledFunctParamNum != callingParamsNum) {
			throw new SemanticException("Function call expression must have same number of arguments as function being called.", f.line, f.getOffset());
		}
		for(int i =0 ; i < callingParamsNum ; i++) {
			Type calledParamType = calledFunctionParams.get(i).type;
			Type callingParamType = callingExprParamList.get(i).accept(this);
			if(!calledParamType.getType().equals(callingParamType.getType())) {
				throw new SemanticException("Function call parameter types must match called function parameter types.", f.line, callingParamType.getType().getOffset());
			}
			if(calledParamType.toString().equals("ArrayType") ) {
				if(callingParamType.toString().equals("ArrayType") ) {
					int rightSize = ((ArrayType)callingParamType).size;
					int leftSIze = ((ArrayType)calledParamType).size;
					if(rightSize != leftSIze) {
						throw new SemanticException("Illegal function call parameter, array sizes don't match.", f.line, callingExprParamList.get(i).getOffset());
					}
				} else {
					throw new SemanticException("Illegal function call parameter, requires array type.", f.line, callingExprParamList.get(i).getOffset());

				}
			}

		}
		return calledFunctionReturnType;
	}

	public Type visit(ExpressionList eList) throws SemanticException  {
		
		return null;
	}

	public Type visit(EqualityExpression e) throws SemanticException {
		Type leftType = e.exprOne.accept(this);
		Type rightType = e.exprTwo.accept(this);
		
		if(!leftType.getType().equals(rightType.getType())) {
			throw new SemanticException("Equality expression valid only for identical expressions types.", e.line, e.getOffset());
		} 
		if(leftType.getType().equals(new VoidType(0,0))) {
			throw new SemanticException("Void type invalid for equality expression.", e.line, e.getOffset());
		}
		if(leftType.toString().equals("ArrayType") || rightType.toString().equals("ArrayType") ) {
			throw new SemanticException("Array variable invalid for equality expression.", e.line, e.getOffset());
		}
		return new BooleanType(e.line, e.getOffset());
	}
	
	public Type visit(LessThanExpression e) throws SemanticException {
		Type leftType = e.exprOne.accept(this);
		Type rightType = e.exprTwo.accept(this);
		if(!leftType.getType().equals(rightType.getType())) {
			throw new SemanticException("Less-than expression valid only for identical expressions types.",  e.line, e.getOffset());
		} 
		if(leftType.getType().equals(new VoidType(0,0))) {
			throw new SemanticException("Void type invalid for less-than expression.",  e.line, e.getOffset());
		}
		if(leftType.toString().equals("ArrayType")|| rightType.toString().equals("ArrayType")) {
			throw new SemanticException("Array variable invalid for less-than expression.", e.line, e.getOffset());
		}
		return new BooleanType(e.line, leftType.getType().getOffset());
	}

	public Type visit(AddExpression e) throws SemanticException {
		Type leftType = e.exprOne.accept(this);
		int lineNumber = leftType.getType().getLine();
		int lineOffset = leftType.getType().getOffset();
		Type rightType = e.exprTwo.accept(this);
		if(!leftType.getType().equals(rightType.getType())) {
			throw new SemanticException("Add expression valid only for identical expressions types.",  e.line, e.getOffset());
		} 

		if(leftType.getType().equals(new BooleanType(0,0)) || leftType.getType().equals(new VoidType(0,0)) ) {
			throw new SemanticException("Add expression not valid for types boolean or void.",  e.line, e.getOffset());
		}
		if(leftType.toString().equals("ArrayType")|| rightType.toString().equals("ArrayType")) {
			throw new SemanticException("Array variable invalid for add expression.", e.line, e.getOffset());
		}

		return leftType;
	}

	public Type visit(SubstractExpression e) throws SemanticException{
		Type leftType = e.exprOne.accept(this);
		Type rightType = e.exprTwo.accept(this);
		if(!leftType.getType().equals(rightType.getType())) {
			throw new SemanticException("Subtract expression valid only for identical expressions types.",  e.line, e.getOffset());
		} 

		if(leftType.getType().equals(new BooleanType(0,0)) || leftType.getType().equals(new VoidType(0,0)) ||  leftType.getType().equals(new StringType(0,0))) {
			throw new SemanticException("Subtract expression valid only for types int, char and float.",  e.line, e.getOffset());
		}
		if(leftType.toString().equals("ArrayType")|| rightType.toString().equals("ArrayType")) {
			throw new SemanticException("Array variable invalid for subtract expression.", e.line, e.getOffset());
		}

		return leftType;
	}

	public Type visit(MultExpression e) throws SemanticException{
		Type leftType = e.exprOne.accept(this);
		Type rightType = e.exprTwo.accept(this);
		if(!leftType.getType().equals(rightType.getType())) {
			throw new SemanticException("Multiplication expression valid only for identical expressions types.",  e.line, e.getOffset());
		} 

		if(! (leftType.getType().equals(new IntegerType(0,0)) || leftType.getType().equals(new FloatType(0,0)))) {
			throw new SemanticException("Multiplication expression valid only for types int and float.",  e.line, e.getOffset());
		}
		if(leftType.toString().equals("ArrayType")|| rightType.toString().equals("ArrayType")) {
			throw new SemanticException("Array variable invalid for subtract expression.", e.line, e.getOffset());
		}

		return leftType;
	}

/**
Literals visited this section
**/

	public Type visit(IntegerLiteral i) {
		return new IntegerType(i.line, i.offset);
	}

	public Type visit(FloatLiteral f) {
		return new FloatType(f.line, f.offset);
	}

	public Type visit(BooleanLiteral b) {
		return new BooleanType(b.line, b.offset);
	}
	
	public Type visit(StringLiteral s) {
		return new StringType(s.line, s.offset);
	}

	public Type visit(CharacterLiteral c) {
		return new CharType(c.line, c.offset);
	}

/**
Types visited this section
**/
	public Type visit(Type ty){
		return ty;
	}
	
	public Type visit(CharType c) {
		return c;
	}

	public Type visit(ArrayType c) {
		return c;
	}

	public Type visit(IntegerType c) {
		return c;
	}

	public Type visit(FloatType c) {
		return c;
	}

	public Type visit(BooleanType c) {
		return c;
	}

	public Type visit(VoidType c) {
		return c;
	}

	public Type visit(StringType c) {
		return c;
	}

	public Type visit(SimpleType c) {
		return c;
	}

	public Type visit(FormalParameter c) {
		return c.type;
	}
}