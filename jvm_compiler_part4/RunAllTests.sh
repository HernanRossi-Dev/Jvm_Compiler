#!/bin/bash
printf "Running all tests: "

printf "\n\nProgram Name = arrayFunction : Program output below: \n"
java Compiler My_Test_Cases/arrayFunction.ul 
java jasmin.Main arrayFunction.j
java arrayFunction

printf "\n\nProgram Name = arrayFunctionSimple : Program output below:\n"
java Compiler My_Test_Cases/arrayFunctionSimple.ul 
java jasmin.Main arrayFunctionSimple.j
java arrayFunctionSimple

printf "\n\nProgram Name = arrayFunctionSimpleFloat : Program output below:\n"
java Compiler My_Test_Cases/arrayFunctionSimpleFloat.ul 
java jasmin.Main arrayFunctionSimpleFloat.j
java arrayFunctionSimpleFloat

printf "\n\nProgram Name = arrayFunctionSimpleInt : Program output below:\n"
java Compiler My_Test_Cases/arrayFunctionSimpleInt.ul 
java jasmin.Main arrayFunctionSimpleInt.j
java arrayFunctionSimpleInt

printf "\n\nProgram Name = arrayParamPass : Program output below:\n"
java Compiler My_Test_Cases/arrayParamPass.ul 
java jasmin.Main arrayParamPass.j
java arrayParamPass

printf "\n\nProgram Name = factorial : Program output below:\n"
java Compiler My_Test_Cases/factorial.ul 
java jasmin.Main factorial.j
java factorial

printf "\n\nProgram Name = fibonacci : Program output below:\n"
java Compiler My_Test_Cases/fibonacci.ul 
java jasmin.Main fibonacci.j
java fibonacci

printf "\n\nProgram Name = heapsort : Program output below:\n"
java Compiler My_Test_Cases/heapsort.ul 
java jasmin.Main heapsort.j
java heapsort

printf "\n\nProgram Name = helloworld : Program output below:\n"
java Compiler My_Test_Cases/helloworld.ul 
java jasmin.Main helloworld.j
java helloworld

printf "\n\nProgram Name = lotsOfParameters : Program output below:\n"
java Compiler My_Test_Cases/lotsOfParameters.ul 
java jasmin.Main lotsOfParameters.j
java lotsOfParameters

printf "\n\nProgram Name = Mathul : Program output below: \n"
java Compiler My_Test_Cases/Mathul.ul 
java jasmin.Main Mathul.j
java Mathul


printf "\n\nProgram Name = quicksort : Program output below:\n"
java Compiler My_Test_Cases/quicksort.ul 
java jasmin.Main quicksort.j
java quicksort


printf "\n\nProgram Name = sieveOfEratosthenes : Program output below:\n"
java Compiler My_Test_Cases/sieveOfEratosthenes.ul 
java jasmin.Main sieveOfEratosthenes.j
java sieveOfEratosthenes


printf "\n\nProgram Name = temp : Program output below:\n"
java Compiler My_Test_Cases/temp.ul 
java jasmin.Main temp.j
java temp

printf "\n\nProgram Name =  temp2 : Program output below:\n"
java Compiler My_Test_Cases/temp2.ul 
java jasmin.Main temp2.j
java temp2

printf "\n\nProgram Name = towerOfHanoi : Program output below:\n"
java Compiler My_Test_Cases/towerOfHanoi.ul 
java jasmin.Main towerOfHanoi.j
java towerOfHanoi

printf "\n\nProgram Name = plusCharString : Program output below:\n"
java Compiler My_Test_Cases/plusCharString.ul
java jasmin.Main plusCharString.j
java plusCharString

printf "\n\nProgram Name = plentyOfFunctions : Program output below:\n"
java Compiler My_Test_Cases/plentyOfFunctions.ul
java jasmin.Main plentyOfFunctions.j
java plentyOfFunctions

printf "\n\nProgram Name = lotsOfMath : Program output below:\n"
java Compiler My_Test_Cases/lotsOfMath.ul
java jasmin.Main lotsOfMath.j
java lotsOfMath

printf "\n\nProgram Name = printlnItTest : Program output below:\n"
java Compiler My_Test_Cases/printlnItTest.ul
java jasmin.Main printlnItTest.j
java printlnItTest

printf "\n\nInstructors Tests below:\n"
printf "\n\nProgram Name = ar : \n"
java Compiler Instructors_Tests/ar.ul
java jasmin.Main ar.j
java ar

printf "\n\nProgram Name = ar2 : \n"
java Compiler Instructors_Tests/ar2.ul
java jasmin.Main ar2.j
java ar2

printf "\n\nProgram Name = ar : \n"
java Compiler Instructors_Tests/ar.ul
java jasmin.Main ar.j
java ar

printf "\n\nProgram Name = expr : \n"
java Compiler Instructors_Tests/expr.ul
java jasmin.Main expr.j
java expr

printf "\n\nProgram Name = fact : \n"
java Compiler Instructors_Tests/fact.ul
java jasmin.Main fact.j
java fact

printf "\n\nProgram Name = foo : \n"
java Compiler Instructors_Tests/foo.ul
java jasmin.Main foo.j
java foo

printf "\n\nProgram Name = foo2 : \n"
java Compiler Instructors_Tests/foo2.ul
java jasmin.Main foo2.j
java foo2

printf "\n\nProgram Name = foo3 : \n"
java Compiler Instructors_Tests/foo3.ul
java jasmin.Main foo3.j
java foo3

printf "\n\nProgram Name = foo4 : \n"
java Compiler Instructors_Tests/foo4.ul
java jasmin.Main foo4.j
java foo4

printf "\n\nProgram Name = if : \n"
java Compiler Instructors_Tests/if.ul
java jasmin.Main if.j
java if

printf "\n\nProgram Name = ar : \n"
java Compiler a3_tests/a3_tests/ar.ul
java jasmin.Main ar.j
java ar

printf "\n\nProgram Name = expr : \n"
java Compiler a3_tests/a3_tests/expr.ul
java jasmin.Main expr.j
java expr

printf "\n\nProgram Name = factorial : \n"
java Compiler a3_tests/a3_tests/factorial.ul
java jasmin.Main factorial.j
java factorial

printf "\n\nProgram Name = if : \n"
java Compiler a3_tests/a3_tests/if.ul
java jasmin.Main if.j
java if

printf "\n\nProgram Name = towersOfHanoi : \n"
java Compiler a3_tests/a3_tests/towersOfHanoi.ul
java jasmin.Main towersOfHanoi.j
java towersOfHanoi





