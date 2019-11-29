package com.example.collegetour;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/*TODO: General All make all image backgrounds "light"
*  Improve overall UI -> intuitive navigation, obvious symbols(home button), correctly sized buttons and images*/

/*TODO: Urgents:
   Learn how to make pop ups that are user interactive
   Figure out how to set up merchant account

*/

//data entry of all the meals for hillside, parkside, beachside --> quick on buttons to make breakfast,lunch,dinner menus appear --> only breakfast lunch or dinner will be shown
//make text that is shown more readable
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
                Button changeBreakfastButton = findViewById(R.id.breakfastButton);
                changeBreakfastButton.setBackgroundColor(Color.GREEN);
                Button changeLunchButton = findViewById(R.id.lunchButton);
                changeLunchButton.setBackgroundColor(Color.LTGRAY);
                Button changeDinnerButton = findViewById(R.id.dinnerButton);
                changeDinnerButton.setBackgroundColor(Color.LTGRAY);
                changeMenuOptions();
                break;
            case R.id.lunchButton:// handle button A click;
                curMeal = "lunch";
                Button breakfastButton = findViewById(R.id.breakfastButton);
                breakfastButton.setBackgroundColor(Color.LTGRAY);
                Button lunchButton = findViewById(R.id.lunchButton);
                lunchButton.setBackgroundColor(Color.GREEN);
                Button dinnerButton = findViewById(R.id.dinnerButton);
                dinnerButton.setBackgroundColor(Color.LTGRAY);
                changeMenuOptions();
                break;
            case R.id.dinnerButton:// handle button A click;
                curMeal = "dinner";
                Button breakfast = findViewById(R.id.breakfastButton);
                breakfast.setBackgroundColor(Color.LTGRAY);
                Button lunch = findViewById(R.id.lunchButton);
                lunch.setBackgroundColor(Color.LTGRAY);
                Button dinner = findViewById(R.id.dinnerButton);
                dinner.setBackgroundColor(Color.GREEN);
                changeMenuOptions();
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
        getCurrentTimeUsingDate();
    }

    //method that sets current meal options text to the text on file
    //lights up the clicked button and unlights the unselected buttons
    public void changeMenuOptions (){
        TextView textToSet = findViewById(R.id.currentMenu);
        if (curDiningHall.equals("Hillside")){
            textToSet.setText(readMenuFile("Hillside Dining"));
        } else if (curDiningHall.equals("Parkside")){
            textToSet.setText(readMenuFile("Parkside Dining"));
        } else if (curDiningHall.equals("Beachside")){
            textToSet.setText(readMenuFile("Beachside Dining"));
        }
    }


    //reads file for a particular day and returns the values in file
    public String readMenuFile (String name){
        String menuOfCurMeal = "";
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(name);
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
            System.out.println("WHY ");
            while (s.hasNext() && menuOfCurMeal.equals("")){
                String value = s.nextLine();
                System.out.println("WHY " + value);
                //TODO come back and make this part work
                if (value.contains(curMeal) && value.contains("1/23")){//&& value.contains(getCurrentTimeUsingDate())
                    menuOfCurMeal = menuOfCurMeal + value;
                }
            }
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
        }

        System.out.println("PLS " + menuOfCurMeal);
        return menuOfCurMeal;
    }



    public static String getCurrentTimeUsingDate() {
        DateTimeFormatter dtf = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ofPattern("MM/dd");
        }
        LocalDate localDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            localDate = LocalDate.now();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            System.out.println(dtf.format(localDate));
        }
        return localDate.toString();
    }


//    public static void getCurrentTimeUsingCalendar() {
//        Calendar cal = Calendar.getInstance();
//        Date date=cal.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        String formattedDate=dateFormat.format(date);
//        System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate);
//    }
}
