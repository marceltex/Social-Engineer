package za.co.social_engineer.www.socialengineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button finalStateButton;

    private Question finalQuestion;

    private Vibrator vibrator;

    private int[] visitedStates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        resultTextView = (TextView) findViewById(R.id.text_view_result);

        state1Button = (Button) findViewById(R.id.button_state_1);
        state2Button = (Button) findViewById(R.id.button_state_2);
        state3Button = (Button) findViewById(R.id.button_state_3);
        state4Button = (Button) findViewById(R.id.button_state_4);
        state5Button = (Button) findViewById(R.id.button_state_5);
        state6Button = (Button) findViewById(R.id.button_state_6);
        finalStateButton = (Button) findViewById(R.id.button_final_state);

        Intent intent = getIntent();
        finalQuestion = intent.getParcelableExtra(SEADMActivity.FINAL_QUESTION);
        visitedStates = intent.getIntArrayExtra(SEADMActivity.VISITED_STATES);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (finalQuestion.getQuestionSet() == 100) {
            resultTextView.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else {
            resultTextView.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
        vibrator.vibrate(1000);

        resultTextView.setText(finalQuestion.getQuestion());

        // Set the colours of the state buttons in progress bar
        for (int i = 0; i < visitedStates.length; i++) {
            if (visitedStates[i] == 1) {

            } else {
                Button stateButton = SEADMActivity.get
            }
        }
    }

    public void restartButtonClicked(View view) {
        DatabaseHandler db = new DatabaseHandler(this);

        Question firstQuestion = db.getFirstQuestion();

        db.close();

        Intent intent = new Intent(FinishActivity.this, SEADMActivity.class);
        intent.putExtra(SplashActivity.FIRST_QUESTION, firstQuestion);
        startActivity(intent);
        finish();
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
            default:
                Log.e(TAG, "Undefined state ID, " + stateId + ", passed to getStateButton method.");
                return null;
        }
    }
}
