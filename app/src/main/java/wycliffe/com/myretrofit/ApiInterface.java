package wycliffe.com.myretrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Wycliffe on 6/28/2017.
 */


// This interface contains methods we are going to use to execute HTTP requests such as POST, PUT, and DELETE

public interface ApiInterface {


//    @Headers({
//            "Accept: application/json",
//            "Content-Type: application/json"
//    })

    @FormUrlEncoded
    @POST("/login")
    Call<Modeler> getLogged(@Field("api-key") String apiKey, @Field("password") String password,
                                 @Field("email") String email);
}
