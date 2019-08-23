/*
 * Create a programming assignment object
 */
package coursegrades;

/**
 *
 * @author ayeletbuchen
 * @since 03-27-18
 * @version 1
 */
public class GradedPA extends GradedActivity {

    private static final int MAX_SCORE = 100;
    private int grade2;

    /**
     * 
     * @param gr 
     */
    public GradedPA(int gr)
    {
        super(gr);
        grade2 = 0;
    }

    public int getGrade2()
    {
        return grade2;
    }

    /**
     * 
     * @param gr2 
     */
    public void setGrade2(int gr2)
    {
        grade2 = gr2;
    }

    /**
     * Public method that can be used without instance of GradedPA to get
     * MAX_SCORE
     * 
     * @return the maximum score of a programming assignment
     */
    public static int getMaxScore()
    {
        return MAX_SCORE;
    }
}
