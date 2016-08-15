package za.co.social_engineer.www.socialengineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import za.co.social_engineer.www.socialengineer.R;
import za.co.social_engineer.www.socialengineer.model.Question;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private Question firstQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        firstQuestion = intent.getParcelableExtra(SplashActivity.FIRST_QUESTION);
    }

    public void questionsButtonClicked(View view) {
        Intent intent = new Intent(HomeActivity.this, SEADMActivity.class);
        intent.putExtra(SplashActivity.FIRST_QUESTION, firstQuestion);
        startActivity(intent);
    }

    public void pictureOfModelButtonClicked(View view) {
        Intent intent = new Intent(HomeActivity.this, PictureOfModelActivity.class);
        startActivity(intent);
    }

    public void trainingButtonClicked(View view) {
        Intent intent = new Intent(HomeActivity.this, TrainingActivity.class);
        startActivity(intent);
    }

    public void aboutButtonClicked(View view) {
        Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}
