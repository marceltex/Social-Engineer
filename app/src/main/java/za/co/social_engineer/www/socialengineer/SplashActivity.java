package za.co.social_engineer.www.socialengineer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Activity to display the splash screen on app startup.
 *
 * Created by Marcel Teixeira on 2016/06/19
 */

public class SplashActivity extends AppCompatActivity {

    private static final String DISPLAYED = "DISPLAYED";

    // Activity object required so that the splash activity can be finished from the SEADM activity.
    // Splash activity needs to be finished to prevents app from returning to splash if user pushes the back button.
    public static Activity splash;

    private boolean hasBeenDisplayed = false; // Boolean to keep track of whether the splash has been displayed or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.activity_splash);

        if (savedInstanceState != null) {
            hasBeenDisplayed = savedInstanceState.getBoolean(DISPLAYED);
        }

        splash = this;

        /**
         * NB!!! The timerThread is temporary. Will be replaced eventually by code that pulls the
         * most recent version of the database from the web service and starts the SEADM activity,
         * once the database has been pulled.
         */
        Thread timerThread = new Thread() { // Thread used to display splash for a fixed period of time
            public void run(){
                try {
                    sleep(2000); // Display splash for 2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Prevents multiple instances of the SEADM activity being started if user rotates
                    // the device while splash is being displayed
                    if (!hasBeenDisplayed) {
                        Intent intent = new Intent(SplashActivity.this, SEADMActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        timerThread.start();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(DISPLAYED, true);

        super.onSaveInstanceState(savedInstanceState);
    }
}
