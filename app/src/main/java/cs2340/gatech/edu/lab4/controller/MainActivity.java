package cs2340.gatech.edu.lab4.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cs2340.gatech.edu.lab4.R;

public class MainActivity extends AppCompatActivity {
    /**
     * Sets up main activity, initializes controller for Firebase
     * @param savedInstanceState last saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity started");

        FirebaseController controller = FirebaseController.getInstance();
        FirebaseController.init();

    }

    /**
     *  Starts login activity
     * @param view login view object
     */
    public void login(View view){
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Starts register activity
     * @param view register view object
     */
    public void register(View view){
        Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
        startActivity(intent);
    }
}
