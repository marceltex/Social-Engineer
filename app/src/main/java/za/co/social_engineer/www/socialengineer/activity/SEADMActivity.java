package za.co.social_engineer.www.socialengineer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import za.co.social_engineer.www.socialengineer.R;
import za.co.social_engineer.www.socialengineer.api.DatabaseHandler;
import za.co.social_engineer.www.socialengineer.model.Question;

public class SEADMActivity extends AppCompatActivity {

    private static final String TAG = "SEADMActivity";
    public static final String FINAL_QUESTION = "FINAL_QUESTION";
    public static final String VISITED_STATES = "VISITED_STATES";

    private TextView questionTextView;

    private Button state1Button;
    private Button state2Button;
    private Button state3Button;
    private Button state4Button;
    private Button state5Button;
    private Button state6Button;
    private Button finalStateButton;

    private Question currentQuestion;

    private int count;

    private int[] visitedStates;

    private boolean isMultiColoredProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seadm);

        DatabaseHandler db = new DatabaseHandler(this);

        questionTextView = (TextView) findViewById(R.id.text_view_question);

        state1Button = (Button) findViewById(R.id.button_state_1);
        state2Button = (Button) findViewById(R.id.button_state_2);
        state3Button = (Button) findViewById(R.id.button_state_3);
        state4Button = (Button) findViewById(R.id.button_state_4);
        state5Button = (Button) findViewById(R.id.button_state_5);
        state6Button = (Button) findViewById(R.id.button_state_6);
        finalStateButton = (Button) findViewById(R.id.button_final_state);

        Intent intent = getIntent();
        currentQuestion = intent.getParcelableExtra(SplashActivity.FIRST_QUESTION);
        isMultiColoredProgressBar = intent.getBooleanExtra(HomeActivity.IS_MULTI_COLORED_PROGRESS_BAR, false);

        count = 0;

        visitedStates = new int[7]; // Initialised to zeros according to the Java language spec

        int stateId = currentQuestion.getQuestionSet();
        char colorChar = db.getStateColor(stateId);

        questionTextView.setText(currentQuestion.getQuestion());
        state1Button.setTextColor(Color.WHITE);
        setStateColor(state1Button, colorChar);
        visitedStates[stateId - 2] = 1;

        db.close();
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

    public void helpButtonClicked(View view) {
        Toast helpToast = Toast.makeText(this, currentQuestion.getQuestionExplained(), Toast.LENGTH_LONG);
        helpToast.show();
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
            visitedStates[visitedStates.length - 1] = currentQuestion.getQuestionSet();
            Intent intent = new Intent(SEADMActivity.this, FinishActivity.class);
            intent.putExtra(FINAL_QUESTION, currentQuestion);
            intent.putExtra(VISITED_STATES, visitedStates);
            intent.putExtra(HomeActivity.IS_MULTI_COLORED_PROGRESS_BAR, isMultiColoredProgressBar);
            startActivity(intent);
            finish();
        } else {
            int stateId = currentQuestion.getQuestionSet();
            char colorChar = db.getStateColor(stateId);
            Button stateButton = getStateButton(stateId - 1);

            questionTextView.setText(currentQuestion.getQuestion());
            stateButton.setTextColor(Color.WHITE);
            setStateColor(stateButton, colorChar);
            visitedStates[stateId - 2] = 1;
        }
        db.close();
    }

    /**
     * Method used to retrieve the state button to which a given state ID refers.
     *
     * @param stateId The state ID of the current state
     * @return State button which matches the provided state ID
     */
    public Button getStateButton(int stateId) {
        switch (stateId) {
            case 1:
                return state1Button;
            case 2:
                return state2Button;
            case 3:
                return state3Button;
            case 4:
                return state4Button;
            case 5:
                return state5Button;
            case 6:
                return state6Button;
            case 7:
                return finalStateButton;
            default:
                Log.e(TAG, "Undefined state ID, " + stateId + ", passed to getStateButton method.");
                return null;
        }
    }

    /**
     * Method used to set the colour of the state buttons in the progress bar.
     *
     * @param stateButton State button which the colour needs to be changed
     * @param colorChar A character representing the colour to which the state button should be changed
     */
    public void setStateColor(Button stateButton, char colorChar) {
        if (isMultiColoredProgressBar) {
            switch (colorChar) {
                case 'Y':
                    stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.yellow_chevron));
                    break;
                case 'B':
                    stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_chevron));
                    break;
                case 'G':
                    stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.green_chevron));
                    break;
                case 'R':
                    stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.red_chevron));
                    break;
                default:
                    Log.e(TAG, "Undefined character, '" + colorChar + "', passed to setStateColor method.");
                    break;
            }
        } else {
            stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.green_chevron));
        }
    }
}
