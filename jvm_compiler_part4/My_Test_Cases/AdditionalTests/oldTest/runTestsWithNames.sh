printf "Running all official passing tests: "

printf "\n2.1_valid.ul: "
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.1_valid.ul 
printf "\n2.5_valid.ul: "
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.2.5_valid.ul 
printf "\n2.2_valid.ul: "
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.2_valid.ul
printf "\n3.1_valid.ul: "
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.1_valid.ul 
printf "\n3.2_valid.ul: "
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.2_valid.ul 
printf "\n3.3_valid.ul: "
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.3_valid.ul
printf "\n\nRunning all official failing tests: "

printf "\nshould fail before type checking: lexer error: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.1.1_invalid.ul
printf "\nshould fail line 1 offset 10: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.1.2a_invalid.ul
printf "\nshould fail line 1 offset 0: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.1.2b_invalid.ul
printf "\nshould fail line 9 offset 5: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.1.3_invalid.ul 
printf "\nshould fail line 5 offset 19: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.2.1_invalid.ul
printf "\nshould fail line 7 offset 8: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.2.2_invalid.ul
printf "\nshould fail line 5 offset 8: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.2.3_invalid.ul
printf "\nshould fail line 6 offset 4: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.2.4_invalid.ul
printf "\nshould fail line 6 offset 8: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.2.6_invalid.ul
printf "\nshould fail line 6 offset 4: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_2.2.7_invalid.ul
printf "\nshould fail line 3 offset 8: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.1.1_invalid.ul
printf "\nshould fail line 3 offset 11: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.1.2_invalid.ul
printf "\nshould fail line 2 offset 10: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.1.3_invalid.ul
printf "\nshould fail line 2 offset 12: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.1.4_invalid.ul
printf "\nshould fail line 8 offset 11: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.1.5_invalid.ul
printf "\nshould fail line 4 offset 8: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.1.6_invalid.ul
printf "\nshould fail line 3 offset 12: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.2.1_invalid.ul
printf "\nshould fail line 5 offset 10: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.2.2.a_invalid.ul
printf "\nshould fail line 10 offset 12: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.2.2.b_invalid.ul
printf "\nshould fail line 6 offset 12: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.2.2.c_invalid.ul
printf "\nshould fail line 6 offset 10: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.2.2.d_invalid.ul
printf "\nshould fail line 6 offset 12: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.2.2.e_invalid.ul
printf "\nshould fail line 2 offset 4: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.3.a_invalid.ul
printf "\nshould fail line 4 offset 4: \n"
java Compiler OfficialA2TestCases/withoutSubtypes/woSt_3.3.b_invalid.ul