/*
 * Create a new final exam object
 */
package coursegrades;

/**
 *
 * @author ayeletbuchen
 * @since 03-27-18
 * @version 1
 */
public class GradedFE extends GradedActivity {

    private static final int MAX_SCORE = 120;
    private int extraCredit;

    /**
     * 
     * @param gr 
     */
    public GradedFE(int gr)
    {
        super(gr);
        extraCredit = 0;
    }

    public int getExtraCredit()
    {
        return extraCredit;
    }

    /**
     * 
     * @param ec 
     */
    public void setExtraCredit(int ec)
    {
        extraCredit = ec;
    }

    /**
     * Public method that can be used without instance of GradedFE to get
     * MAX_SCORE
     * 
     * @return the maximum score of final exam
     */
    public static int getMaxScore()
    {
        return MAX_SCORE;
    }
}
