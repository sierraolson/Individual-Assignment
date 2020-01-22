package cs2340.gatech.edu.lab4.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import cs2340.gatech.edu.lab4.R;


public class WeatherActivity extends Activity{

        /**
         * Sets up main activity, initializes controller for Firebase
         * @param savedInstanceState last saved instance
         */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_weather);
//            setContentView(R.layout.activity_profile);
//        System.out.println("MainActivity started");
//
//        FirebaseController controller = FirebaseController.getInstance();
//        FirebaseController.init();

        }

        /**
         *  Starts login activity
         * @param view login view object
         */
        public void onWeatherPressed(View view){

            final TextView textView = (TextView) findViewById(R.id.text);
//            System.out.println("pressed");
//            final String[] s = new String[1];
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://c4tn0dg8a4.execute-api.us-east-2.amazonaws.com/deploy/dress?lat=37.7749&lon=-122.4194";

//             Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string
                                    String out = "";
                                    String out2 = "";
                                    try {
                                        JSONObject obj = new JSONObject(response);

                                        out = obj.getString("message");
                                        out2 = obj.getString("determineDress");

                                    } catch (JSONException e) {}



                                    textView.setText(out + " You should wear: " + out2);
//                                    s[0] = response.substring(0, 500);

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            textView.setText("That didn't work!");

                        }
                    });

        // Add the request to the RequestQueue
                queue.add(stringRequest);

//            Snackbar.make(view, s[0] , Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//            System.out.println("done");



        }


}
