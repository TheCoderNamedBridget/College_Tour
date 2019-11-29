package com.example.collegetour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/*TODO: General All make all image backgrounds "light"
*  Improve overall UI -> intuitive navigation, obvious symbols(home button), correctly sized buttons and images*/

/*TODO: Urgents:
   Learn how to make pop ups that are user interactive
   Figure out how to set up merchant account

*/

//data entry of all the meals for hillside, parkside, beachside --> quick on buttons to make breakfast,lunch,dinner menus appear --> only breakfast lunch or dinner will be shown
//make key:value map for all buildings
//make search bar more intuitive
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

    String curDiningHall = "";
    String curMeal = "";



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
            case R.id.toMenus:// handle button A click;
                setContentView(R.layout.activity_dininghall_menus);
                break;
            case R.id.beachsideDining:// handle button A click;
                curDiningHall = "Beachside";
                setContentView(R.layout.activity_menu_dropdowns);
                break;
            case R.id.hillsideDining:// handle button A click;
                curDiningHall = "Hillside";
                setContentView(R.layout.activity_menu_dropdowns);
                break;
            case R.id.parksideDining:// handle button A click;
                curDiningHall = "Parkside";
                setContentView(R.layout.activity_menu_dropdowns);
                break;
            case R.id.breakfastButton:// handle button A click
                curMeal = "breakfast";
                //set text to breakfast options for current date
                break;
            case R.id.lunchButton:// handle button A click;
                curMeal = "lunch";
                //set text to lunch options for current date
                break;
            case R.id.dinnerButton:// handle button A click;
                curMeal = "dinner";
                //set text to dinner options for current date
                break;
            case R.id.alwaysThere:// handle button A click;
                curMeal = "regular";
                //set text to usual options for current date
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
    }

    //method that sets current meal options text to the text on file
    //lights up the clicked button and unlights the unselected buttons
    public void changeMenuOptions (){
        if (curDiningHall.equals("Hillside")){
            if (curMeal.equals("breakfast")){
                TextView textToSet = findViewById(R.id.currentMenu);
                textToSet.setText(readMenuFile("Hillside Dining"));
            } else if (curMeal.equals("lunch")){

            } else if (curMeal.equals("dinner")){

            }
        } else if (curDiningHall.equals("Parkside")){

        } else if (curDiningHall.equals("Beachside")){

        }
    }

    public String readMenuFile (String name){
        String menuOfCurMeal = "";
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(name);
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
            System.out.println("PLSWORK " + s.next());
            menuOfCurMeal = menuOfCurMeal + s.nextLine();
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
        }

        System.out.println(menuOfCurMeal);
        return menuOfCurMeal;
    }

}
