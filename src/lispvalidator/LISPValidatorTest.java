// 
/**
 *   class LISPValidatorTest.java (Version 1.0.0.0)
 * 
 *   LISPValidatorTest class is for unit testing.
 */

package lispvalidator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/////////////////////////////////////////////////////////////////////////////
class LISPValidatorTest {

    //-----------------------------------------------------------------------
    @Test
    void testEmptyFilePath() {

        try {
            LISPValidator lispValidator = new LISPValidator();
            assertTrue(lispValidator.validateLISPFile(""));
        } catch ( IOException ioe ) {
            System.out.println (ioe.toString());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    void testLispStartsWithOpenedParentheses() {

        try {
            LISPValidator lispValidator = new LISPValidator();

            String lispFilePath = "test/test.lisp";
            String lispCode = ")(a + b)(";

            if ( createLispFile(lispFilePath, lispCode) ) {
                assertTrue(lispValidator.validateLISPFile(lispFilePath));
            }
        } catch ( IOException ioe ) {
            System.out.println (ioe.toString());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    void testLispForNotClosedParentheses() {

        try {
            LISPValidator lispValidator = new LISPValidator();

            String lispFilePath = "test/test.lisp";
            String lispCode = "((a + b) ; comment";

            if ( createLispFile(lispFilePath, lispCode) ) {
                assertTrue(lispValidator.validateLISPFile(lispFilePath));
            }
        } catch ( IOException ioe ) {
            System.out.println (ioe.toString());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    void testLispForCommentParentheses() {

        try {
            LISPValidator lispValidator = new LISPValidator();

            String lispFilePath = "test/test.lisp";
            String lispCode = "((a + b)) ; comment ((((";

            if ( createLispFile(lispFilePath, lispCode) ) {
                assertTrue(lispValidator.validateLISPFile(lispFilePath));
            }
        } catch ( IOException ioe ) {
            System.out.println (ioe.toString());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    void testLispForQuotesParentheses() {

        try {
            LISPValidator lispValidator = new LISPValidator();

            String lispFilePath = "test/test.lisp";
            String lispCode = "(\" )  \")";

            if ( createLispFile(lispFilePath, lispCode) ) {
                assertTrue(lispValidator.validateLISPFile(lispFilePath));
            }
        } catch ( IOException ioe ) {
            System.out.println (ioe.toString());
        }
    }

    //-----------------------------------------------------------------------
    boolean createLispFile(String lispFilePath, String lispCode) throws IOException {

        boolean isSuccess = false;

        if ( !lispFilePath.isEmpty() ) {
            File file = new File(lispFilePath);

            if ( !file.exists() ) {
                file.createNewFile();
            }

            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(file)) ) {
                bw.write(lispCode);
                isSuccess = true;
            }
        }

        return isSuccess;
    }

    //-----------------------------------------------------------------------

}