package za.co.social_engineer.www.socialengineer.api;

import retrofit2.http.GET;

/**
 * Interface that defines HTTP GET requests used by Retrofit.
 *
 * Created by Marcel Teixeira on 2016/07/09.
 */
public interface SocialEngineerAPI {

    @GET("/GetFirstQuestion.php")
    void getFirstQuestion();
}
