#!/bin/bash
echo "Running all 16 failing tests: "
echo "Test One: "
java Compiler testFolder/shouldFail/arrayTestFailOne.ul
echo "Test Two: "
java Compiler testFolder/shouldFail/arrayTestFailThree.ul 
echo "Test Three: "
java Compiler testFolder/shouldFail/emptyFileTestFail.ul 
echo "Test Four: "
java Compiler testFolder/shouldFail/floatTestFailOne.ul 
echo "Test Five: "
java Compiler testFolder/shouldFail/floatTestFailTwo.ul 
echo "Test Six: "
java Compiler testFolder/shouldFail/floatTestFailThree.ul 
echo "Test Seven: "
java Compiler testFolder/shouldFail/functionfail.ul 
echo "Test Eight: "
java Compiler testFolder/shouldFail/functionNoEOF.ul 
echo "Test Nine: "
java Compiler testFolder/shouldFail/ifTestFail.ul 
echo "Test Ten: "
java Compiler testFolder/shouldFail/intTestFailOne.ul 
echo "Test Eleven: "
java Compiler testFolder/shouldFail/intTestFailTwo.ul 
echo "Test Twelve: "
java Compiler testFolder/shouldFail/arrayTestFailTwo.ul 
echo "Test Thirteen: "
java Compiler testFolder/shouldFail/noFunctionFail.ul 
echo "Test Fourteen: "
java Compiler testFolder/shouldFail/stringTestFailOne.ul 
echo "Test Fifthteen: "
java Compiler testFolder/shouldFail/stringTestFailTwo.ul 
echo "Test Sixteen: "
java Compiler testFolder/shouldFail/whileTestFail.ul 

