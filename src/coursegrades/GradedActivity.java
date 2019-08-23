/*
 * Create a new graded activity object
 */
package coursegrades;

/**
 *
 * @author ayeletbuchen
 * @since 03-27-18
 * @version 1
 */
public class GradedActivity {

    private int grade;

    /**
     * 
     * @param gr 
     */
    public GradedActivity(int gr)
    {
        grade = gr;
    }

    public int getGrade()
    {
        return grade;
    }

    /**
     * 
     * @param gr 
     */
    public void setGrade(int gr)
    {

        grade = gr;
    }
}
