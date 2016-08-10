package za.co.social_engineer.www.socialengineer.api;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import za.co.social_engineer.www.socialengineer.model.Question;
import za.co.social_engineer.www.socialengineer.util.MiscUtil;

/**
 * Class used to manage database CRUD
 *
 * Created by Marcel Teixeira on 2016/07/03.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHandler";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "septt";

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
    private static final String KEY_MATCH = "`match`";
    private static final String KEY_TRANSITION = "transition";
    private static final String KEY_CIRCULAR = "circular";

    // State table column names
    private static final String KEY_NAME = "name";

    // Complex questions table column names
    private static final String KEY_QUESTIONS = "questions";
    private static final String KEY_COUNT = "count";
    private static final String KEY_RETURN = "return";

    //Table create statements
    // Questions table create statement
    private static final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + " ( " +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION_SET + " INTEGER, " +
            KEY_QUESTION + " TEXT, " + KEY_OPTION_A + " TEXT, " + KEY_RETURN_A + " INTEGER, " +
            KEY_OPTION_B + " TEXT, " + KEY_RETURN_B + " INTEGER, " + KEY_IS_SKIPPABLE + " INTEGER," +
            KEY_IS_COUNT + " INTEGER, " + KEY_IS_FINAL_COUNT + " INTEGER )";

    // State transitions table create statement
    private static final String CREATE_STATE_TRANSITIONS_TABLE = "CREATE TABLE " +
            TABLE_STATE_TRANSITIONS + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_STATE + " INTEGER, " + KEY_MATCH + " INTEGER, " + KEY_TRANSITION + " INTEGER, " +
            KEY_CIRCULAR + " INTEGER )";

    // State table create statement
    private static final String CREATE_STATE_TABLE = "CREATE TABLE " + TABLE_STATE + " ( " + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT )";

    // Complex questions table create statement
    private static final String CREATE_COMPLEX_QUESTIONS_TABLE = "CREATE TABLE " +
            TABLE_COMPLEX_QUESTIONS + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_QUESTION_SET + " INTEGER, " + KEY_QUESTIONS + " INTEGER, " + KEY_COUNT + " INTEGER, " +
            KEY_RETURN + " INTEGER )";

    // Array of SQL files
    private static final String[] SQL_FILES = {"complexQuestions.sql", "questions.sql", "State.sql",
            "stateTransitions.sql"};

    private static final int FIRST_QUESTION_ID = 2;

    private Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }

    /**
     * Method to create SQLite database.
     *
     * @param db Database in which tables must be added
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create required tables
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_STATE_TRANSITIONS_TABLE);
        db.execSQL(CREATE_STATE_TABLE);
        db.execSQL(CREATE_COMPLEX_QUESTIONS_TABLE);

        // Add data from SQL files to tables
        for (int i = 0; i < SQL_FILES.length; i++) {
            try {
                InputStream inputStream = context.getResources().getAssets().open(SQL_FILES[i]);

                String sql = MiscUtil.readTextFromInputStream(inputStream);
                db.execSQL(sql);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    /**
     * Method to update SQLite database.
     *
     * @param db Database to be updates
     * @param oldVersion Old version of database
     * @param newVersion New version of database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables, if they existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATE_TRANSITIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLEX_QUESTIONS);

        // Create database tables again
        onCreate(db);
    }

    /**
     * Method to get the first question from the questions table and return a question object of the
     * first question.
     *
     * @return First question to be displayed
     */
    public Question getFirstQuestion() {
        SQLiteDatabase db = this.getReadableDatabase();

        String getFirstQuestionQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_ID +
                " = " + FIRST_QUESTION_ID;

        Cursor cursor = db.rawQuery(getFirstQuestionQuery, null);

        if (!(cursor.moveToFirst()) || cursor.getCount() == 0) {
            // Return null if question not found
            return null;
        } else {
            cursor.moveToFirst();

            Question firstQuestion = new Question(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getInt(4), cursor.getString(5),
                    cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9));

            return firstQuestion;
        }
    }

    /**
     * Method to determine and return a ResultSet with the next question that must be displayed.
     *
     * @param id ID of the question that is currently displayed to the user
     * @param state State that the current question is in
     * @param match State that must be transitioned to
     * @return Next question to be displayed
     */
    public Question getNextQuestion(int id, int state, int match, int count) {
        SQLiteDatabase db = this.getReadableDatabase();

        if (match == -1) {
            String complexQuestionQuery = "SELECT * FROM " + TABLE_COMPLEX_QUESTIONS +" WHERE " +
                    KEY_QUESTION_SET + " = " + state + " AND " + KEY_COUNT + " = " + count;

            Cursor complexQuestionCursor = db.rawQuery(complexQuestionQuery, null);

            if (!(complexQuestionCursor.moveToFirst()) || complexQuestionCursor.getCount() == 0) {
                // Return null if complex question not found
                return null;
            } else {
                complexQuestionCursor.moveToFirst();

                match = complexQuestionCursor.getInt(4);
            }
        }

        String stateTransitionQuery = "SELECT * FROM " + TABLE_STATE_TRANSITIONS +" WHERE " +
                KEY_STATE + " = " + state + " AND " + KEY_MATCH + " = " + match;

        Cursor stateTransitionCursor = db.rawQuery(stateTransitionQuery, null);

        if (!(stateTransitionCursor.moveToFirst()) || stateTransitionCursor.getCount() == 0) {
            // Return null if state transition not found
            return null;
        } else {
            stateTransitionCursor.moveToFirst();

            int questionSet  = stateTransitionCursor.getInt(3);

            if ((questionSet != 100) && (questionSet != 200)) {
                String nextQuestionQuery;

                // Circular dependency - Will be removed in next version of SEADM, so hard-coded for now
                if ((id == 3) && (state == 2) && (match == 3)) {
                    id = 2;
                    nextQuestionQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_ID +
                            " = " + id;
                } else if (questionSet == state) { // If state doesn't change return next question in current state
                    id++;
                    nextQuestionQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_ID +
                            " = " + id;
                } else {
                    nextQuestionQuery = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " +
                            KEY_QUESTION_SET + " = " + questionSet + " ORDER BY " + KEY_ID;
                }

                Cursor nextQuestionCursor = db.rawQuery(nextQuestionQuery, null);

                if (!(nextQuestionCursor.moveToFirst()) || nextQuestionCursor.getCount() == 0) {
                    // Return null if question not found
                    return null;
                } else {
                    nextQuestionCursor.moveToFirst();

                    Question nextQuestion = new Question(nextQuestionCursor.getInt(0), nextQuestionCursor.getInt(1),
                            nextQuestionCursor.getString(2), nextQuestionCursor.getString(3),
                            nextQuestionCursor.getInt(4), nextQuestionCursor.getString(5),
                            nextQuestionCursor.getInt(6), nextQuestionCursor.getInt(7),
                            nextQuestionCursor.getInt(8), nextQuestionCursor.getInt(9));

                    return nextQuestion;
                }
            } else {
                Question finalQuestion;
                if (questionSet == 100) {
                    finalQuestion = new Question(0, questionSet, "Defer or Refer Request",
                            "", 0, "", 0, 0, 0, 0);
                } else {
                    finalQuestion = new Question(0, questionSet, "Perform the Request",
                            "", 0, "", 0, 0, 0, 0);
                }
                return finalQuestion;
            }
        }
    }

    /**
     * Method to get and return colour of given state
     *
     * @param id State id of state for which the colour is required
     * @return String representation of the colour of the given state
     */
    public String getStateColour(int id) {

    }
}
