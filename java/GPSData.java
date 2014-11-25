package amyslawson.com.virtualcampustour;

/* Created by Amy on 10/26/2014.
 */


import android.util.Log;
import java.io.Serializable;


public class GPSData implements Serializable  {

    double latitude;
    double longitude;
    long currTime;

    public GPSData(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        currTime = System.currentTimeMillis();
        Log.e("DATA INFO", "\nTime: " + currTime + "\nLatitude: " + latitude + "\nLongitude: " +
                longitude);
    }

//    public String getDataString () {
//        Log.e("STRING TO SEND", currTime + " " + latitude + " " + longitude);
//        return (currTime + " " + latitude + " " + longitude);
//    }

    public double getLatitude () {return latitude;}

    public double getLongitude () {return longitude;}

    public long getCurrTime () {return currTime;}
}
