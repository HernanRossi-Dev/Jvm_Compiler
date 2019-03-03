#!/bin/bash
echo "Running all 14 passing tests: "

echo "Test One: "
java Compiler testFolder/shouldPass/arrayTestPass.ul 
echo "Test Two: "
java Compiler testFolder/shouldPass/booleanTestPass.ul
echo "Test Three: "
java Compiler testFolder/shouldPass/charTestPass.ul 
echo "Test Four: "
java Compiler testFolder/shouldPass/exprAndCommentTestPass.ul 
echo "Test Five: "
java Compiler testFolder/shouldPass/exprListTestPass.ul 
echo "Test Six: "
java Compiler testFolder/shouldPass/exprEqualTestPass.ul 
echo "Test Seven: "
java Compiler testFolder/shouldPass/floatTestPass.ul 
echo "Test Eight: "
java Compiler testFolder/shouldPass/ifElseIntTestPass.ul 
echo "Test Nine: "
java Compiler testFolder/shouldPass/printStringTestPass.ul 
echo "Test Ten: "
java Compiler testFolder/shouldPass/emptyFunctionPass.ul 
echo "Test Eleven: "
java Compiler testFolder/shouldPass/returnTestPass.ul 
echo "Test Twelve: "
java Compiler testFolder/shouldPass/statementNoVarDeclTestPass.ul 
echo "Test Thirteen: "
java Compiler testFolder/shouldPass/threeEmptyFunctionPass.ul 
echo "Test Fourteen: "
java Compiler testFolder/shouldPass/threeFunctions.ul 
echo "Test Fifthteen: "
java Compiler testFolder/shouldPass/mathExpressionTest.ul 
echo "Test SixTeen: "
java Compiler testFolder/shouldPass/completePrettyPrintTest.ul 

