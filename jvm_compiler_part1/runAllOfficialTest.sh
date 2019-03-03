#!/bin/bash
printf "Running all official tests:\n"
printf "RUNNING ALL VALID TESTS\n"
printf "\nfactorial.ul:\n"
java Compiler tests/factorial.ul
printf "\nvalid1.ul: \n"
java Compiler tests/valid1.ul 
printf "\nvalid2.ul: \n"
java Compiler tests/valid2.ul 
printf "\nvalid3.ul: \n"
java Compiler tests/valid3.ul 
printf "\nvalid4.ul: \n"
java Compiler tests/valid4.ul 
printf "\nvalid5.ul: \n"
java Compiler tests/valid5.ul 
printf "\nvalid6.ul: \n"
java Compiler tests/valid6.ul 
printf "\n"
printf "END OF ALL PASSING TEST NOW RUNNING FAILING TESTS"
printf "\ninvalid1.ul : \n"
java Compiler tests/invalid1.ul 
printf "\ninvalid2.ul : \n"
java Compiler tests/invalid2.ul 
printf "\ninvalid3.ul : \n"
java Compiler tests/invalid3.ul 
printf "\ninvalid4.ul : \n"
java Compiler tests/invalid4.ul 
printf "\nEND OF ALL OFFICIAL TESTS\n\n"
