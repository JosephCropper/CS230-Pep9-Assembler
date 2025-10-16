# CS230-Pep9-Assembler
Simple Pep9 Assembler written in java for cs 230


## Main
- Prompts and Recieves user input
- Sends input to "Run.java"

## Run
- Recieves input from "Main.java"
- Checks input versus command list
- Runs valid commands via "Compiler.java"

## Compiler
- Recieves commands via "Run.java"
- Loads valid file as commanded
- Translates content of files by breaking each line into strings and examining the properties within substrings, looking for action identifiers ":", ",", "x"
- Has a method to negate use of spaces in computation

