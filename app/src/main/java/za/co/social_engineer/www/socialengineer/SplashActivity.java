package za.co.social_engineer.www.socialengineer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import za.co.social_engineer.www.socialengineer.model.Question;

/**
 * Activity to display the splash screen on app startup.
 *
 * Created by Marcel Teixeira on 2016/06/19
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    private static final String DISPLAYED = "DISPLAYED";
    // private static final String WEB_SERVICE_BASE_URL = "http://www.social-engineer.co.za/webservice/";
    public static final String FIRST_QUESTION = "FIRST_QUESTION";

    // Activity object required so that the splash activity can be finished from the SEADM activity.
    public static Activity splash;

    private boolean hasBeenDisplayed = false; // Boolean to keep track of whether the splash has been displayed or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);

        if (savedInstanceState != null) {
            hasBeenDisplayed = savedInstanceState.getBoolean(DISPLAYED);
        }

        splash = this;

//        if (!hasBeenDisplayed) {
//            Gson gson = new GsonBuilder()
//                    .setDateFormat("yyyy/MM/dd'T'HH:mm:ssZ")
//                    .create();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(WEB_SERVICE_BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//
//            // Prepare call in Retrofit 2.0
//            SocialEngineerAPI socialEngineerAPI = retrofit.create(SocialEngineerAPI.class);
//
//            Call<Question> call = socialEngineerAPI.getFirstQuestion();
//
//            // Asynchronous call
//            call.enqueue(this);
//        }
        DatabaseHandler db = new DatabaseHandler(this);

        final Question firstQuestion = db.getFirstQuestion();

        /**
         * NB!!! The timerThread is temporary. Will be replaced eventually by code that pulls the
         * most recent version of the database from the web service and starts the SEADM activity,
         * once the database has been pulled.
         */
        Thread timerThread = new Thread() { // Thread used to display splash for a fixed period of time
            public void run(){
                try {
                    sleep(1000); // Display splash for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Prevents multiple instances of the SEADM activity being started if user rotates
                    // the device while splash is being displayed
                    if (!hasBeenDisplayed) {
                        Intent intent = new Intent(SplashActivity.this, SEADMActivity.class);
                        intent.putExtra(FIRST_QUESTION, firstQuestion);
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

//    @Override
//    public void onResponse(Call<Question> call, Response<Question> response) {
//        if (response.code() == 200) {
//            Question question = response.body();
//            if (!hasBeenDisplayed) {
//                Intent intent = new Intent(SplashActivity.this, SEADMActivity.class);
//                intent.putExtra(FIRST_QUESTION, question.getQuestion());
//                startActivity(intent);
//            }
//        } else {
//            Log.e(TAG, "Failed to fetch first question. Retrofit response code " + response.code());
//        }
//    }
//
//    @Override
//    public void onFailure(Call<Question> call, Throwable t) {
//        Log.e(TAG, "Failed to fetch first question");
//    }
}
