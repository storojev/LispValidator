# LispValidator
Java Project

It is assumed that JDK is installed on the machine.

Download LispValidator folder to your local machine.

From command prompt:
  - Navigate to LispValidator directory
  - Compile the program from LispValidator directory with command: javac -d ./bin/ ./src/lispvalidator/LISPValidator.java
  - Run the program from LispValidator directory with command: java -classpath bin lispvalidator/LISPValidator

For using IDE the source files are located in LISPValidator/src folder.

The LISPValidator program
  - Reads .lisp file(s) from input folder
  - Checks if all parentheses are properly closed and nested
  - Prints out message whether the content of the lisp file is valid
