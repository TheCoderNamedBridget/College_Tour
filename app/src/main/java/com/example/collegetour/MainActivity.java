package com.example.collegetour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/*TODO: General All make all image backgrounds "light"

* */


/*TODO: Urgents:
   Learn how to make pop ups that are user interactive


   */

//Paint redo all of the maps: Main, Dorms, Police/Gym, Upper campus, Lower campus
//Erase bus stations, and pay stations from all maps --> make into buttons that can be filtered out
//For back to main map highlight current area of map and resize map so that whole map shows
//Make staff directory in recycler view
//Hard code staff directory first then later try to make it work with school website
//Make the menu section work -> look at foldable

//Resize arrows so that they look nice
//Resize specific maps so that they look nice and are usable
//make caveman instructions more clear, human
//make caveman speech change -> give instructions
//make caveman speak random college tour and csulb facts
//make set preferences screen work
//make user homepage more usable, user can choose

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Runs and show different screens




    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dormImageMainMapButton:// handle button A click;
                setContentView(R.layout.activity_dorms);
                break;
            case R.id.lowerCampusMainMapButton:// handle button B click;
                setContentView(R.layout.activity_lower_campus);
                break;
            case R.id.gymPoliceMainMapButton:// handle button A click;
                setContentView(R.layout.activity_engineering_police_gym);
                break;
            case R.id.upperCampusMainMapButton:// handle button A click;
                setContentView(R.layout.activty_upper_campus);
                break;
            case R.id.toLowerCampusFromGym:// handle button A click;
                setContentView(R.layout.activity_lower_campus);
                break;
            case R.id.toLowerCampusFromUpperCampus:// handle button A click;
                setContentView(R.layout.activity_lower_campus);
                break;
            case R.id.toLowerCampusFromDorms:// handle button A click;
                setContentView(R.layout.activity_lower_campus);
                break;
            case R.id.toUpperCampusFromLowerCampus:// handle button A click;
                setContentView(R.layout.activty_upper_campus);
                break;
            case R.id.toDormsFromLowerCampus:// handle button A click;
                setContentView(R.layout.activity_dorms);
                break;
            case R.id.toGymFromLowerCampus:// handle button A click;
                setContentView(R.layout.activity_engineering_police_gym);
                break;
            case R.id.backToMainMap:// handle button A click;
                setContentView(R.layout.activity_main_map);
                break;
            case R.id.toDiningMenus:// handle button A click;
                setContentView(R.layout.activity_dininghall_menus);
                break;
            case R.id.toMainActivity:// handle button A click;
                setContentView(R.layout.activity_main);
                break;
            case R.id.toStaffDirectory:// handle button A click;
                setContentView(R.layout.activity_staff_directory);
                break;
            case R.id.settingsGear:// handle button A click;
                setContentView(R.layout.activity_settings);
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
    }

}
