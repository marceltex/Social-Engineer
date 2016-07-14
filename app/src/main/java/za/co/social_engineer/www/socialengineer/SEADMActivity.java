package za.co.social_engineer.www.socialengineer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import za.co.social_engineer.www.socialengineer.model.Question;

public class SEADMActivity extends AppCompatActivity {

    private static final String TAG = "SEADMActivity";
    private static final String CURRENT_QUESTION = "CURRENT_QUESTION";
    //private static final String WEB_SERVICE_BASE_URL = "http://www.social-engineer.co.za/webservice/";

    private Button yesButton;
    private Button noButton;

    private TextView questionTextView;

    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seadm);

        SplashActivity.splash.finish(); // Finish Splash Activity, so that if user presses back button, it is not displayed

        yesButton = (Button) findViewById(R.id.button_yes);
        noButton = (Button) findViewById(R.id.button_no);

        questionTextView = (TextView) findViewById(R.id.text_view_question);

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getParcelable(CURRENT_QUESTION);
        } else {
            Intent intent = getIntent();
            currentQuestion = intent.getParcelableExtra(SplashActivity.FIRST_QUESTION);
        }

        questionTextView.setText(currentQuestion.getQuestion());

//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy/MM/dd'T'HH:mm:ssZ")
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(WEB_SERVICE_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();

        // Prepare call in Retrofit 2.0
//        SocialEngineerAPI socialEngineerAPI = retrofit.create(SocialEngineerAPI.class);
//
//        Call<List<Question>> call = socialEngineerAPI.getNextQuestion(1,2);

//        // Asynchronous call
//        call.enqueue(this);

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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(CURRENT_QUESTION, currentQuestion);

        super.onSaveInstanceState(savedInstanceState);
    }

    public void yesButtonClicked(View view) {
        int id = currentQuestion.getId();
        int questionSet = currentQuestion.getQuestionSet();
        int returnA = currentQuestion.getReturnA();

        getNextQuestion(id, questionSet, returnA);
    }

    public void noButtonClicked(View view) {
        int id = currentQuestion.getId();
        int questionSet = currentQuestion.getQuestionSet();
        int returnB = currentQuestion.getReturnB();

        getNextQuestion(id, questionSet, returnB);
    }

    /**
     * Method to get next question from database handler and update text of question displayed to user.
     *
     * @param id Question id of current question
     * @param state State of current question
     * @param match Matching integer used to determine next state
     */
    public void getNextQuestion(int id, int state, int match) {
        DatabaseHandler db = new DatabaseHandler(this);

        currentQuestion = db.getNextQuestion(id, state, match);

        questionTextView.setText(currentQuestion.getQuestion());

        db.close();
    }

//    @Override
//    public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
////        if (response.code() == 200) {
////            Question question = response.body();
////            questionTextView.setText(question.getQuestion());
////        } else {
////            Log.e(TAG, "Failed to fetch first question. Retrofit response code " + response.code());
////        }
//    }
//
//    @Override
//    public void onFailure(Call<List<Question>> call, Throwable t) {
////        Log.e(TAG, "Failed to fetch first question");
//    }
}
