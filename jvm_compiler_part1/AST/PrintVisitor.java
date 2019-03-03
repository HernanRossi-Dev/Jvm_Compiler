package AST;
import Type.*;
//Hernan Rossi

public class PrintVisitor implements Visitor {	
	// Increment when an if/while statement is encountered, decrement after they are exited
	// for each increment print four space;
	private int indentLevel = 0;
	public void visit (Program p) {
		if ( p!=null ) {
			int functionCount = p.size();
			for (int i=0;i<functionCount;i++) {
				Function f = p.elementAt(0);
				f.accept(this);
				System.out.println();
			}
		}
	}

	public void visit (Function f) {
		f.funcDecl.accept(this);
		f.funcBody.accept(this);
	}

	public void visit (FunctionDeclaration f) {
		f.type.accept(this);
		printIndent();
		f.id.accept(this);
		f.formalParameterList.accept(this);
	}

	public void visit (Identifier idVal) {
		System.out.print(idVal.idName);
	}

	public void visit(FormalParameterList fpl) {
		System.out.print(" (");
		if (fpl.noParams) {
			//empty parameter list
		} else {
			if (fpl.type.toString().equals("sType")) {
				SimpleType st = (SimpleType)fpl.type;
				System.out.print(st.getType().toString()+ " ");
			} else if (fpl.type.toString().equals("array") ) {
				ArrayType at = (ArrayType)fpl.type;
				System.out.print(at.getType().toString());
				System.out.print("[");
				System.out.print(at.getSize());
				System.out.print("] ");
			}
			Identifier id = fpl.id;
			System.out.print(id.idName);

			int numParams = fpl.size();
			for (int i=0;i<numParams;i++)
			{
				FormalParameter fp = fpl.elementAt(0);
				System.out.print(", ");
				id = fp.id;
				
				Type type = fp.type;
				if (type.toString().equals("sType")){
					SimpleType st = (SimpleType)fp.type;
					System.out.print(st.getType().toString());
				} else if ( type.toString().equals("array")){
					ArrayType at = (ArrayType)fp.type;
					System.out.print(at.getType().toString());
					System.out.print("[");
					System.out.print(at.getSize());
					System.out.print("]");
				}
				System.out.print(" " +id.idName);
			}
		}
		System.out.print(") ");
	}
	
	public void visit (FunctionBody fb) {
		indentLevel++;
		System.out.print("\n{\n");
		fb.variableDeclarationList.accept(this);
		fb.statementList.accept(this);
		System.out.println("}");
		indentLevel--;
	}

	public void visit(VariableDeclarationList varDeclList) {
		int sizeOfList = varDeclList.size();
		for (int i = 0; i < sizeOfList; i++) {
			VariableDeclaration varDecl = varDeclList.elementAt(0);
			varDecl.accept(this); 
		}

		if ( sizeOfList==0 ){
		 } else { System.out.println(); } 
	}

	public void visit(VariableDeclaration vd) {
		printIndent();
		if (vd.type.toString().equals("sType")) {
			SimpleType st = (SimpleType)vd.type;
			System.out.print(st.getType().toString());
		} else if ( vd.type.toString().equals("array")) {
			ArrayType at = (ArrayType)vd.type;
			System.out.print(at.getType().toString());
			System.out.print("[");
			System.out.print(at.getSize());
			System.out.print("]");
		}
		
		System.out.print(" " + vd.id.idName);
		System.out.println(";");
	}

/**
Statements Visited this section
**/
	public void visit (StatementList stmtList) {
		int sizeOfList = stmtList.size();
		for (int i = 0; i < sizeOfList; i++) {		
			Statement stmt = stmtList.elementAt(0);
			if(stmt.typeOfStatement()=="ExpressionStatement"){
				printIndent();
			}
			stmt.accept(this);   
		}		
	}

	public void visit (Block b) {
		b.statementList.accept(this);   	
	}

	public void visit (Statement s) {
		
	}

	public void visit (EmptyStatement s) {
		//printIndent();
		// Dont bother printing the semi-colon for an empty statement
		//System.out.println(";");
	}

	public void visit (ExpressionStatement s) {
		s.expr.accept(this);
		System.out.println(";");
	}

	public void visit (ParenExpression e) {
		System.out.print("(");
		e.expr.accept(this);
		System.out.print(")");
	}

	public void visit (WhileStatement w) {
		printIndent();
		System.out.print("while (");
		int tempIndent = indentLevel;
		indentLevel=0;
		w.expr.accept(this);
		indentLevel=tempIndent;
		System.out.println(")");
		printIndent();
		System.out.println("{");
		indentLevel++;
		w.block.accept(this);
		indentLevel--;
		printIndent();
		System.out.println("}");
	}

	public void visit (PrintLnStatement pl) {
		printIndent();
		System.out.print("println ");
		int tempIndent = indentLevel;
		indentLevel=0;
		pl.expr.accept(this);
		indentLevel=tempIndent;
		System.out.println(";");
	}

	public void visit (PrintStatement p) {
		printIndent();
		System.out.print("print ");
		int tempIndent = indentLevel;
		indentLevel=0;
		p.expr.accept(this);
		indentLevel=tempIndent;
		System.out.println(";");
	}

	public void visit(ReturnStatement r) {
		printIndent();
		System.out.print("return");	
		if(r.expr.typeOfExpression().equals("Expression")){
			System.out.println(";");
		} else{
			System.out.print(" ");
			int tempIndent = indentLevel;
			indentLevel=0;
			r.expr.accept(this);
			indentLevel=tempIndent;
			System.out.println(";");
		}
	}

	public void visit(AssignmentStatement a) {
		printIndent();
		a.id.accept(this);
		System.out.print("=");
		int tempIndent = indentLevel;
		indentLevel=0;
		a.expr.accept(this);
		indentLevel=tempIndent;
		System.out.println(";");
	}

	public void visit(IfStatement i) {
		printIndent();
		Expression ex = i.expr;
		Block blockOne = i.block1;
		Block blockTwo = i.block2;
		if(blockTwo == null) {
			System.out.print("if (");
			int tempIndent = indentLevel;
			indentLevel=0;
			ex.accept(this); 
			indentLevel=tempIndent;
			System.out.println(")");
			printIndent();
			System.out.print("{\n");
			indentLevel++;
			blockOne.accept(this);
			indentLevel--;
			printIndent();
			System.out.println("}");
			
		} else if(blockTwo!=null) {
			System.out.print("if (");
			int tempIndent = indentLevel;
			indentLevel=0;
			ex.accept(this); 
			indentLevel=tempIndent;
			System.out.println(")");
			printIndent();
			System.out.print("{\n");	
			indentLevel++;
			blockOne.accept(this);
			indentLevel--;
			printIndent();
			System.out.println("}");
			printIndent();
			System.out.print("else\n");
			printIndent();
			System.out.println("{");
			indentLevel++;
			blockTwo.accept(this);
			indentLevel--;
			printIndent();
			System.out.print("}\n");
		} else {
			System.out.println("Error encountered printing if/else statement.");
		}
	}

/**
Expressions Visited this section
**/
	public void visit(ArrayAssignment a) {
		printIndent();
		a.id.accept(this);
		System.out.print("[");
		int tempIndent = indentLevel;
		indentLevel=0;
		a.index.accept(this);
		System.out.print("]=");
		a.expr.accept(this);
		indentLevel=tempIndent;
		System.out.println(";");
	}
	
	public void visit(ArrayReference a) {
		printIndent();
		a.id.accept(this);
		System.out.print("[");
		int tempIndent = indentLevel;
		indentLevel=0;
		a.index.accept(this);
		System.out.print("]");
		indentLevel=tempIndent;
	}

	public void visit(Expression e) {
	}

	public void visit(ExpressionList eList){
		int tempIndent = indentLevel;
		indentLevel=0;
		eList.expr.accept(this);

		int listSize = eList.size();
		for(int i = 0; i < listSize; i++){
			System.out.print(", ");
			Expression temp = eList.elementAt(0);
			temp.accept(this);
		}
		indentLevel=tempIndent;
	}

	public void visit(EqualityExpression e) {
		Expression leftExpr = e.exprOne;
		Expression rightExpr = e.exprTwo;
		leftExpr.accept(this);
		System.out.print("==");
		rightExpr.accept(this);
	}
	
	public void visit(LessThanExpression e) {
		Expression leftExpr = e.exprOne;
		Expression rightExpr = e.exprTwo;
		leftExpr.accept(this);
		System.out.print("<");
		rightExpr.accept(this);
	}

	public void visit(AddExpression e) {
		Expression leftExpr = e.exprOne;
		Expression rightExpr = e.exprTwo;
		leftExpr.accept(this);
		System.out.print("+");
		rightExpr.accept(this);
	}

	public void visit(SubstractExpression e) {
		Expression leftExpr = e.exprOne;
		Expression rightExpr = e.exprTwo;
		leftExpr.accept(this);
		System.out.print("-");
		rightExpr.accept(this);
	}

	public void visit(MultExpression e) {
		Expression leftExpr = e.exprOne;
		Expression rightExpr = e.exprTwo;
		leftExpr.accept(this);
		System.out.print("*");
		rightExpr.accept(this);
	}

	public void visit(FunctionCall f) {
		int tempIndent = indentLevel;
		indentLevel=0;
			//ex.accept(this); 
		if(f.exprList.expr == null){
			f.id.accept(this);
			System.out.print("(");
			System.out.print(")");
		} else {
			f.id.accept(this);
			System.out.print("(");
			f.exprList.accept(this);
			System.out.print(")");
		}
		indentLevel=tempIndent;
	}

/**
Literals visited this section
**/

	public void visit(IntegerLiteral i) {
		System.out.print(i.value);
	}

	public void visit(FloatLiteral f) {
		System.out.print(f.value);
	}

	public void visit(BooleanLiteral b) {
		System.out.print(b.value);
	}
	
	public void visit(StringLiteral s) {
		//String are stored with the parens
		System.out.print("\"");
		System.out.print(s.value);
		System.out.print("\"");
	}

	public void visit(CharacterLiteral c) {
		System.out.print("\'");
		System.out.print(c.value);
		System.out.print("\'");
	}

/**
Types visited this section
**/
	public void visit(Type ty){
	}
	
	public void visit(CharType c) {
	}

	public void visit(ArrayType c) {
		Type temp = c.getType();
		String typeName = temp.toString();
		int size = c.getSize();
		System.out.print(typeName + "[" + size + "] ");

	}
	public void visit(IntegerType c) {
	}

	public void visit(FloatType c) {
	}

	public void visit(BooleanType c) {
	}

	public void visit(VoidType c) {
	}

	public void visit(StringType c) {
	}

	public void visit(SimpleType c) {
		Type temp = c.getType();
		String typeName = temp.toString();
		System.out.print(typeName + " ");
	}

	public void visit(FormalParameter c) {
	}

/**
Helper Methods
**/
	public void printIndent(){
		for(int i=0; i<indentLevel; i++){
			for(int j=0; j<4; j++) {
				System.out.print(" ");
			}
		}
	}
}