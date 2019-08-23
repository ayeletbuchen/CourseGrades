/*
 * Throw custom made exception if input is invalid.
 * Tell the user the valid range for input.
 */
package coursegrades;

import javax.swing.JOptionPane;

/**
 *
 * @author ayeletbuchen
 * @since 03-27-18
 * @version 1
 */
public class InvalidNumberSubmitted extends Exception {

    /**
     * 
     * @param validNr 
     */
    public InvalidNumberSubmitted(int validNr)
    {
        JOptionPane.showMessageDialog(null, "Invalid number.\n"
                + "Number must be between 0 and " + validNr + ".");
    }
}
