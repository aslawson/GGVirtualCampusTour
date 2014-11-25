package amyslawson.com.virtualcampustour;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Amy on 11/17/2014.
 */
public class BuildingInfoActivity extends Activity{

    static List<Building> buildingList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_info);

        //location listening
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        MyCurrentLoctionListener locationListener = new MyCurrentLoctionListener(this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        compareCompass(this);

    }


    public static void updateList (List<Building> list, Activity thisActivity) {
        buildingList = list;
        compareCompass(thisActivity);
    }

    private static void compareCompass (Activity activity) {
        //get compass direction
        Double compassDegrees = 200.0;
        Building currBuilding =  null;
        Building desiredBuilding = null;

        //compare with compass to select correct building in list
        if (buildingList != null) {
            Iterator<Building> itr = buildingList.iterator();
            boolean unfound = true;
            while (itr.hasNext() && unfound) {
                currBuilding = itr.next();
                if (currBuilding.getMinDeg() > currBuilding.getMaxDeg()) {
                    if ((compassDegrees >= 0 && compassDegrees <= currBuilding.getMaxDeg()) ||
                            (compassDegrees <= 0 && compassDegrees >= currBuilding.getMinDeg()) )
                    {
                        desiredBuilding = currBuilding;
                        unfound = false;
                    }
                }
                else if (currBuilding.getMinDeg() <= compassDegrees && currBuilding.getMaxDeg() >=
                        compassDegrees) {
                    desiredBuilding = currBuilding;
                    unfound = false;
                }
            }
        }
        Log.d("Status","Updating Display");
        updateDisplay(desiredBuilding, activity);
    }

    private static void updateDisplay (Building currBuilding, Activity activity) {
        if (currBuilding == null) {
            Log.e("Status","No Building Found");
            TextView title = (TextView) activity.findViewById(R.id.BuildingName);
            title.setText("No Match");}
        else {
            TextView title = (TextView) activity.findViewById(R.id.BuildingName);
            TextView info = (TextView) activity.findViewById(R.id.BuildingInfo);
            title.setText(currBuilding.getName());
            info.setText(currBuilding.getBuildingInfo());

        }

    }
}
