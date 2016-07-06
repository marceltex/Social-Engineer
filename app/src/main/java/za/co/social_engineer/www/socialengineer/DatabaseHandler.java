package za.co.social_engineer.www.socialengineer;

import java.sql.*;

/**
 * Class used to manage database CRUD
 *
 * Created by Marcel Teixeira on 2016/07/03.
 */
public class DatabaseHandler {

    private static final String QUESTIONS_TABLE = "questions";
    private static final String STATE_TRANSITIONS_TABLE = "stateTransitions";
    private static final String STATE_TABLE = "State";
    private static final String COMPLEX_QUESTIONS_TABLE = "complexQuestions";

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

        ResultSet firstQuestion = stmnt.executeQuery("SELECT * FROM " + QUESTIONS_TABLE +
                " WHERE id = " + FIRST_QUESTION_ID);

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

        ResultSet stateTransition = stmnt.executeQuery("SELECT * FROM stateTransitions");

        conn.close();
    }
}
