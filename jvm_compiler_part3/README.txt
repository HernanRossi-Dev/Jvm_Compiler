README: 
Student: Hernan Rossi
ID: V00767958

Class CSC435 Assignemnt 3 Due March 19th

******************************************************
IMPORTANT: CLASSPATH must be properly set for antlr to be detected when $ make is run

Cd into main folder: 
Create lexer and parser files:
From within the main directory HernanRossiAssignment3:

	Run Command: $ make

 My test cases are in folder /My_Test_Cases, in this folder I have 11 programs that produce output to standard out when run including an implementation of heapsort, quicksort, towers of hanoi, sieve of erastothenes, fibonacci and a factorial program. The forlder Non_Output_Tests include 18 additional tests cases created for unit testing the IR visitor and do not create output. These will need to be compiled then put through the code generator provided by the instructor, input into jasmin to get the .class file then run using java.

To Run a specific test such as my quicksort implementation:

	 $ java Compiler My_Test_Cases/quicksort.ul

This will create the new file "quicksort.ir" in the main directory /HernanRossiAssignment3/