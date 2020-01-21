package cs2340.gatech.edu.lab4.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText password;
    Model model = Model.getInstance();
    public static final String ARG_USER_ID = "user_id";
    public static final String ARG_PASSWORD_ID = "password_id";

    @Override
    /**
     * Sets up entry fields and view
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.username_field);
        password = findViewById(R.id.password_field);
    }

    /**
     * When login is pressed, triggers user validation
     * @param view Current view of app
     */
    public void onLoginPressed(View view) {
        if(Model.isValidUserAndPassword(usernameField.getText().toString(),password.getText().toString())) {
            Intent intent = new Intent(getBaseContext(), ShelterListActivity.class);
            intent.putExtra(ARG_USER_ID,usernameField.getText().toString());
            startActivity(intent);
        } else {
            Snackbar.make(view, "Invalid username or password. Please try again.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    /**
     * returns user to start screen if cancel is pressed
     * @param view Current app view
     */
    public void onCancelPressed(View view) {
        finish();
    }



}
