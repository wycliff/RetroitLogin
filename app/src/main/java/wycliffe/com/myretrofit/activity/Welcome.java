package wycliffe.com.myretrofit.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wycliffe.com.myretrofit.R;
import wycliffe.com.myretrofit.model.Message;
import wycliffe.com.myretrofit.model.Modeler;
import wycliffe.com.myretrofit.rest.ApiClient;
import wycliffe.com.myretrofit.rest.ApiInterface;

import static wycliffe.com.myretrofit.rest.Constants.API_KEY;
import static wycliffe.com.myretrofit.rest.Constants._EMAIL;
import static wycliffe.com.myretrofit.rest.Constants._PASSWAD;


/*
PULL DOWN REFRESH.
We use the SwipeRefreshLayout , in the ndroid.support.v4
    STEPS:
    -  wrap the view around SwipeRefreshLayout element ,for your view in xml.
    -  implement your activity class from SwipeRefreshLayout.OnRefreshListener
 */
public class Welcome extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    TextView displayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        displayer = (TextView)findViewById(R.id.myJsonResponse);

    }


    @Override
    public void onRefresh() {

        requestSender();

    }


    public void requestSender(){

        //prepare call in retrofit 2.0
        // get type retrofit object stored into service.

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //Toast.makeText(MainActivity.this,ApiClient.getClient().toString() , Toast.LENGTH_SHORT).show();


        // Giving it the info needed
        Call<Modeler> call = apiService.getLogged(API_KEY,_PASSWAD,_EMAIL);

        call.enqueue(new Callback<Modeler>() {
            @Override
            public void onResponse(Call<Modeler> call, Response<Modeler> response) {

                Message message = response.body().getMessage();
                displayer.setText(message.getName());
            }

            @Override
            public void onFailure(Call<Modeler> call, Throwable t) {

            }
        });
    }
}
