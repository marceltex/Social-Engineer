package za.co.social_engineer.www.socialengineer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import za.co.social_engineer.www.socialengineer.api.DatabaseHandler;
import za.co.social_engineer.www.socialengineer.model.Question;

public class SEADMActivity extends AppCompatActivity {

    private static final String TAG = "SEADMActivity";
    private static final String CURRENT_QUESTION = "CURRENT_QUESTION";
    public static final String FINAL_QUESTION = "FINAL_QUESTION";
    private static final String COUNT = "COUNT";

    private Button yesButton;
    private Button noButton;

    private TextView questionTextView;

    private Question currentQuestion;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seadm);

        yesButton = (Button) findViewById(R.id.button_yes);
        noButton = (Button) findViewById(R.id.button_no);

        questionTextView = (TextView) findViewById(R.id.text_view_question);

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getParcelable(CURRENT_QUESTION);
            count = savedInstanceState.getInt(COUNT);
        } else {
            Intent intent = getIntent();
            currentQuestion = intent.getParcelableExtra(SplashActivity.FIRST_QUESTION);
            count = 0;
        }

        questionTextView.setText(currentQuestion.getQuestion());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(CURRENT_QUESTION, currentQuestion);
        savedInstanceState.putInt(COUNT, count);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void yesButtonClicked(View view) {
        int id = currentQuestion.getId();
        int questionSet = currentQuestion.getQuestionSet();
        int returnA = currentQuestion.getReturnA();

        if (currentQuestion.getIsCount() == 1) {
            count++;
        }

        getNextQuestion(id, questionSet, returnA);
    }

    public void noButtonClicked(View view) {
        int id = currentQuestion.getId();
        int questionSet = currentQuestion.getQuestionSet();
        int returnB = currentQuestion.getReturnB();

        getNextQuestion(id, questionSet, returnB);
    }

    /**
     * Method to get next question from database handler and update text of question displayed to user.
     *
     * @param id Question id of current question
     * @param state State of current question
     * @param match Matching integer used to determine next state
     */
    public void getNextQuestion(int id, int state, int match) {
        DatabaseHandler db = new DatabaseHandler(this);

        int tempCount = count;

        if (currentQuestion.getIsFinalCount() == 1) {
            count = 0;
        }

        currentQuestion = db.getNextQuestion(id, state, match, tempCount);

        if ((currentQuestion.getQuestionSet() == 100) || (currentQuestion.getQuestionSet() == 200)) {
            Intent intent = new Intent(SEADMActivity.this, FinishActivity.class);
            intent.putExtra(FINAL_QUESTION, currentQuestion);
            startActivity(intent);
            finish();
        } else {
            questionTextView.setText(currentQuestion.getQuestion());
        }
        db.close();
    }
}
