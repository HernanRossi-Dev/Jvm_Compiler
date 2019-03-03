#!/bin/bash
echo "Running all 41 Failing tests for Assignment 2: "

echo "Test One: "
java Compiler A2Tests/Fail/addExprFail.ul
echo "Test Two: "
java Compiler A2Tests/Fail/arrayAssignmentFailTwo.ul
echo "Test Three: "
java Compiler A2Tests/Fail/arrayAssignmentFailWrongAssignType.ul 
echo "Test Four: "
java Compiler A2Tests/Fail/arrayAssignmentFailWrongIndexType.ul
echo "Test Five: "
java Compiler A2Tests/Fail/arrayRefFailIndexTypeError.ul 
echo "Test Six: "
java Compiler A2Tests/Fail/arrayRefFailReturnIndexMissmatch.ul 
echo "Test Seven: "
java Compiler A2Tests/Fail/arrayTypeAssignmentFail.ul
echo "Test Eight: "
java Compiler A2Tests/Fail/arrayTypeAssignmentFailSizeMissmatch.ul
echo "Test Nine: "
java Compiler A2Tests/Fail/assignmentStmtFail.ul
echo "Test Ten: "
java Compiler A2Tests/Fail/assignmentStmtFailFunctParam.ul
echo "Test Eleven: "
java Compiler A2Tests/Fail/assignmentStmtFailVariableNotDefined.ul 
echo "Test Twelve: "
java Compiler A2Tests/Fail/complexAlgebraExpressionFail.ul 
echo "Test Thirteen: "
java Compiler A2Tests/Fail/declareVoidVarFail.ul
echo "Test Fourteen: "
java Compiler A2Tests/Fail/duplicateFunctionParameterNames.ul
echo "Test Fifthteen: "
java Compiler A2Tests/Fail/duplicateFunctNameFail.ul
echo "Test SixTeen: "
java Compiler A2Tests/Fail/duplicateVariableDeclFail.ul
echo "Test SevenTeen: "
java Compiler A2Tests/Fail/equalityExprFailTypeMissmatch.ul 
echo "Test Eighteen: "
java Compiler A2Tests/Fail/formalParamVoid.ul
echo "Test Nineteen: "
java Compiler A2Tests/Fail/functionCallArraySizeFail.ul 
echo "Test Twenty: "
java Compiler A2Tests/Fail/functionCallFailIncorrectFunctArgNum.ul
echo "Test TwentyOne: "
java Compiler A2Tests/Fail/printVoidFail.ul
echo "Test TwentyTwo: "
java Compiler A2Tests/Fail/functionCallFailTypeMissmatch.ul
echo "Test TwentyThree: "
java Compiler A2Tests/Fail/functionCallInWhileFail.ul
echo "Test TwentyFour: "
java Compiler A2Tests/Fail/functionFailDoesNotExist.ul
echo "Test TwentyFive: "
java Compiler A2Tests/Fail/ifStmtFail.ul 
echo "Test TwentySix: "
java Compiler A2Tests/Fail/lessthanFail.ul
echo "Test TwentySeven: "
java Compiler A2Tests/Fail/mainHasParamsFail.ul 
echo "Test TwentyEight: "
java Compiler A2Tests/Fail/mainNotVoidFail.ul 
echo "Test TwentyNine: "
java Compiler A2Tests/Fail/multExprFail.ul
echo "Test Thirty: "
java Compiler A2Tests/Fail/noFunctionFail.ul
echo "Test ThirtyOne: "
java Compiler A2Tests/Fail/noMainFail.ul
echo "Test ThirtyTwo: "
java Compiler A2Tests/Fail/returnSizeofReturnArrayMustMatchFuntionReturnTYpeSize.ul
echo "Test ThirtyThree: "
java Compiler A2Tests/Fail/returnTestFail.ul
echo "Test ThirtyFour: "
java Compiler A2Tests/Fail/returnVoidTestFail.ul
echo "Test ThirtyFive: "
java Compiler A2Tests/Fail/shadowParamName.ul 
echo "Test ThirtySix: "
java Compiler A2Tests/Fail/subExprFail.ul 
echo "Test ThirtySeven: "
java Compiler A2Tests/Fail/varMustBeDeclared.ul  
echo "Test ThirtyEight: "
java Compiler A2Tests/Fail/varMustBeDeclaredTwo.ul
echo "Test ThirtyNine: "
java Compiler A2Tests/Fail/voidFunctParamTwo.ul 
echo "Test Fourty: "
java Compiler A2Tests/Fail/voidVarDeclFail.ul
echo "Test FourtyOne: "
java Compiler A2Tests/Fail/whileStatementFail.ul
echo "Test FourtyTwo: "
java Compiler A2Tests/Fail/arrayRefonNonArrayTypeError.ul
echo "Test FourtyThree: "
java Compiler A2Tests/Fail/equalityExprFailArrayVariable.ul
echo "Test FourtyFour: "
java Compiler A2Tests/Fail/printlnStmtFailArrayVariable.ul