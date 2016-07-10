package za.co.social_engineer.www.socialengineer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.social_engineer.www.socialengineer.api.SocialEngineerAPI;
import za.co.social_engineer.www.socialengineer.model.Question;

public class SEADMActivity extends AppCompatActivity implements Callback<Question> {

    private static final String TAG = "SEADMActivity";
    private static final String WEB_SERVICE_BASE_URL = "http://www.social-engineer.co.za/webservice/";

    private Button yesButton;
    private Button noButton;

    private TextView questionTextView;

    private Gson gson;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seadm);

        SplashActivity.splash.finish(); // Finish Splash Activity, so that if user presses back button, it is not displayed

        yesButton = (Button) findViewById(R.id.button_yes);
        noButton = (Button) findViewById(R.id.button_no);

        questionTextView = (TextView) findViewById(R.id.text_view_question);

        gson = new GsonBuilder()
                .setDateFormat("yyyy/MM/dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(WEB_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // Prepare call in Retrofit 2.0
        SocialEngineerAPI socialEngineerAPI = retrofit.create(SocialEngineerAPI.class);

        Call<Question> call = socialEngineerAPI.getFirstQuestion();

        // Asynchronous call
        call.enqueue(this);

//        final DatabaseHandler databaseHandler = new DatabaseHandler("moutonf.co.za", "3306", "SEPTT",
//                "septt", "toor");

        // Set text of questionTextView to the string of the first question of the SEADM
//        try {
//            currentQuestion = databaseHandler.getFirstQuestion();
//
//            if (currentQuestion.next()) {
//                questionTextView.setText(currentQuestion.getString(3));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        yesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    int questionId = currentQuestion.getInt(1);
//                    int currentState = currentQuestion.getInt(2);
//                    int nextState = currentQuestion.getInt(5);
//
//                    currentQuestion = databaseHandler.getNextQuestion(questionId, currentState, nextState);
//
//                    if (currentQuestion.next()) {
//                        questionTextView.setText(currentQuestion.getString(3));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//        noButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    int questionId = currentQuestion.getInt(1);
//                    int currentState = currentQuestion.getInt(2);
//                    int nextState = currentQuestion.getInt(7);
//
//                    currentQuestion = databaseHandler.getNextQuestion(questionId, currentState, nextState);
//
//                    if (currentQuestion.next()) {
//                        questionTextView.setText(currentQuestion.getString(3));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public void yesButtonClicked(View view) {

    }

    public void noButtonClicked(View view) {

    }

    @Override
    public void onResponse(Call<Question> call, Response<Question> response) {
        if (response.code() == 200) {
            Question question = response.body();
            questionTextView.setText(question.getQuestion());
        } else {
            Log.e(TAG, "Failed to fetch first question. Retrofit response code " + response.code());
        }
    }

    @Override
    public void onFailure(Call<Question> call, Throwable t) {
        Log.e(TAG, "Failed to fetch first question");
    }
}
