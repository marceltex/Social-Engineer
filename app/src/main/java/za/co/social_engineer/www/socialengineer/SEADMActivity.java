package za.co.social_engineer.www.socialengineer;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.ResultSet;

public class SEADMActivity extends AppCompatActivity {

    private static final String TAG = "SEADMActivity";

    private Button yesButton;
    private Button noButton;

    private TextView questionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seadm);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        // Required to allow the app to process network requests on the main thread
        // Todo: This must change before final version is released. All network transactions should
        // be done with an AsyncTask
        StrictMode.setThreadPolicy(policy);
        SplashActivity.splash.finish(); // Finish Splash Activity, so that if user presses back button, it is not displayed

        yesButton = (Button) findViewById(R.id.button_yes);
        noButton = (Button) findViewById(R.id.button_no);

        questionTextView = (TextView) findViewById(R.id.text_view_question);

        DatabaseHandler databaseHandler = new DatabaseHandler("moutonf.co.za", "3306", "SEPTT",
                "septt", "toor");

        // Set text of questionTextView to the string of the first question of the SEADM
        try {
            ResultSet rs = databaseHandler.getFirstQuestion();

            rs.next();

            questionTextView.setText(rs.getString(3));
        } catch (Exception e) {
            e.printStackTrace();
        }

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
