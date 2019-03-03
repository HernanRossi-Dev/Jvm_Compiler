grammar ulNoActions;
// Hernan Rossi V00767958 

@header {
    import AST.*;
    import Type.*;
}   
				
@members
{
protected void mismatch (IntStream input, int ttype, BitSet follow)
        throws RecognitionException
{
        throw new MismatchedTokenException(ttype, input);
}
public void recoverFromMismatchedSet (IntStream input,
                                      RecognitionException e,
                                      BitSet follow)
        throws RecognitionException
{
        reportError(e);
        throw e;
}
}

@rulecatch {
        catch (RecognitionException ex) {
                reportError(ex);
                throw ex;
        }
}

program returns [Program prog]
    @init
    {
            prog = new Program();
    }
    : (f=function { prog.add(f);} )+ EOF
    ;

function returns [Function f]: d=functionDecl b=functionBody
    { 
        f = new Function(d, b); 
    }
    ;

functionDecl returns [FunctionDeclaration f]: c=compoundType i=id '(' p=formalParameters ')'
    {
        f = new FunctionDeclaration(c, i, p);
    }
    ;

compoundType returns [Type t]:  type=typeProduction
    {
       t = new SimpleType(type);
    } 
    |  type=typeProduction '[' size=INTEGERCONSTANT ']'
    {
        t = new ArrayType(type, $size.text);
    }
    ;

typeProduction returns [Type t] : type=TYPE 
    {
        if ($type.text.equals("int"))               { t = new IntegerType($type.getLine(), $type.pos);
        } else if($type.text.equals("char"))        { t = new CharType($type.getLine(), $type.pos);
        } else if($type.text.equals("float"))       { t = new FloatType($type.getLine(), $type.pos);
        } else if($type.text.equals("boolean"))     { t = new BooleanType($type.getLine(), $type.pos);
        } else if($type.text.equals("void"))        { t = new VoidType($type.getLine(), $type.pos);
        } else if($type.text.equals("string"))      { t = new StringType($type.getLine(), $type.pos);
        }
    }
    ;

formalParameters returns [FormalParameterList f]  
    @init {
        f = new FormalParameterList();
    } : (c=compoundType{ f.addType(c); f.setEmpty(false);}) (i=id{ f.addID(i); } ) (m=moreFormals { f.addElement(m); } )* 
    |   
    ;

moreFormals returns [FormalParameter f] : ',' c=compoundType i=id
    {
        f = new FormalParameter(c, i);
    }
    ;

functionBody returns [FunctionBody f]
    @init {
        f = new FunctionBody();
    }  : '{' (vl=variableDeclarationProduction{f.addVariableList(vl);})
            (s=statementListProduction{f.addStatementList(s); }) '}'
    ;

variableDeclarationProduction returns [VariableDeclarationList v] 
    @init{
        v = new VariableDeclarationList();
    } : (d=varDecl {v.addDeclaration(d); } )*
    ; 


statementListProduction returns [StatementList s] 
    @init{
        s= new StatementList();
    } :  (t=statement{s.addStatement(t); } )* 
    ; 

id returns [Identifier i] : t=ID {
        i = new Identifier($t.text, $t.getLine(), $t.pos );
    }
    ;

varDecl returns [VariableDeclaration d]: c=compoundType i=id ';' 
    {
        d = new VariableDeclaration(c, i);
    }
    ;

statement returns [Statement s] options { backtrack=true; } : 
       e=emptyStatement         { s=e; }
    |  es=exprStatement         { s=es;}
    |  i=ifStatement            { s=i; }
    |  w=whileStatement         { s=w; }
    |  pl=printlnStatement      { s=pl;}
    |  p=printStatement         { s=p; }
    |  r=returnStatement        { s=r; }
    |  as=assignStatementSingle { s=as;}
    |  aa=assignStatementArray  { s=aa;}
    ;

emptyStatement returns [EmptyStatement s] : ';'
    {
        s = new EmptyStatement();
    }
    ;

exprStatement returns [ExpressionStatement s]:  e=expr ';'
    {
        s = new ExpressionStatement(e);
    }
    ;

ifStatement returns [IfStatement s] : 
        l=IF '(' e=expr ')' blk=block (ELSE blk2=block)?
    {
        s = new IfStatement(e, blk, blk2, $l.getLine());
    }
    ;

whileStatement returns [WhileStatement s] : l=WHILE '(' e=expr ')' blk=block
    {
        s = new WhileStatement(e, blk, $l.getLine());
    }
    ;

printlnStatement returns [PrintLnStatement p] : l=PRINTLN e=expr ';'
    {
        p = new PrintLnStatement(e, $l.getLine());
    }  
    ;

printStatement returns [PrintStatement s] : l=PRINT e=expr ';'
    {
        s = new PrintStatement(e,$l.getLine());
    }
    ;

returnStatement returns [ReturnStatement s]
    @init{
        s = new ReturnStatement();
    }
    @after{
        s.addLineOffset($l.getLine(), $l.pos);
    }
    : l = RETURN (e=expr{s.addExpression(e, $l.getLine(), $l.pos); })? ';'
    ;

assignStatementSingle returns [AssignmentStatement s] : i=id '=' e=expr ';'
    {
        s = new AssignmentStatement(i, e);
    }
    ;

assignStatementArray returns [ArrayAssignment a]: i=id lb='[' e1=expr ']' '=' e2=expr ';'
    {
        a = new ArrayAssignment(i, e1, e2, $lb.line, $lb.pos);
    }
    ;


block returns [Block b]
    @init {
        b = new Block();
    } : '{'  (s=statementListProduction{b.addStatementList(s); }) '}'
    ;

expr returns [Expression e] 
    @init {
        Expression it = null;
    }
    @after {
        e = it;
    } :  e1=lessThanExpr{ it=e1; }
            (l='=='  (e2=lessThanExpr{ it=new EqualityExpression(it, e2, $l.getLine(), $l.pos); }) )*
    ;


lessThanExpr returns [Expression e]
    @init {
        Expression it = null;
    }
    @after {
        e = it;
    }: (e1 = addSubExpr{ it =e1; }) 
            (l='<' (e2 = addSubExpr{ it = new LessThanExpression(it, e2, $l.getLine(), $l.pos); }) )*
    ;
    
addSubExpr returns [Expression e]
    @init {
        Expression it = null;
    }
    @after{
        e = it;
    }: (e1 = multExpr{ it=e1;}) ( (l='+' (e2=multExpr{ it=new AddExpression(it, e2, $l.getLine(), $l.pos); })) |
      (ll='-' (e2=multExpr{ it=new SubstractExpression(it, e2, $ll.getLine(), $ll.pos); })))*
    ;

multExpr returns [Expression e]
    @init{
        Expression it = null;
    }
    @after{
        e = it;
    }: ( e1=atom{it=e1;}) (l='*' (e2=atom{it=new MultExpression(it, e2, $l.getLine(), $l.pos);} ))*
    ;

atom returns [Expression e]:  
      i=id '[' exp=expr ']'      { e = new ArrayReference(i, exp);}
    | i=id lin='(' exl=exprList ')'  { e = new FunctionCall(i, exl, $lin.getLine()); }
    | i=id                       { e = i; }
    | l=literal                  { e = l; }
    | li='(' exp=expr ')'           { e = new ParenExpression(exp, $li.getLine());}
    ;

exprList returns [ExpressionList e]
    @init{
        e = new ExpressionList();
    }: (exp=expr{e.addFirstExpression(exp); }) 
        (expm=exprMore{e.addToExpressionList(expm); })*
    |
    ;

exprMore returns [Expression e]: ',' exp=expr
    {
        e = exp;
    }
    ;

literal returns [Expression e]:   
        i=INTEGERCONSTANT
    {
        e= new IntegerLiteral(Integer.parseInt($i.text), $i.getLine(), $i.pos);
    }
    |   s=STRINGCONSTANT
    {
        e= new StringLiteral($s.text, $s.getLine(), $s.pos);
    }
    |   c=CHARCONSTANT
    {
        e= new CharacterLiteral($c.text, $c.getLine(), $c.pos);
    }
    |   f=FLOATCONSTANT
    {
        e = new FloatLiteral($f.text, $f.getLine(), $f.pos);
    }
    |   t=TRUE
    {
        e = new BooleanLiteral($t.text, $t.getLine(), $t.pos);
    }
    |   f=FALSE
    {
        e = new BooleanLiteral($f.text, $f.getLine(), $f.pos);
    }
    ;

/* Lexer */

TYPE : 
      'int'
    | 'float'
    | 'char'
    | 'string'
    | 'boolean'
    | 'void'
    ;

RETURN : 'return'
    ;

PRINT : 'print'
    ;

PRINTLN : 'println'
    ;

WHILE : 'while'
    ;

TRUE :  'true'
    ;

FALSE : 'false'
    ;

IF  : 'if'
    ;

ELSE : 'else'
    ;

ID  : ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*
    ;
	
INTEGERCONSTANT : ( '0' | '1'..'9'('0'..'9')* )
    ;

STRINGCONSTANT : '"' ('a'..'z' | 'A'..'Z' | '_' | ' ' | '0'..'9' | '?' | '.' | ',' | '!')* '"'
    ;

CHARCONSTANT :  '\u0027' ('a'..'z'| 'A'..'Z' | ' ') '\u0027'
    ;

FLOATCONSTANT : '0'..'9'+ '.' '0'..'9'+
    ;

/* These two lines match whitespace and comments 
 * and ignore them.
 * You want to leave these as last in the file.  
 * Add new lexical rules above 
 */
WS      : ( '\t' | ' ' | ('\r' | '\n') )+ { $channel = HIDDEN; skip();}
        ;

COMMENT : '//' ~('\r' | '\n')* ('\r' | '\n') { $channel = HIDDEN;}
        ;
