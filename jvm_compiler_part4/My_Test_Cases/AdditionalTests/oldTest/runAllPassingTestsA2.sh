#!/bin/bash
echo "Running all 14 passing tests: "

echo "Test One: "
java Compiler A2Tests/Pass/addExprPass.ul
echo "Test Two: "
java Compiler A2Tests/Pass/arrayAssignmentPass.ul
echo "Test Three: "
java Compiler A2Tests/Pass/arrayRefPass.ul
echo "Test Four: "
java Compiler A2Tests/Pass/arrayTypeAssignmentPass.ul
echo "Test Five: "
java Compiler A2Tests/Pass/assignmentStmtPass.ul 
echo "Test Six: "
java Compiler A2Tests/Pass/complexAlgebraExpressionPass.ul
echo "Test Seven: "
java Compiler A2Tests/Pass/complexParansAlgebraExpressionPass.ul
echo "Test Eight: "
java Compiler A2Tests/Pass/equalityExprPass.ul
echo "Test Nine: "
java Compiler A2Tests/Pass/functionCallOnePass.ul
echo "Test Ten: "
java Compiler A2Tests/Pass/functionCallWithArrayRefPass.ul
echo "Test Eleven: "
java Compiler A2Tests/Pass/ifStmtPass.ul 
echo "Test Twelve: "
java Compiler A2Tests/Pass/lessthanPass.ul
echo "Test Thirteen: "
java Compiler A2Tests/Pass/multExprPass.ul
echo "Test Fourteen: "
java Compiler A2Tests/Pass/printTestPass.ul
echo "Test Fifthteen: "
java Compiler A2Tests/Pass/printlnTestPass.ul
echo "Test SixTeen: "
java Compiler A2Tests/Pass/returnTestPass.ul
echo "Test SevenTeen: "
java Compiler A2Tests/Pass/returnVoidTestPass.ul 
echo "Test Eighteen: "
java Compiler A2Tests/Pass/subExprPass.ul
echo "Test Nineteen: "
java Compiler A2Tests/Pass/whileStatementPass.ul
echo "Test Twenty: "
java Compiler A2Tests/Pass/factorial.ul
echo "Test Twenty One"
java Compiler A2Tests/Pass/varCanHideFunctionName.ul
echo "Test Twenty Two"
java Compiler A2Tests/Pass/arrayRefPassIndexOutOfrange.ul
echo "Test Twenty Three"
java Compiler A2Tests/Pass/WhileStmtpassBoolVar.ul