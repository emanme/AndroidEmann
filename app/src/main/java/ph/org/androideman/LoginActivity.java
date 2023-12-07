package ph.org.androideman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity {
    private static final String TAGS = "LoginACT";
    String user, pass, loginkey, sessionkey;
    private EditText etEmail;
    private EditText etPassword;

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get Reference to variables
        etEmail = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
    }


    // Triggers when LOGIN Button clicked
    public void checkLogin(View arg0) {

        // Get text from email and passord field
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        loginkey = email + UUID.randomUUID().toString();
        user = email;
        pass = password;
        // Initialize  AsyncLogin() class with email and password
        new AsyncLogin().execute(email, password, loginkey);

    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(LoginActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("https://traceapiv1.southernleyte.org.ph/json7/login/");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e(TAGS, "MS : e.printStackTrace()");
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Log.e(TAGS, "MS : POST");
                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1])
                        .appendQueryParameter("app", params[2]);
                String query = builder.build().getEncodedQuery();
                Log.e(TAGS, "MS : Uri.Builder()");
                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                Log.e(TAGS, "MS : getOutputStream");
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                Log.e(TAGS, "MS : BufferedWriter");
                writer.write(query);
                Log.e(TAGS, "MS : write");
                writer.flush();
                Log.e(TAGS, "MS : flush");
                writer.close();
                Log.e(TAGS, "MS : close");
                os.close();
                conn.connect();
                Log.e(TAGS, "Query : \"" + query + "\"");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                Log.e(TAGS, "MS :IOException ");
                return "exception";
            }
            try {

                int response_code = conn.getResponseCode();
                Log.e(TAGS, "MS : try { start");
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    Log.e(TAGS, "MS : HTTP_OK()");
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());


                } else {
                    Log.e(TAGS, "MS : unsuccessful");
                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAGS, "MS : exception");
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonobject = new JSONObject();
            String msresult = "";
            //this method will be running on UI thread
            Log.d(TAGS, "onPostExecute: result=" + result);
            pdLoading.dismiss();


            try {
                jsonobject = new JSONObject(result);
                msresult = jsonobject.getString("result");
                // dbHelper.saveSessionString("date",establishmentdate,database);
            } catch (Throwable t) {
                Log.e(TAGS, "Could not parse malformed JSON: \"" + jsonobject.toString() + "\"");
            }

            if (msresult.equalsIgnoreCase("success")) {
//####################################################################################################################################################
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();

            } else if (msresult.equalsIgnoreCase("false")) {

                // If username and password does not match display a error message
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();

            } else if (msresult.equalsIgnoreCase("exception") || msresult.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(LoginActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(LoginActivity.this, "OOPs!.. Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }


        }
    }
}