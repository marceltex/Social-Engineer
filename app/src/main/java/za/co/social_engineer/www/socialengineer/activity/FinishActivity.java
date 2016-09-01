package za.co.social_engineer.www.socialengineer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import za.co.social_engineer.www.socialengineer.R;
import za.co.social_engineer.www.socialengineer.api.DatabaseHandler;
import za.co.social_engineer.www.socialengineer.model.Question;

public class FinishActivity extends AppCompatActivity {

    private static final String TAG = "FinishActivity";

    private TextView resultTextView;

    private Button state1Button;
    private Button state2Button;
    private Button state3Button;
    private Button state4Button;
    private Button state5Button;
    private Button state6Button;
    private Button state7Button;
    private Button finalStateButton;

    private ImageButton helpImageButton;

    private Question finalQuestion;

    private Vibrator vibrator;

    private int[] visitedStates;

    private boolean isMultiColoredProgressBar;

    private String helpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        DatabaseHandler db = new DatabaseHandler(this);

        resultTextView = (TextView) findViewById(R.id.text_view_result);

        state1Button = (Button) findViewById(R.id.button_state_1);
        state2Button = (Button) findViewById(R.id.button_state_2);
        state3Button = (Button) findViewById(R.id.button_state_3);
        state4Button = (Button) findViewById(R.id.button_state_4);
        state5Button = (Button) findViewById(R.id.button_state_5);
        state6Button = (Button) findViewById(R.id.button_state_6);
        state7Button = (Button) findViewById(R.id.button_state_7);
        finalStateButton = (Button) findViewById(R.id.button_final_state);

        helpImageButton = (ImageButton) findViewById(R.id.image_button_help);

        Intent intent = getIntent();
        finalQuestion = intent.getParcelableExtra(SEADMActivity.FINAL_QUESTION);
        visitedStates = intent.getIntArrayExtra(SEADMActivity.VISITED_STATES);
        isMultiColoredProgressBar = intent.getBooleanExtra(HomeActivity.IS_MULTI_COLORED_PROGRESS_BAR, false);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (finalQuestion.getQuestionSet() == 100) {
            resultTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        } else {
            resultTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        }
        vibrator.vibrate(1000);

        resultTextView.setText(finalQuestion.getQuestion());

        helpText = "You should " + finalQuestion.getQuestion() + ". Touch the Restart button to start again.";

        // Set the colours of the state buttons in progress bar
        for (int i = 0; i < visitedStates.length; i++) {
            Button stateButton = getStateButton(i + 1);

            if (((visitedStates[i] == 1) && (finalQuestion.getQuestionSet() == 100)) ||
                    (visitedStates[i] == 100)) {
                stateButton.setTextColor(Color.WHITE);
                setStateColor(stateButton, 6);
            } else if (((visitedStates[i] == 1) && (finalQuestion.getQuestionSet() == 200)) ||
                    (visitedStates[i] == 200)) {
                stateButton.setTextColor(Color.WHITE);
                setStateColor(stateButton, 5);
            } else {
                stateButton.setEnabled(false);
            }
        }

        db.close();
    }

    public void restartButtonClicked(View view) {
        DatabaseHandler db = new DatabaseHandler(this);

        Question firstQuestion = db.getFirstQuestion();

        db.close();

        Intent intent = new Intent(FinishActivity.this, SEADMActivity.class);
        intent.putExtra(SplashActivity.FIRST_QUESTION, firstQuestion);
        intent.putExtra(HomeActivity.IS_MULTI_COLORED_PROGRESS_BAR, isMultiColoredProgressBar);
        startActivity(intent);
        finish();
    }

    public void helpButtonClicked(View view) {
        helpImageButton.setVisibility(View.INVISIBLE);

        Snackbar helpSnackbar = Snackbar.make(view, helpText, Snackbar.LENGTH_LONG);

        helpSnackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                helpImageButton.setVisibility(View.VISIBLE);
            }
        });

        helpSnackbar.show();
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
                return state7Button;
            case 8:
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
     * @param stateId State ID of the current state
     */
    public void setStateColor(Button stateButton, int stateId) {
        switch (stateId) {
            case 1:
            case 2:
            case 4:
                stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.yellow_chevron));
                break;
            case 3:
                stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_chevron));
                break;
            case 5:
            case 7:
                stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.green_chevron));
                break;
            case 6:
                stateButton.setBackground(ContextCompat.getDrawable(this, R.drawable.red_chevron));
                break;
            default:
                Log.e(TAG, "Undefined state ID, '" + stateId + "', passed to setStateColor method.");
                break;
        }
    }
}
