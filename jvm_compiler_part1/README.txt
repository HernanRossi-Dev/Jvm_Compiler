README: 
Student: Hernan Rossi

******************************************************
IMPORTANT: CLASSPATH must be properly set for antlr to be detected when $ make is run

Cd into main folder: HernanRossicsc435A1Part2.tar.gz
Create lexer and parser files:

	Run Command: $ make

Run all of the tests that should pass without errors: (Repeat for each test in the folder, replace testName with desired test to run)

	Run Command: $ java Compiler /testFolder/shouldPass/testName.ul

Run all of the tests that should fail due to errors during lexical analysis:
	
	Run Command: $ java Compiler /testFolder/shouldFail/testName.ul

Run all tests scripts: 

    Alternatively you can run all of the tests using the bash scripts provided from the main directory(Note this may or may not work depending on your environment)

    //runs all of my custom passing tests
   $ bash runAllPassingTest.sh

   //runs all of my custom failing tests
   $ bash runAllFailingTest.sh

   // runs all of the tests provided by instructor
   $ bash runAllOfficialTest.sh

