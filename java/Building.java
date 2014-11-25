package amyslawson.com.virtualcampustour;

/**
 * Created by Amy on 11/17/2014.
 */
public class Building {

    //variables
    String buildingName;
    double minDeg;
    double maxDeg;
    String buildingInfo;


    public Building (String name, double minDeg, double maxDeg, String info) {
        buildingName = name;
        this.minDeg = minDeg;
        this.maxDeg = maxDeg;
        buildingInfo = info;
    }

    public String getName () {
        return buildingName;
    }

    public double getMinDeg (){
        return minDeg;
    }

    public double getMaxDeg (){
        return maxDeg;
    }

    public String getBuildingInfo () {
        return buildingInfo;
    }
}
