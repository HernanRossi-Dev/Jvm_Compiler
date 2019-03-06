README: 
Student: Hernan Rossi


******************************************************
IMPORTANT: CLASSPATH must be properly set for antlr to be detected when $ make is run

Cd into main folder: 
Create lexer and parser files:
From within the main directory HernanRossiAssignment4:

	Run Command: $ make

	There is a script to run all of the test that I have:

	:$ bash RunAllTests.sh

 My test cases are in folder /My_Test_Cases, in this folder I have 11 programs that produce output to standard out when run including an implementation of heapsort, quicksort, towers of hanoi, sieve of erastothenes, fibonacci and a factorial program. The folder Non_Output_Tests includes additional tests cases created for unit testing which do not create output. These will need to be compiled then input into jasmin to get the .class file then run using java.

To Run a specific test such as my quicksort implementation:

	 $ java Compiler My_Test_Cases/quicksort.ul

This will create the new file "quicksort.j" in the main directory /HernanRossiAssignment4/, this file can be run through jasmin to get a .class file and then ran using 

$java quicksort
