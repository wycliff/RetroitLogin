package wycliffe.com.myretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wycliffe on 6/28/2017.
 */


// Creating a retrofit instance

public class ApiClient {
    public static final String BASE_URL ="http://**************";
    private static Retrofit retrofit = null;


    public static Retrofit getClient(){
        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    // Return type is Retrofit
        return retrofit;
    }

}// end class
