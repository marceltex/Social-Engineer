package za.co.social_engineer.www.socialengineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import za.co.social_engineer.www.socialengineer.R;
import za.co.social_engineer.www.socialengineer.model.Question;

public class HomeActivity extends AppCompatActivity {

    private Question firstQuestion;

    private Toast todoToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        firstQuestion = intent.getParcelableExtra(SplashActivity.FIRST_QUESTION);

        todoToast = Toast.makeText(this, "Todo", Toast.LENGTH_LONG);
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
        todoToast.show();
    }

    public void aboutButtonClicked(View view) {
        Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}
