/*
 * Create a new quiz
 */
package coursegrades;

/**
 *
 * @author ayeletbuchen
 * @since 03-27-18
 * @version 1
 */
public class GradedQ extends GradedActivity {

    private static final int MAX_SCORE = 10;

    /**
     * 
     * @param gr 
     */
    public GradedQ(int gr)
    {
        super(gr);
    }
    
    /**
     * Public method that can be used without instance of GradedQ to get
     * MAX_SCORE
     * 
     * @return the maximum score of a quiz
     */
    public static int getMaxScore()
    {
        return MAX_SCORE;
    }
}
