package com.example.collegetour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Runs and show different screens

    //possible way to identify which button was clicked
//    public void onClick(View v) {
//        switch(v.getId())
//        {
//            case R.id.button_a_id:
//// handle button A click;
//                break;
//            case R.id.button_b_id:
//// handle button B click;
//                break;
//            default:
//                throw new RuntimeException("Unknow button ID");
//        }

    //screen navigation methods
    public void showUpperCampusMap (View view){
        setContentView(R.layout.activty_upper_campus);
    }

    public void showLowerCampusMap (View view){
        setContentView(R.layout.activity_lower_campus);
    }

    public void showDormsCampusMap (View view){
        setContentView(R.layout.activity_dorms);
    }

    public void showEngineeringCampusMap (View view){
        setContentView(R.layout.activity_engineering_police_gym);
    }

    public void showMainCampusMap (View view){
        setContentView(R.layout.activity_main_map);
    }

    public void showMain (View view){
        setContentView(R.layout.activity_main);
    }

}
