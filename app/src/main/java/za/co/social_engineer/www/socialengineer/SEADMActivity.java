package za.co.social_engineer.www.socialengineer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SEADMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seadm);

        SplashActivity.splash.finish(); // Finish Splash Activity, so that if user presses back button, it is not displayed
    }
}
