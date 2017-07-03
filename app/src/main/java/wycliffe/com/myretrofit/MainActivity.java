package wycliffe.com.myretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "Q4Ja6isRqtlJHPgYaU8hUyvs0yLqHgfo";
//    private final static String EMAIL = "wycliffnyakemwa@gmail.com";
//    private final static String PASSWORD = "12345";

    ProgressDialog progressDialog;


    EditText etEmail ,etPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    Button btnLogin, btnToReg;
    private String email,password,corPassword;
    CheckBox cbShowPassword;
    private String eEmail, ePassword;
    // Session Manager Class

    /*
    We need: 1) The POJO- from jsonschema2pojo.
                2) The API interface
                3) The APIClient with the base URL.(May be done in the main class).
                4) The main class
                                    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Login housekeeping ===================================================================================================================

//sugested : Using toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        // For the back arrow n stuff
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        //setting title
        getSupportActionBar().setTitle("Login");

//----------------------------------------------------------------------------------------------------------------------------------------

        //Getting the email from user
        etEmail = (EditText) findViewById(R.id.etEmail);

        //Getting the password from user
        etPassword = (EditText) findViewById(R.id.etPassword);

        //Getting the login Button
        btnLogin = (Button)findViewById(R.id.buttonLogin);

        //Getting the to the register button
        btnToReg= (Button)findViewById(R.id.btnRegister);




//-------------------------------------------The check box---------------------------------------------------------------------------
        //show password check box
        cbShowPassword = (CheckBox) findViewById(R.id.showPassword);

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else {
                    // hide password
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

//==========================================Handling foalting Labels=============================================================================

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        //All the <TextInput> editables
        etEmail.addTextChangedListener(new MyTextWatcher(etEmail));
        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));


//=============================  Setting up connection to the  API ( Login logic using RETROFIT) ==============================================

        Button sendRequesButton = (Button) findViewById(R.id.buttonLogin);
        sendRequesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Getting the inputted String
                eEmail = etEmail.getText().toString().trim();
                ePassword  = etPassword.getText().toString().trim();


                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("Its loading....");
                progressDialog.setTitle("Fetching Server Data");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();

                // Call process
                if (API_KEY.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please obtain your API KEY first", Toast.LENGTH_LONG).show();
                    return;
                }

                //prepare call in retrofit 2.0
                // get type retrofit object stored into service.

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                //Toast.makeText(MainActivity.this,ApiClient.getClient().toString() , Toast.LENGTH_SHORT).show();
                // Giving it the info from the edit tet views.

                Call<Modeler> call = apiService.getLogged(API_KEY,ePassword,eEmail);
                call.enqueue(new Callback<Modeler>() {
                    @Override
                    public void onResponse(Call<Modeler> call, Response<Modeler> response) {

                        progressDialog.dismiss();

                        Toast.makeText(MainActivity.this, "This is the Response: " + response.message(), Toast.LENGTH_SHORT).show();

                        try {


                            Boolean status = response.body().getStatus();
                            Toast.makeText(MainActivity.this, "This is the status: " + status, Toast.LENGTH_SHORT).show();

                            if (status==true){

                                Message messageObject  = response.body().getMessage();
                                String name = messageObject.getName();
                                Toast.makeText(MainActivity.this, "This is the Name : " + name , Toast.LENGTH_SHORT).show();

                                //intent which to next
                                Intent in = new Intent(MainActivity.this, Welcome.class);
                                startActivity(in);
                                MainActivity.this.finish();
                            }
                        }catch (NullPointerException e){

                            Toast.makeText(MainActivity.this, " Exception Message: " + e.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Modeler> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "CONECTION PROBLEM:  "+  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });




    }// End onCreate

//======================Methods/ classes handling the floating Labels====================================================================

    //------------------------------------------- Validators ---------------------------------------------------------------------------------
    private boolean validateEmail() {
        String email = etEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email) ) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            //requestFocus(etEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (etPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            //requestFocus(etPassword);
            return false;
        }
        else {

            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    } //Each needs its own validator

    //checks the pattern of the email.
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



//---------------------------  Wactches text activity and calls needed validator --------------------------------------------------------

    private class MyTextWatcher implements TextWatcher {

        public View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                // Done for each editText view and each should have a validate(), some are similar create validateEdit()
                case R.id.etEmail:
                    validateEmail();
                    break;
                case R.id.etPassword:
                    validatePassword();
                    break;
            }
        }
    }

}
