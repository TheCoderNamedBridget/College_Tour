package com.example.collegetour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*TODO: General All make all image backgrounds "light"
*  Improve overall UI -> intuitive navigation, obvious symbols(home button), correctly sized buttons and images*/

/*TODO: Urgents:
   Figure out how to set up merchant account

*/

//data entry of all the meals for hillside, parkside, beachside --> quick on buttons to make breakfast,lunch,dinner menus appear --> only breakfast lunch or dinner will be shown
//make key:value map for all buildings
//make search bar more intuitive
//For back to main map highlight current area of map and resize map so that whole map shows
//Hard code staff directory first then later try to make it work with school website

//make caveman instructions more clear, human
//make caveman speech change -> give instructions
//make caveman speak random college tour and csulb facts
//make set preferences screen work
//make user homepage more usable, user can choose

public class MainActivity extends AppCompatActivity {

    String curDiningHall = "";
    String curMeal = "";
    ArrayList<Contact> contacts;
    ArrayList<Building> buildings = new ArrayList<>(20);
    ArrayList<Building> buildingsWithKeywords = new ArrayList<>(20);

    String curBuilding = "";//so when using search it can just look for key words in the file under each building and return the building or buildings with keywords

    String userSearchInput = "";

    String hillsideDiningStationsBreakfast = "Omelet Bar:\r\nHot Cereal 1:\r\nHot Cereal 2:\r\nCereal Bar:\r\nBeverage Bar:\r\nFruit Cart:\r\nWaffle Bar:\r\nBfast Special:\r\nEggs:\r\nHot Side 1:\r\nHot Side 2:\r\nPotatoes:\r\nVegan:\r\n" ;
    String hillsideDiningStationsLunchAndDinner = "Soup 1:\r\nSoup 2:\r\nSalad & Deli:\r\nCereal Bar:\r\nBeverage Bar:\r\nFruit Cart:\r\nWaffle Bar:\r\nAction Station:\r\nMain Entree:\r\nVeggie option:\r\nGrill Station:\r\nStir Fry:\r\nGrain Bowl:\r\nDessert:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lower_campus);

        readBuildingFileAddBuildingToList(); // Figure out why this breaks code and come up with a better way to parse file
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
            case R.id.settingsGear:// handle button A click;
                setContentView(R.layout.activity_settings);
                break;
            case R.id.toMenus:// handle button A click;
                setContentView(R.layout.activity_dininghall_menus);
                break;
            case R.id.beachsideDining:// handle button A click;
                curDiningHall = "Beachside";
                setContentView(R.layout.activity_menu_dropdowns);
                Button hallToSet = findViewById(R.id.hall);
                hallToSet.setText("Beachside");
                break;
            case R.id.hillsideDining:// handle button A click;
                curDiningHall = "Hillside";
                setContentView(R.layout.activity_menu_dropdowns);
                Button hallToSet1 = findViewById(R.id.hall);
                hallToSet1.setText("Hillside");
                break;
            case R.id.parksideDining:// handle button A click;

                curDiningHall = "Parkside";
                setContentView(R.layout.activity_menu_dropdowns);
                Button hallToSet2 = findViewById(R.id.hall);
                hallToSet2.setText("Parkside");
                break;
            case R.id.breakfastButton:// handle button A click
                curMeal = "breakfast";
                if (curDiningHall.equals("Hillside")){
                    TextView changeBreakfastText = findViewById(R.id.hillsideStations);
                    changeBreakfastText.setText(hillsideDiningStationsBreakfast);
                }
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
                if (curDiningHall.equals("Hillside")){
                    TextView changeLunchText = findViewById(R.id.hillsideStations);
                    changeLunchText.setText(hillsideDiningStationsLunchAndDinner);
                }
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
                if (curDiningHall.equals("Hillside")){
                    TextView changeLunchText = findViewById(R.id.hillsideStations);
                    changeLunchText.setText(hillsideDiningStationsLunchAndDinner);
                }
                Button breakfast = findViewById(R.id.breakfastButton);
                breakfast.setBackgroundColor(Color.LTGRAY);
                Button lunch = findViewById(R.id.lunchButton);
                lunch.setBackgroundColor(Color.LTGRAY);
                Button dinner = findViewById(R.id.dinnerButton);
                dinner.setBackgroundColor(Color.GREEN);
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
            default:
                throw new RuntimeException("Unknow button ID");
        }
        getCurrentTimeUsingDate();
    }

    //Shows pop up when a building is clicked
    //TODO: FIGURE OU HOW TO DO POP UPS
    //TODO: FINISH HARD CODING BUILDING INFO
    public void onPop (String nameOfBuilding){

        for (int i = 0; i < buildings.size(); i++){
            if (buildings.get(i).getName().equals(nameOfBuilding)){
                Toast toast = Toast.makeText(MainActivity.this, buildings.get(i).getName() + buildings.get(i).getHours() + buildings.get(i).getDescription(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
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
            System.out.println("WHY ");
            while (s.hasNext() && menuOfCurMeal.equals("")){
                String value = s.nextLine();
                System.out.println("WHY " + value);
                if (value.contains(curMeal) && value.contains("3/2")){//&& value.contains(getCurrentTimeUsingDate())
                    value = value.substring(14);
                    value = value.replace(",", "\r\n");
                    value = value.replace("[", "");
                    value = value.replace("]", "");
                    menuOfCurMeal = menuOfCurMeal + value.toUpperCase();
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
                    System.out.println("Name: " + curBuilding + " Hours: " + hours + " Description: " + description);
                }
                if (value.contains("HOURS")){
                    hours = value.substring(7);
                    System.out.println("Name: " + curBuilding + " Hours: " + hours + " Description: " + description);
                }
                if (value.contains("DESCRIPTION")){
                    description = value;

                }
//                System.out.println("WHY ");
//                System.out.println("WHY " + "[" + curBuilding +"][" + hours +"][" + description+"]");
                if (!curBuilding.equals("") && !hours.equals("") && !description.equals("")){
                    Building newBuilding = new Building(curBuilding,hours,description);
//                    System.out.println("Name: 22 " + newBuilding.getName() + " Hours: " + newBuilding.getHours() + " Description: " + newBuilding.getDescription());
                    buildings.add(newBuilding);
//                    System.out.println("Name: 22 " + buildings.get(0).getName());
//                    System.out.println("Name: 22 " + buildings.size());
//                    System.out.println("Name: 22 " + curBuilding + " Hours: " + hours + " Description: " + description);
                    curBuilding = "";
                    hours = "";
                    description = "";
                }
            }
            System.out.println("DONE");
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
        }
    }

    //manipulate user input
    //parse building file
    //find buildings with description or tags containing keywords
    //return buildings with keyword highlighted
    //TODO: Add feature if building isn't in current screen then ask if they want to search whole campus, then search whole caampus
    //TODO: highlight building buttons that have keywords in the description
    public void searchForBuilding (String userInput){//curScreen == lower campus then check the lower campus file if doesn't find anything then ask if they to want to chekc whole campus
        buildingsWithKeywords.clear();
        String keyword = userInput.toLowerCase();
        System.out.println("FOUND " + keyword);
        AssetManager assetManager = getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("Building Info");
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");

            while (s.hasNext()){
                String value = s.nextLine();
                if (value.contains("ISBUILDING")){
                    curBuilding = value.substring(11);
                } else if (value.contains(keyword)){
                    if (buildingsWithKeywords.size() == 0 || (buildingsWithKeywords.size() != 0 && !(buildingsWithKeywords.get(buildingsWithKeywords.size() -1).getName()).equals(curBuilding))){
                        buildingsWithKeywords.add(new Building(curBuilding));
                        System.out.println("FOUND " + curBuilding);
                    }
                }
            }
            System.out.println("FOUND " + buildingsWithKeywords.size());
            for (int i = 0;i < buildingsWithKeywords.size();i++){
                System.out.println("FOUNDinloop " + buildingsWithKeywords.get(i).getName());
            }

            System.out.println("DONE");
        }
        catch (IOException e){
            Log.e("message: ",e.getMessage());
        }

    }

}





//    public static void getCurrentTimeUsingCalendar() {
//        Calendar cal = Calendar.getInstance();
//        Date date=cal.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        String formattedDate=dateFormat.format(date);
//        System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate);
//    }
