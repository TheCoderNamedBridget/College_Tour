package com.example.collegetour;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static androidx.core.view.ViewCompat.setBackgroundTintList;

/*
*  Improve overall UI -> intuitive navigation, obvious symbols(home button), correctly sized buttons and images*/

//TODO: Publish version with dining hall menus working and coming soon disclaimers for the other 2 sections
//TODO: Make tutorial for the app


//JUST SEND IT
//CHANGE BUTTONS


public class MainActivity extends AppCompatActivity {

    String curDiningHall = "";
    String curMeal = "";
    ArrayList<Contact> contacts; // will the the list created of staff
    ArrayList<Building> buildings = new ArrayList<>(20);
    ArrayList<Building> buildingsWithKeywords = new ArrayList<>(20);

    String curBuilding = "";//so when using search it can just look for key words in the file under each building and return the building or buildings with keywords
    String curScreen = "";//mainmap, lowercampus, uppercampus, police, dorms
    String curCampusArea = "";
    String campusAreaWithKeyword = "";
    Boolean searchedInThisArea = false;
    String areaLastIn = "";
    String userSearchInput = "";

    String hillsideDiningStationsBreakfast = "Omelet Bar:\r\nHot Cereal 1:\r\nHot Cereal 2:\r\nCereal Bar:\r\nBeverage Bar:\r\nFruit Cart:\r\nWaffle Bar:\r\nBfast Special:\r\nEggs:\r\nHot Side 1:\r\nHot Side 2:\r\nPotatoes:\r\nVegan:\r\n" ;
    String hillsideDiningStationsLunchAndDinner = "Soup 1:\r\nSoup 2:\r\nSalad & Deli:\r\nCereal Bar:\r\nBeverage Bar:\r\nFruit Cart:\r\nWaffle Bar:\r\nAction Station:\r\nMain Entree:\r\nVeggie option:\r\nGrill Station:\r\nStir Fry:\r\nGrain Bowl:\r\nDessert:";

    String parksideDiningStationsBreakfast = "Breakfast Bar:";
    String parksideDiningStationsLunchAndDinner = "Soups 1:\r\nSoups 2:\r\nSalad Bar:\r\nAction Station:\r\nMain Dish:\r\nVeggie/Vegan:\r\nGrill:\r\nDeli:\r\nPizza:\r\nChef Table:\r\nSweets:";

    String beachsideDiningStationsBreakfast = "Hot Cereal:\r\nHome:\r\nHome:\r\nHome:\r\nHome:";
    String beachsideDiningStationsLunchAndDinner = "Soups 1:\r\nSoups 2:\r\nSalad Bar:\r\nDeli(entree):\r\nAt Home:\r\nThe Bar:\r\nGrill:\r\nDeli:\r\nPizza:\r\nSweets:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readBuildingFileAddBuildingToList(); // Figure out why this breaks code and come up with a better way to parse file
    }

    //TODO: finish recycler view scroll
//write practice staff directory
    //create list that is sorted by department then alphabetically
    //list order will match the recycer view order

    public void onItemClick(View v, int pos){
        //linearLayoutManager.scrollToPositionWithOffset(2, 20);
    }

    //Runs and show different screens
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dormImageMainMapButton:
                curScreen = "dorms";
                setContentView(R.layout.activity_dorms);

                break;
            case R.id.lowerCampusMainMapButton:
                curScreen = "lowercampus";
                setContentView(R.layout.activity_lower_campus);

                break;
            case R.id.gymPoliceMainMapButton:
                curScreen = "police";
                setContentView(R.layout.activity_engineering_police_gym);

                break;
            case R.id.upperCampusMainMapButton:
                curScreen = "uppercampus";
                setContentView(R.layout.activity_upper_campus);

                break;
            case R.id.toLowerCampusFromGym:
                curScreen = "lowercampus";
                setContentView(R.layout.activity_lower_campus);

                break;
            case R.id.toLowerCampusFromUpperCampus:
                curScreen = "lowercampus";
                setContentView(R.layout.activity_lower_campus);

                break;
            case R.id.toLowerCampusFromDorms:
                curScreen = "lowercampus";
                setContentView(R.layout.activity_lower_campus);

                break;
            case R.id.toUpperCampusFromLowerCampus:
                curScreen = "uppercampus";
                setContentView(R.layout.activity_upper_campus);

                break;
            case R.id.toDormsFromLowerCampus:
                curScreen = "dorms";
                setContentView(R.layout.activity_dorms);

                break;
            case R.id.toGymFromLowerCampus:
                curScreen = "police";
                setContentView(R.layout.activity_engineering_police_gym);

                break;
            case R.id.backToMainMap:
                curScreen = "mainmap";
                setContentView(R.layout.activity_main_map);

                break;
            case R.id.toDiningMenus:
                curScreen = "dining";
                setContentView(R.layout.activity_menu_dropdowns);

                break;
            case R.id.toMainActivity:
                curScreen = "main";
                setContentView(R.layout.activity_main);

                break;
            case R.id.toStaffDirectory:
                setContentView(R.layout.activity_staff_directory);
                // Lookup the recyclerview in activity layout
                RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
                // Initialize contacts
                contacts = Contact.createContactsList(20);
                // Create adapter passing in the sample user data
                ContactsAdapter adapter = new ContactsAdapter(contacts);
                // Attach the adapter to the recyclerview to populate items
                rvContacts.setAdapter(adapter);
                // Set layout manager to position the items
                rvContacts.setLayoutManager(new LinearLayoutManager(this));
                // That's all!
                break;
            case R.id.settingsGear:
                setContentView(R.layout.activity_settings);
                break;
            case R.id.beachsideDining:
                curDiningHall = "Beachside";
                setContentView(R.layout.activity_menu_dropdowns);
                Button hallToSet = findViewById(R.id.beachsideDining);
                hallToSet.setTextColor(Color.BLACK);

                Button hallToSet1 = findViewById(R.id.hillsideDining);
                hallToSet1.setTextColor(Color.LTGRAY);

                Button hallToSet2 = findViewById(R.id.parksideDining);
                hallToSet2.setTextColor(Color.LTGRAY);

                Button breakfastButton3 = findViewById(R.id.breakfastButton);
                breakfastButton3.setVisibility(View.VISIBLE);

                Button lunchButton3 = findViewById(R.id.lunchButton);
                lunchButton3.setVisibility(View.VISIBLE);

                Button dinnerButton3 = findViewById(R.id.dinnerButton);
                dinnerButton3.setVisibility(View.VISIBLE);
                break;
            case R.id.hillsideDining:// handle button A click;

                curDiningHall = "Hillside";
                setContentView(R.layout.activity_menu_dropdowns);
                Button hallToSet3 = findViewById(R.id.hillsideDining);
                hallToSet3.setTextColor(Color.BLACK);

                Button hallToSet4 = findViewById(R.id.parksideDining);
                hallToSet4.setTextColor(Color.LTGRAY);

                Button hallToSet5 = findViewById(R.id.beachsideDining);
                hallToSet5.setTextColor(Color.LTGRAY);

                Button breakfastButton1 = findViewById(R.id.breakfastButton);
                breakfastButton1.setVisibility(View.VISIBLE);

                Button lunchButton1 = findViewById(R.id.lunchButton);
                lunchButton1.setVisibility(View.VISIBLE);

                Button dinnerButton1 = findViewById(R.id.dinnerButton);
                dinnerButton1.setVisibility(View.VISIBLE);
                break;
            case R.id.parksideDining:// handle button A click;
                curDiningHall = "Parkside";
                setContentView(R.layout.activity_menu_dropdowns);
                Button hallToSet6 = findViewById(R.id.parksideDining);
                hallToSet6.setTextColor(Color.BLACK);

                Button hallToSet7 = findViewById(R.id.hillsideDining);
                hallToSet7.setTextColor(Color.LTGRAY);

                Button hallToSet8 = findViewById(R.id.beachsideDining);
                hallToSet8.setTextColor(Color.LTGRAY);

                Button breakfastButton2 = findViewById(R.id.breakfastButton);
                breakfastButton2.setVisibility(View.VISIBLE);

                Button lunchButton2 = findViewById(R.id.lunchButton);
                lunchButton2.setVisibility(View.VISIBLE);

                Button dinnerButton2 = findViewById(R.id.dinnerButton);
                dinnerButton2.setVisibility(View.VISIBLE);
                break;
            case R.id.breakfastButton:// handle button A click
                curMeal = "breakfast";
                if (curDiningHall.equals("Hillside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(hillsideDiningStationsBreakfast);
                } else if (curDiningHall.equals("Parkside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(parksideDiningStationsBreakfast);
                } else if (curDiningHall.equals("Beachside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(beachsideDiningStationsBreakfast);
                }
                Button changeBreakfastButton = findViewById(R.id.breakfastButton);
                changeBreakfastButton.setTextColor(Color.BLACK);

                Button changeLunchButton = findViewById(R.id.lunchButton);
                changeLunchButton.setTextColor(Color.LTGRAY);

                Button changeDinnerButton = findViewById(R.id.dinnerButton);
                changeDinnerButton.setTextColor(Color.LTGRAY);

                changeMenuOptions();
                break;
            case R.id.lunchButton:// handle button A click;
                curMeal = "lunch";
                if (curDiningHall.equals("Hillside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(hillsideDiningStationsLunchAndDinner);
                } else if (curDiningHall.equals("Parkside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(parksideDiningStationsLunchAndDinner);
                } else if (curDiningHall.equals("Beachside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(beachsideDiningStationsLunchAndDinner);
                }
                Button breakfastButton = findViewById(R.id.breakfastButton);
                breakfastButton.setTextColor(Color.LTGRAY);

                Button lunchButton = findViewById(R.id.lunchButton);
                lunchButton.setTextColor(Color.BLACK);

                Button dinnerButton = findViewById(R.id.dinnerButton);
                dinnerButton.setTextColor(Color.LTGRAY);

                changeMenuOptions();
                break;
            case R.id.dinnerButton:// handle button A click;
                curMeal = "dinner";
                if (curDiningHall.equals("Hillside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(hillsideDiningStationsLunchAndDinner);
                } else if (curDiningHall.equals("Parkside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(parksideDiningStationsLunchAndDinner);
                } else if (curDiningHall.equals("Beachside")){
                    TextView changeBreakfastText = findViewById(R.id.stations);
                    changeBreakfastText.setText(beachsideDiningStationsLunchAndDinner);
                }
                Button breakfast = findViewById(R.id.breakfastButton);
                breakfast.setTextColor(Color.LTGRAY);

                Button lunch = findViewById(R.id.lunchButton);
                lunch.setTextColor(Color.LTGRAY);

                Button dinner = findViewById(R.id.dinnerButton);
                dinner.setTextColor(Color.BLACK);

                changeMenuOptions();
                break;
            case R.id.PYR:
                onPop("PYR");
                break;
            case R.id.CPAC:
                onPop("CPAC");
                break;
            case R.id.DC:
                onPop("DC");
                break;
            case R.id.UMC:
                onPop("UMC");
                break;
            case R.id.BAC:
                onPop("BAC");
                break;
            case R.id.KIN:
                onPop("KIN");
                break;
            case R.id.HC:
                onPop("HC");
                break;
            case R.id.UAM:
                onPop("UAM");
                break;
            case R.id.HHS1:
                onPop("HHS1");
                break;
            case R.id.HHS2:
                onPop("HHS2");
                break;
            case R.id.RC:
                onPop("RC");
                break;
            case R.id.CDC:
                onPop("CDC");
                break;
            case R.id.HRL:
                onPop("HRL");
                break;
            case R.id.JG:
                onPop("JG");
                break;
            case R.id.BH:
                onPop("BH");
                break;
            case R.id.LAH:
                onPop("LAH");
                break;
            case R.id.NUR:
                onPop("NUR");
                break;
            case R.id.FCS:
                onPop("FCS");
                break;
            case R.id.SOR:
                onPop("SOR");
                break;
            case R.id.IH:
                onPop("IH");
                break;
            case R.id.SHS:
                onPop("SHS");
                break;
            case R.id.CBA:
                onPop("CBA");
                break;
            case R.id.LCH:
                onPop("LCH");
                break;
            case R.id.PTS:
                onPop("PTS");
                break;
            case R.id.SRWC:
                onPop("SRWC");
                break;
            case R.id.DESN:
                onPop("DESN");
                break;
            case R.id.FND:
                onPop("FND");
                break;
            case R.id.SSPA:
                onPop("SSPA");
                break;
            case R.id.OP:
                onPop("OP");
                break;
            case R.id.HSD:
                onPop("HSD");
                break;
            case R.id.VEC:
                onPop("VEC");
                break;
            case R.id.EN2:
                onPop("EN2");
                break;
            case R.id.EN3:
                onPop("EN3");
                break;
            case R.id.EN4:
                onPop("EN4");
                break;
            case R.id.ECS:
                onPop("ECS");
                break;
            case R.id.MS:
                onPop("MS");
                break;
            case R.id.REC:
                onPop("REC");
                break;
            case R.id.FM:
                onPop("FM");
                break;
            case R.id.CORP:
                onPop("CORP");
                break;
            case R.id.UP:
                onPop("UP");
                break;
            case R.id.REPR:
                onPop("REPR");
                break;
            case R.id.ET1:
                onPop("ET");
                break;
            case R.id.ET2:
                onPop("ET");
                break;
            case R.id.USU:
                onPop("USU");
                break;
            case R.id.CP:
                onPop("CP");
                break;
            case R.id.CAFE:
                onPop("CAFE");
                break;
            case R.id.BKS:
                onPop("BKS");
                break;
            case R.id.MLSC:
                onPop("MLSC");
                break;
            case R.id.LIB:
                onPop("LIB");
                break;
            case R.id.FO2:
                onPop("FO2");
                break;
            case R.id.FO3:
                onPop("FO3");
                break;
            case R.id.FO4:
                onPop("FO4");
                break;
            case R.id.FO5:
                onPop("FO5");
                break;
            case R.id.PSY:
                onPop("PSY");
                break;
            case R.id.AS:
                onPop("AS");
                break;
            case R.id.MMC:
                onPop("MMC");
                break;
            case R.id.ED1:
                onPop("ED1");
                break;
            case R.id.ED2:
                onPop("ED2");
                break;
            case R.id.ANNEX:
                onPop("ANNEX");
                break;
            case R.id.MIC:
                onPop("MIC");
                break;
            case R.id.PH2:
                onPop("PH2");
                break;
            case R.id.PH1:
                onPop("PH1");
                break;
            case R.id.LH:
                onPop("LH");
                break;
            case R.id.MHB:
                onPop("MHB");
                break;
            case R.id.KKJZ:
                onPop("KKJZ");
                break;
            case R.id.LA5:
                onPop("LA5");
                break;
            case R.id.LA4:
                onPop("LA4");
                break;
            case R.id.LA3:
                onPop("LA3");
                break;
            case R.id.LA2:
                onPop("LA2");
                break;
            case R.id.LA1:
                onPop("LA1");
                break;
            case R.id.BA:
                onPop("BA");
                break;
            case R.id.FA4:
                onPop("FA4");
                break;
            case R.id.FA3:
                onPop("FA3");
                break;
            case R.id.FA2:
                onPop("FA3");
                break;
            case R.id.FA1:
                onPop("FA3");
                break;
            case R.id.UTC:
                onPop("UTC");
                break;
            case R.id.UT:
                onPop("UT");
                break;
            case R.id.HSCI:
                onPop("HSCI");
                break;
            case R.id.LAB:
                onPop("LAB");
                break;
            case R.id.STTA:
                onPop("STTA");
                break;
            case R.id.searchForBuilding:
                searchForBuilding();
                break;
            case R.id.searchForStaff:
                searchForStaff();
                break;
            default:
                throw new RuntimeException("Unknow button ID");
        }
        getCurrentTimeUsingDate();
    }

    //Shows pop up when a building is clicked
    public void onPop (String nameOfBuilding){

        for (int i = 0; i < buildings.size(); i++){
            if (buildings.get(i).getName().equals(nameOfBuilding)){

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(buildings.get(i).getName());
                alertDialog.setMessage("Hours: " + buildings.get(i).getHours() + " " + buildings.get(i).getDescription());
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }

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

            String currentTime = getCurrentTimeUsingDate();
            if (currentTime.length() != 5) {
                if (currentTime.substring(0,2).contains("/")) {
                    currentTime = "0" + currentTime;
                }
                if (currentTime.length() != 5) {
                    currentTime = currentTime.substring(0,3) + "0" + currentTime.substring(3);
                }
            }
            while (s.hasNext() && menuOfCurMeal.equals("")){
                String value = s.nextLine();
                if (value.contains(curMeal) && value.contains(currentTime)){
                    value = value.substring(18);
                    value = value.replace(",", "\r\n");
                    value = value.replace("[", "");
                    value = value.replace("]", "");
                    menuOfCurMeal = menuOfCurMeal + value;
                }
            }
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
        }

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
        }

        String theDate = "";
        if (dtf.format(localDate).substring(0,1).equals("0")){
            theDate = dtf.format(localDate).substring(1);
        }
        return theDate;
    }




    //reads file for a building's name hours description
    //when building is clicked this will display on the pop up
    public void readBuildingFileAddBuildingToList (){
        String hours = "";
        String description = "";
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("Building Info");
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");

            while (s.hasNext()){
                String value = s.nextLine();
                if (value.contains("ISBUILDING")){
                    curBuilding = value.substring(11);
                }
                if (value.contains("HOURS")){
                    hours = "\r\n" + value.substring(7);
                }
                if (value.contains("DESCRIPTION")){
                    description = "\r\n" + value;

                }
                if (!curBuilding.equals("") && !hours.equals("") && !description.equals("")){
                    Building newBuilding = new Building(curBuilding,hours,description);
                    buildings.add(newBuilding);
                    curBuilding = "";
                    hours = "";
                    description = "";
                }
            }
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
        }
    }

    //manipulate user input
    //parse building file
    //find buildings with description or tags containing keywords
    //return buildings with keyword highlighted
    //COME
    public void searchForBuilding (){//curScreen == lower campus then check the lower campus file if doesn't find anything then ask if they to want to chekc whole campus
        EditText input = findViewById(R.id.searchBarText);

        if (!areaLastIn.equals(curScreen)){
            areaLastIn = curScreen;
            buildingsWithKeywords.clear();
        } else if (areaLastIn.equals(curScreen) && !curScreen.equals("mainmap") ){
            if (buildingsWithKeywords.size() != 0){
                for (int j = 0; j < buildingsWithKeywords.size(); j ++){
                    int resID = getResources().getIdentifier(buildingsWithKeywords.get(j).getName(), "id",getPackageName());
                    ImageButton But = findViewById(resID);
                    But.setColorFilter(null);
                }
                buildingsWithKeywords.clear();
            }

        } else if (areaLastIn.equals("mainmap")){
            buildingsWithKeywords.clear();

        }

        String keyword = input.getText().toString().toLowerCase();
        if (keyword.equals("")){
            return;
        }
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            if (curScreen.equals("dorms")){
                inputStream = assetManager.open("Dorm Buildings");
            } else if (curScreen.equals("police")){
                inputStream = assetManager.open("Gym Police Engineering Buildings");
            } else if (curScreen.equals("lowercampus")){
                inputStream = assetManager.open("Lower Campus Buildings");
            } else if (curScreen.equals("uppercampus")){
                inputStream = assetManager.open("Upper Campus Buildings");
            } else if (curScreen.equals("mainmap")){
                inputStream = assetManager.open("Building Info");
            }
            //inputStream = assetManager.open("Building Info");
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
            while (s.hasNext()){
                String value = s.nextLine();
                if (value.contains("ISBUILDING")){
                    curBuilding = value.substring(11);
                } else if (value.contains(keyword)){
                    if (buildingsWithKeywords.size() == 0 || (buildingsWithKeywords.size() != 0 && !(buildingsWithKeywords.get(buildingsWithKeywords.size() -1).getName()).equals(curBuilding))){
                        buildingsWithKeywords.add(new Building(curBuilding));
                    }
                } else if (value.contains("CAMPUSAREA") && buildingsWithKeywords.size() == 0){
                    curCampusArea = value.substring(11);
                    campusAreaWithKeyword = curCampusArea;
                }
            }
            if (buildingsWithKeywords.size() == 0 && !curScreen.equals("mainmap")){
                Toast toast = Toast.makeText(MainActivity.this, "No Results Found :(", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else if (buildingsWithKeywords.size() != 0 && !curScreen.equals("mainmap")){
                if (buildingsWithKeywords.size() == 1){
                    Toast toast = Toast.makeText(MainActivity.this, "1 Result Found", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (buildingsWithKeywords.size() != 1 ){
                    Toast toast = Toast.makeText(MainActivity.this, buildingsWithKeywords.size() + " Results Found", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

                for (int j = 0; j < buildingsWithKeywords.size(); j ++){

                    int resID = getResources().getIdentifier(buildingsWithKeywords.get(j).getName(), "id",getPackageName());
                    ImageButton But = findViewById(resID);
                    But.setColorFilter(Color.argb(255, 255, 0, 0));


                }
            }
            if (curScreen.equals("mainmap") && !campusAreaWithKeyword.equals("")){
                Toast toast = Toast.makeText(MainActivity.this, " Results Found in " + campusAreaWithKeyword, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
        }
    }


    //Creates the list of staff names and emails from the staff file
    public void readStaffFileAddStaffToList(){

    }
    //parse staff list
    //scroll recycler view to searched staff
    public void searchForStaff (){

    }

}



//    public static void getCurrentTimeUsingCalendar() {
//        Calendar cal = Calendar.getInstance();
//        Date date=cal.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        String formattedDate=dateFormat.format(date);
//    }
