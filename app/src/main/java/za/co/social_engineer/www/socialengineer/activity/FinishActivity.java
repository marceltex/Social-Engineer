package za.co.social_engineer.www.socialengineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import za.co.social_engineer.www.socialengineer.R;
import za.co.social_engineer.www.socialengineer.api.DatabaseHandler;
import za.co.social_engineer.www.socialengineer.model.Question;

public class FinishActivity extends AppCompatActivity {

    private TextView resultTextView;

    private Question finalQuestion;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        resultTextView = (TextView) findViewById(R.id.text_view_result);

        Intent intent = getIntent();
        finalQuestion = intent.getParcelableExtra(SEADMActivity.FINAL_QUESTION);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (finalQuestion.getQuestionSet() == 100) {
            resultTextView.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else {
            resultTextView.setTextColor(ContextCompat.getColor(this, R.color.green));
        }
        vibrator.vibrate(1000);

        resultTextView.setText(finalQuestion.getQuestion());
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
