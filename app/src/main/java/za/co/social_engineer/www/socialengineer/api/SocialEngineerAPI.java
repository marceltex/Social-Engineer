package za.co.social_engineer.www.socialengineer.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import za.co.social_engineer.www.socialengineer.model.Question;

/**
 * Interface that defines HTTP GET requests used by Retrofit.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public interface SocialEngineerAPI {

    @GET("GetFirstQuestion.php")
    Call<Question> getFirstQuestion();

    @GET("GetNextQuestion.php")
    Call<List<Question>> getNextQuestion(@Query("state") String state, @Query("match") String match);
}
