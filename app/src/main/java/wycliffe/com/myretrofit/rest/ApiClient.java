package wycliffe.com.myretrofit.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wycliffe on 6/28/2017.
 */


// Creating a retrofit instance

public class ApiClient {
    public static final String BASE_URL ="http://52.89.75.169";
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
