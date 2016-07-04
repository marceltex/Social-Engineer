package za.co.social_engineer.www.socialengineer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SEADMActivity extends AppCompatActivity {

    private Button yesBtn;
    private Button noBtn;

    private TextView questionTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seadm);

        SplashActivity.splash.finish(); // Finish Splash Activity, so that if user presses back button, it is not displayed

        yesBtn = (Button) findViewById(R.id.button_yes);
        noBtn = (Button) findViewById(R.id.button_no);

        questionTxtView = (TextView) findViewById(R.id.text_view_question);

        //        DatabaseHandler databaseHandler = new DatabaseHandler("moutonf.co.za", "3306", "SEPTT",
//                "septt", "toor");
//
//        try {
//            ResultSet rs = databaseHandler.getAll();
//
//            rs.next();
//
//            questionTxtView.setText(rs.getString(3));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
