// 
/**
 *   class LISPValidator.java (Version 1.0.0.0)
 * 
 *   LISPValidator class reads .LISP files from the input directory,
 *   Checks if all parentheses are properly closed and nested,
 *   Prints out message whether the content of the lisp file is valid.
 */

package lispvalidator;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


///////////////////////////////////////////////////////////////////////////////
public class LISPValidator {

    static final String INPUT_DIR = "input";
    static final String FILE_EXT = ".lisp";

    //---------------------------------------------------------------------------
    public static void main(String[] args) {

        LISPValidator lispValidator = new LISPValidator();

        try {
            // check if input directory exists
            File dir = new File(INPUT_DIR);
            if ( !dir.exists() ) {
                System.out.println(INPUT_DIR + " directory does not exists!");
            } else {
                File[] files = new File(INPUT_DIR).listFiles();
                for ( File file: files ) {
                    if ( file.isFile() ) {

                        // make sure it is .LISP file
                        if ( file.getName().toLowerCase().endsWith(FILE_EXT) ) {
                            String lispFileName = file.getName();
                            if ( lispValidator.validateLISPFile(INPUT_DIR + "/" + lispFileName) ) {
                                System.out.println(lispFileName + " is valid.");
                            } else {
                                System.out.println(lispFileName + " is not valid.");
                            }
                        }
                    }
                }
            }
        } catch ( IOException ioe ) {
            System.out.println("A problem occurred reading *.lisp file!");
        }
    }

    //---------------------------------------------------------------------------
    protected boolean validateLISPFile(String lispFilePath) throws IOException {

        boolean isValid = false;

        if ( !lispFilePath.isEmpty() ) {
            int opened = 0; // opened parentheses count
            int closed = 0; // closed parentheses count

            try ( BufferedReader br = new BufferedReader(new FileReader(lispFilePath)) ) {
                String line = "";
                while ( (line = br.readLine()) != null && opened >= closed ) {
                    boolean isQuotes = false;
                    boolean isEscape = false;

                    for ( int i = 0; i < line.length() && opened >= closed; i++ ) {
                        char c = line.charAt(i);

                        // ignore escaped char
                        if ( isEscape ) {
                            isEscape = false;
                        } else {
                            if ( c == '"' ) {
                                isQuotes = !isQuotes;
                            } else {

                                // content within the quotes could have parentheses characters as well
                                if ( isQuotes ) {

                                    // keep in mind that quotes characters could be escaped within the quotes
                                    if ( c == '\\' ) {
                                        isEscape = true;
                                    }
                                } else {

                                    // ignore text after comment character
                                    if ( c == ';' ) {
                                        break;
                                    } else {
                                        if ( c == '(' ) {
                                            opened++;
                                        } else if ( c == ')' ) {
                                            closed++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // make sure there is at least one parentheses pair and they are equal
            isValid = ( opened + closed > 0 && opened == closed );
        } else {
            System.out.println("Filename is empty.");
        }

        return isValid;
    }

    //---------------------------------------------------------------------------

}