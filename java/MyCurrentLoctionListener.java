package amyslawson.com.virtualcampustour;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Amy on 10/16/2014.
 */
public class MyCurrentLoctionListener implements LocationListener {

    private String myLocation;
    List<Building> buildingList;
    Activity passedActivity;

    public MyCurrentLoctionListener (Activity activity) {
        passedActivity=activity;
    }


    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        myLocation = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();

        GPSData currGPS = new GPSData (latitude, longitude);

        run(currGPS);

    }


    public void run(GPSData currGPS) {

        try {

//            String messageStr = currGPS.getDataString();

            JSONArray locationArray = new JSONArray();

            JSONObject locationObj = new JSONObject();
            locationObj.put("Lat", currGPS.getLatitude());
            locationObj.put("Lng", currGPS.getLongitude());
            locationArray.put(locationObj);

            JSONObject bundledLocObj = new JSONObject ();
            bundledLocObj.put("location", locationObj);

            JSONObject timeObj = new JSONObject();
            timeObj.put("unixTime", currGPS.getCurrTime());

            JSONArray responseArray = new JSONArray();
            responseArray.put(timeObj);
            responseArray.put(bundledLocObj);

            JSONObject responseObj = new JSONObject();
            responseObj.put("response", responseArray);

            Log.d("JSON",  responseObj.toString());

//
//            int server_port = 8008;
//            DatagramSocket s = new DatagramSocket();
//            InetAddress local = InetAddress.getByName("104.131.111.158");
//            int msg_length = responseObj.toString().getBytes().length;
//            byte[] message = responseObj.toString().getBytes();
//            DatagramPacket p = new DatagramPacket(message, msg_length, local,
//                    server_port);
//            s.send(p);
//            System.out.println(new String (message));
        } catch (Exception e) {
            System.out.println("Exception");
        }

        buildingList = new ArrayList<Building>();

        //temp
        Building addBuild = new Building("Bascom Hall", 100, 250, "Bascom Hall is one of the oldest and most famous buildings on campus. It is located at 500 Lincoln Drive, on top of Bascom Hill. It was originally built in 1857 with numerous additions and subtractions to follow.  ");


        //while loop to add all buildings to list
        buildingList.add(addBuild);

       //send buildingList to BuildingInfo Activity

        BuildingInfoActivity.updateList(buildingList, passedActivity);



    }




    public String getLocationString () {
        return myLocation;
    }



    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}