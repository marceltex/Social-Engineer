package za.co.social_engineer.www.socialengineer;

import java.sql.*;

/**
 * Class used to manage database CRUD
 *
 * Created by Marcel Teixeira on 2016/07/03.
 */
public class DatabaseHandler {

    private static final String TAG = "DatabaseHandler";

    // Table names
    private static final String TABLE_QUESTIONS = "questions";
    private static final String TABLE_STATE_TRANSITIONS = "stateTransitions";
    private static final String TABLE_STATE = "State";
    private static final String TABLE_COMPLEX_QUESTIONS = "complexQuestions";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION_SET = "questionSet";

    // Questions table column names
    private static final String KEY_QUESTION = "question";
    private static final String KEY_OPTION_A = "optionA";
    private static final String KEY_RETURN_A = "returnA";
    private static final String KEY_OPTION_B = "optionB";
    private static final String KEY_RETURN_B = "returnB";
    private static final String KEY_IS_SKIPPABLE = "isSkippable";
    private static final String KEY_IS_COUNT = "isCount";
    private static final String KEY_IS_FINAL_COUNT = "isFinalCount";

    // State transitions table column names
    private static final String KEY_STATE = "state";
    private static final String KEY_MATCH = "match";
    private static final String KEY_TRANSITION = "transition";

    // State table column names
    private static final String KEY_NAME = "name";

    // Complex table column names
    private static final String KEY_QUESTIONS = "questions";
    private static final String KEY_COUNT = "count";
    private static final String KEY_RETURN = "return";

    private static final int FIRST_QUESTION_ID = 2;

    private String databaseUrl;
    private String databasePort;
    private String databaseName;
    private String username;
    private String password;

    public DatabaseHandler(String databaseUrl, String databasePort, String databaseName, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.databasePort = databasePort;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    /**
     * Method to get the first question from the questions table  of the SEPTT database and return it
     * as a ResultSet.
     *
     * @return ResultSet containing the first question to be displayed
     * @throws Exception If connection to database could not be established
     */
    public ResultSet getFirstQuestion() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://" + databaseUrl + ":" +
                databasePort + "/" + databaseName, username, password);

        Statement stmnt = conn.createStatement();

        ResultSet firstQuestion = stmnt.executeQuery("SELECT * FROM " + TABLE_QUESTIONS +
                " WHERE " + KEY_ID + " = " + FIRST_QUESTION_ID);

        conn.close();

        return firstQuestion;
    }

    /**
     * Method to determine and return a ResultSet with the next question that must be displayed.
     *
     * @param questionId QuestionID of the question that is currently displayed to the user
     * @param currentState State that the current question is in
     * @param nextState State that must be transitioned to
     * @return ResultSet containing the next question to be displayed
     * @throws Exception If connection to database could not be established
     */
    public ResultSet getNextQuestion(int questionId, int currentState, int nextState) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://" + databaseUrl + ":" +
                databasePort + "/" + databaseName, username, password);

        Statement stmnt = conn.createStatement();

        ResultSet stateTransition = stmnt.executeQuery("SELECT * FROM " + TABLE_STATE_TRANSITIONS +
                " WHERE " + KEY_STATE + " = " + currentState + " AND " + KEY_MATCH + " = " + nextState);

        int questionSet = stateTransition.getInt(4);

        ResultSet nextQuestion;

        // If state doesn't change return next question in current state
        if (questionSet == currentState) {
            nextQuestion = stmnt.executeQuery("SELECT * FROM " + TABLE_QUESTIONS + " WHERE "
                    + KEY_ID + " = " + (questionId++));
        } else {
            nextQuestion = stmnt.executeQuery("SELECT * FROM " + TABLE_QUESTIONS + " WHERE "
                    + KEY_QUESTION_SET + " = " + questionSet + " ORDER BY " + KEY_ID);
        }

        conn.close();

        return nextQuestion;
    }
}
