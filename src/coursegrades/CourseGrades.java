/*
 * Create an array to hold all graded activities
 */
package coursegrades;

/**
 *
 * @author ayeletbuchen
 * @since 03-27-18
 * @version 1
 */
public class CourseGrades {

    private final int ASSIGNMENT_TYPES = 3; //amount of assignment types

    // Create a ragged 2D array to hold all GradedActivity objects
    private GradedActivity[][] grades = new GradedActivity[ASSIGNMENT_TYPES][];
    private final int NR_PA = 5; //total number of programming assignments
    private final int NR_Q = 12; //total amount of quizzes
    private final int NR_F = 1;
    private final int PA_ORDINAL = 0;
    private final int Q_ORDINAL = 1;
    private final int FE_ORDINAL = 2;
    private final int COUNTED_QUIZZES = 8; //total amount of quizzes counted
    private final int POSSIBLE_POINTS = 300; //total possible points

    /**
     * Properly assign the ragged 2D array and initialize all grades to 0
     */
    public CourseGrades()
    {
        /*
         * In the array:
         * 1st row holds 5 programming assignment.
         * 2nd row holds 12 quizzes.
         * Last row holds final exam.
         */
        grades[PA_ORDINAL] = new GradedPA[NR_PA];
        grades[Q_ORDINAL] = new GradedQ[NR_Q];
        grades[FE_ORDINAL] = new GradedFE[NR_F];

        /*
         * Initialize all grades to 0 so that a grade can be calculated
         * even if some assignments have not yet been graded
         */
        for (int gr = 0; gr < ASSIGNMENT_TYPES; ++gr)
        {
            switch (gr)
            {
                case PA_ORDINAL:
                    for (int ix = 0; ix < NR_PA; ++ix)
                    {
                        grades[gr][ix] = new GradedPA(0);
                    }
                    break;
                case Q_ORDINAL:
                    for (int ix = 0; ix < NR_Q; ++ix)
                    {
                        grades[gr][ix] = new GradedQ(0);
                    }
                    break;
                default:
                    grades[gr][0] = new GradedFE(0);
                    break;
            }
        }
    }

    /**
     * Create a programming assignment (GradedPA) and set it into the array
     *
     * @param grade
     * @param ordinal
     * @return true if ordinal and grade specified are valid. Otherwise return
     * false
     */
    public boolean setGradeProg(int grade, int ordinal)
    {
        grades[PA_ORDINAL][ordinal] = new GradedPA(grade);
        return (validNumber(ordinal, (NR_PA - 1)) && validNumber(grade,
                GradedPA.getMaxScore()));
    }

    /**
     * If a programming assignment was resubmitted, set the resubmitted grade
     * into the appropriate GradedPA object, as specified by which ordinal in
     * the array the object is located in
     *
     * @param grade
     * @param ordinal
     * @param resubmission
     * @return true if ordinal, original grade, and resubmission grade (if there
     * is one) are valid. Otherwise, return false.
     */
    public boolean setGradeProg(int grade, int ordinal, boolean resubmission)
    {
        if (resubmission)
        {
            GradedPA program = (GradedPA) grades[PA_ORDINAL][ordinal];
            program.setGrade2(grade);
            grades[PA_ORDINAL][ordinal] = program;
        }

        //If assignment was not resubmitted, set assignment through first 
        //setGradeProg method, which does not require a boolean parameter
        else
        {
            setGradeProg(grade, ordinal);
        }
        return (validNumber(ordinal, NR_PA) && validNumber(grade,
                GradedPA.getMaxScore()));
    }

    /**
     * Create a quiz (GradedQ) and set it into the array
     *
     * @param grade
     * @param ordinal
     * @return true if ordinal and grade specified are valid. Return false
     * otherwise.
     */
    public boolean setGradeQuiz(int grade, int ordinal)
    {
        grades[Q_ORDINAL][ordinal] = new GradedQ(grade);
        return (validNumber(ordinal, NR_Q) && validNumber(grade,
                GradedQ.getMaxScore()));
    }

    /**
     * Create final exam (GradedFE) and set it into the array
     *
     * @param grade
     * @param bonus
     * @return true if grade and extra credit are valid grades
     */
    public boolean setGradeFinal(int grade, int bonus)
    {

        GradedFE exam = new GradedFE(grade);
        exam.setExtraCredit(bonus);
        grades[FE_ORDINAL][0] = exam;
        return (validNumber(grade, (GradedFE.getMaxScore() - bonus))
                && validNumber(bonus, (GradedFE.getMaxScore() - grade)));
    }

    /**
     *
     * @param number
     * @param max
     * @return true if number specified is valid (at least 0 and not greater
     * than max). Return false otherwise
     */
    private static boolean validNumber(int number, int max)
    {
        return (number >= 0 && number <= max);
    }

    /**
     *
     * @return the weighted grade for the class so far
     */
    public int getWeightedGrade()
    {
        int weightedGrade = 0;      //to hold weighted grade

        //get points received from programming assignments
        weightedGrade += getPAGrades();

        //get points received from quizzes
        weightedGrade += getQuizGrades();

        //get points received from final exam
        weightedGrade += getExamGrade();
        return weightedGrade;
    }

    /**
     *
     * @return String explaining the weighted grade for the class
     */
    @Override
    public String toString()
    {
        return "Your current weighted grade for the class is "
                + getWeightedGrade() + " points.\n"
                + "(Out of " + POSSIBLE_POINTS
                + " possible points to earn this semester)";
    }

    /**
     *
     * @return the points received from programming assignments
     */
    private int getPAGrades()
    {
        ////programGrades holds points recieved from programming assignments
        int programGrades = 0;

        /*
         * For each programming assignment, collect the grade.
         */
        for (int ix = 0; ix < NR_PA; ++ix)
        {
            GradedPA pa = (GradedPA) grades[PA_ORDINAL][ix];
            /*
             * Calculate grade based on the higher grade, whether it is the
             * original grade or a resubmission
             *
             * Although programming assignments are graded out of 100, when
             * calculatng the weighted grade, each assignment is worth 20
             * points, so divide the grade by 5
             */
            if (pa.getGrade2() > pa.getGrade())
            {
                programGrades += (pa.getGrade2() / NR_PA);
            }
            else
            {
                programGrades += (pa.getGrade() / NR_PA);
            }
        }
        return programGrades;
    }

    /**
     *
     * @return points received from quizzes
     */
    private int getQuizGrades()
    {
        int quizGrades = 0;
        /**
         * Sort the quizzes so that they are stored in the array in order of
         * descending grades
         */
        for (int ix = 0; ix < NR_Q; ++ix)
        {
            for (int ii = 0; ii < NR_Q; ++ii)
            {
                if (grades[Q_ORDINAL][ix].getGrade() > grades[1][ii].getGrade())
                {
                    //GradedQ placeHolder serves as a placeholder while 
                    //quizzes are being sorted
                    GradedQ placeHolder = new GradedQ(grades[Q_ORDINAL][ix].getGrade());
                    grades[Q_ORDINAL][ix] = new GradedQ(grades[1][ii].getGrade());
                    grades[Q_ORDINAL][ii] = new GradedQ(placeHolder.getGrade());
                }
            }
        }

        /*
         * Only count the top 8 quizzes
         */
        for (int ix = 0; ix < COUNTED_QUIZZES; ++ix)
        {
            quizGrades += (grades[Q_ORDINAL][ix].getGrade());
        }
        return quizGrades;
    }

    /**
     *
     * @return the points received from the final exam
     */
    private int getExamGrade()
    {
        GradedFE fe = (GradedFE) grades[FE_ORDINAL][0];
        int examGrade = (fe.getGrade() + fe.getExtraCredit());
        return examGrade;
    }
}
