package za.co.social_engineer.www.socialengineer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import za.co.social_engineer.www.socialengineer.model.Question;

public class FinishActivity extends AppCompatActivity {

    private Button restartButton;

    private TextView resultTextView;

    private Question finalQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        restartButton = (Button) findViewById(R.id.button_restart);

        resultTextView = (TextView) findViewById(R.id.text_view_result);

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (savedInstanceState != null) {
            finalQuestion = savedInstanceState.getParcelable(SEADMActivity.FINAL_QUESTION);
        } else {
            Intent intent = getIntent();
            finalQuestion = intent.getParcelableExtra(SEADMActivity.FINAL_QUESTION);
        }

        if (finalQuestion.getQuestionSet() == 100) {
            resultTextView.setTextColor(Color.RED);
            vibrator.vibrate(500);
        } else {
            resultTextView.setTextColor(Color.GREEN);
        }

        resultTextView.setText(finalQuestion.getQuestion());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(SEADMActivity.FINAL_QUESTION, finalQuestion);

        super.onSaveInstanceState(savedInstanceState);
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
}
