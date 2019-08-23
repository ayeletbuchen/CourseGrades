/*
 * This program calculates a student's weighted grade for a programming class.
 */
package coursegrades;

import javax.swing.JOptionPane;

/**
 * @author ayeletbuchen
 * @since 03-27-18
 * @version 1
 */
public class GradeDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JOptionPane.showMessageDialog(
                null, "Let's calculate your current "
                + "grade for your programming class.");

        //Get the necessary information from the user and store it in
        //CourseGrades object
        CourseGrades courseGrades = setGrades();

        //Get the weighted grade from CourseGrades object and print it
        JOptionPane.showMessageDialog(null, courseGrades.toString());
    }

    /*
     * Initialize new CourseGrades object to hold an array of
     * all graded activities.
     * 
     * Assign programming assignments to the CourseGrades object,
     * then use the CourseGrades object with programming assignments to add in
     * quizzes, then use the CourseGrades object with programming assignments
     * and quizzes to add in the final exam.
     */
    private static CourseGrades setGrades()
    {
        CourseGrades courseGrades = new CourseGrades();
        courseGrades = setProgrammingAssignments(courseGrades);
        courseGrades = setQuizzes(courseGrades);
        courseGrades = setFinal(courseGrades);
        return courseGrades;
    }

    /**
     *
     * @param courseGrades
     * @return object courseGrades with programming assignments set into the
     * array of graded activities
     */
    private static CourseGrades
            setProgrammingAssignments(CourseGrades courseGrades)
    {
        final int NR_PA = 5;    //total number of programming assignments
        //boolean valid is true if grade is valid and assigned to a 
        //valid ordinal in CourseGrades object
        boolean valid;
        boolean resubmitted;    //true if user resubmitted assignment
        int programs;           //to hold amount of graded programs
        String input;           //to hold user's input

        /*
        *Countinuously ask user for amount of graded programs until user
        *enters a valid number
         */
        do
        {
            input = JOptionPane.showInputDialog("How many programming "
                    + "assignments have been graded so far?\nThere are " + NR_PA
                    + " programming assignments total for the semester.\n\n");
            programs = getNumber(input, NR_PA);
        } while (!(programs >= 0 && programs <= NR_PA));

        /*
         *For each graded programming assignment, ask the user what grade he
         *received until a valid grade is entered
         */
        for (int ix = 0; ix < programs; ++ix)
        {
            do
            {
                input = JOptionPane.showInputDialog("What grade did you "
                        + "receive on Programming Assignment "
                        + (ix + 1) + "?\n\n");

                //set programming assignment to CourseGrade object
                //valid = true if program grade and ordinal in CourseGrades 
                //object are both valid
                valid = courseGrades.setGradeProg(getNumber(input,
                        GradedPA.getMaxScore()), ix);
            } while (!valid);

            /*
             * If the user resubmitted the assignment, get the resubmission 
             * grade and assign it appropriately in courseGrades
             * 
             * Continuously ask if assignment was
             * resubmitted until input is received. Input is necessary so 
             * that the first char of input can be retrieved
             */
            do
            {
                input = JOptionPane.showInputDialog("Did you resubmit this "
                        + "assignment? (y/n)\n\n");
            } while (input.equals(""));

            //if user resubmimtted assignment
            if (input.charAt(0) == 'y' || input.charAt(0) == 'Y')
            {
                resubmitted = true;
                //Ask user for resubmission grade until a valid grade is entered
                do
                {
                    input = JOptionPane.showInputDialog("What grade did you "
                            + "receive on the resubmission?\n\n");

                    /*
                    * Assign resubmission grade to CourseGrade object 
                    * using a different setGradeProg
                    * method designed for resubmissions
                     */
                    //valid = true if program grade, resubmission grade, 
                    //and ordinal in CourseGrades object are all valid
                    valid = courseGrades.setGradeProg(getNumber(input,
                            GradedPA.getMaxScore()), ix, resubmitted);
                } while (!valid);
            }
        }
        return courseGrades;
    }

    /**
     *
     * @param courseGrades
     * @return object courseGrades with quizzes set into the array of graded
     * activities
     */
    private static CourseGrades setQuizzes(CourseGrades courseGrades)
    {
        final int NR_Q = 12;    //max number of quizzes per semester
        String input;           //to hold user input
        int quizzes;            //amount of quizzes graded
        //boolean valid is true if grade is valid and assigned to a 
        //valid ordinal in CourseGrades object
        boolean valid;

        /*
         * Continuously ask user how many quizzes have been graded so far until
         * a valid number is entered
         */
        do
        {
            input = JOptionPane.showInputDialog("How many quizzes"
                    + " have been graded so far?\nThere are " + NR_Q
                    + " quizzes total for the semester.\n\n");
            quizzes = getNumber(input, NR_Q);
        } while (!(quizzes >= 0 && quizzes <= NR_Q));

        /*
         * For each quiz, ask user what grade he received on the quiz until
         * a valid grade is entered
         */
        for (int ix = 0; ix < quizzes; ++ix)
        {
            do
            {
                input = JOptionPane.showInputDialog("What grade did you "
                        + "receive on Quiz " + (ix + 1) + "?\n\n");
                /*
                * Assign each quiz to CourseGrade object
                 */
                //valid = true if quiz grade and
                // ordinal in CourseGrades object are both valid
                valid = courseGrades.setGradeQuiz(getNumber(input,
                        GradedQ.getMaxScore()), ix);
            } while (!valid);
        }
        return courseGrades;
    }

    /**
     *
     * @param courseGrades
     * @return object courseGrades with the final exam and its extra credit set
     * into the array of graded activities
     */
    private static CourseGrades setFinal(CourseGrades courseGrades)
    {
        //boolean valid is true if both the grade and extra credit grade
        //are valid
        boolean valid;
        String input;      //to hold user input
        int bonus = 0;          //extra credit, initialized to 0

        /*
         * Continuously ask user if the final exam has been graded until an
         * acceptable answer is received. This is to make sure that user does
         * not press enter without typing any input, because input is needed
         * in order to get the first character of the input
         */
        do
        {
            input = JOptionPane.showInputDialog("Was the final exam graded yet?"
                    + " (y/n)\n\n");
        } while (input.equals(""));

        //if user took the final exam
        if (input.charAt(0) == 'y' || input.charAt(0) == 'Y')
        {
            int maxScore = GradedFE.getMaxScore();  //Max score on exam
            int examGrade;                          //Score user received

            /*
             * Continuously ask user what grade he received on the exam until a
             * valid number is received
             */
            do
            {
                input = JOptionPane.showInputDialog("What grade did you "
                        + "receive on the final exam?\n\n");

                examGrade = getNumber(input, maxScore);

                /*
                 * Assign final exam with the user's grade to CourseGrade
                 * object.
                 */
                //valid is true if exam grade and extra credit grade are both
                //valid. (User was not yet asked for extra credit, so it is
                //currently set to 0)
                valid = courseGrades.setGradeFinal(examGrade, bonus);
            } while (!valid);

            //If grade that user entered as exam grade is lower than the max
            //score on the final exam, then it is possible that user received
            //extra credit
            if (examGrade < maxScore)
            {
                /*
                * Continuously ask user if extra credit was received until an
                * acceptable answer is received. This is to make sure that user
                * does not press enter without typing any input, because input
                * is needed in order to get the first character of the input
                 */
                do
                {
                    input = JOptionPane.showInputDialog("Did you get extra credit "
                            + "on the exam? (y/n)\n\n");
                } while (input.equals(""));

                //if user received extra credit
                if (input.charAt(0) == 'y' || input.charAt(0) == 'Y')
                {
                    /*
                     * Continuously ask user how much extra credit was received
                     * until valid number is entered
                     */
                    do
                    {
                        input = JOptionPane.showInputDialog("How many extra credit"
                                + " points did you receive?\n\n");

                        bonus = getNumber(input, (maxScore - examGrade));

                        //valid is true if exam grade and extra credit score
                        //are both valid
                        valid = courseGrades.setGradeFinal(examGrade, bonus);
                    } while (!valid); //Only move on once grade and ec are valid
                }
            }

            /*
             * Assign the final to CourseGrades object after getting
             * both the grade and the extra credit
             */
        }
        return courseGrades;
    }

    /**
     * Get a number from the user
     *
     * @param input
     * @param maxNr
     * @return number
     */
    private static int getNumber(String input, int maxNr)
    {
        int number;     //to hold number
        try
        {
            number = Integer.parseInt(input);

            //if user entered a digit, but it is invalid
            if (number < 0 || number > maxNr)
            {
                throwException(maxNr);
            }
        } // If user tries to enter letters or special characters,
        // set the number to invalid input so that the loop will run again
        catch (NumberFormatException exc)
        {
            throwException(maxNr);
            number = -1;
        }
        return number;
    }

    /**
     * Safely throw a custom made exception
     *
     * @param score
     */
    private static void throwException(int score)
    {
        try
        {
            throw new InvalidNumberSubmitted(score);
        } catch (InvalidNumberSubmitted exc)
        {
            exc.getMessage();
        }
    }
}
