package com.example.collegetour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
    }


    //Runs and show different screens




    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dormImageMainMapButton:// handle button A click;
                Toast.makeText(getApplicationContext(), "Dorms", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lowerCampusMainMapButton:// handle button B click;
                Toast.makeText(getApplicationContext(), "Lower Campus", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gymPoliceMainMapButton:// handle button A click;
                Toast.makeText(getApplicationContext(), "Gym/Police", Toast.LENGTH_SHORT).show();
                break;
            case R.id.upperCampusMainMapButton:// handle button A click;
                Toast.makeText(getApplicationContext(), "Upper Campus", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
    }

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
