import java.util.*;
/**
 * Write a description of class City here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class City implements Comparable<City>
{
    String name;
    List<Road> nbs = new ArrayList<Road>();
    int dist = Integer.MAX_VALUE;
    int centerDist = Integer.MAX_VALUE;
    boolean visited = false;
    boolean hasWarehouse = false;
    boolean shortestSet = true;
    Warehouse cityWarehouse;
    ArrayList<City> closestCities = new ArrayList<City>();
    City prev;
    public City(String n){
        name = n;
    }
    
    @Override
    public int compareTo(City c){
        if(dist - c.dist!=0){
            return dist-c.dist;
        }
        else{
            return name.compareTo(c.name);
        }
    }
    
    public void setWarehouse(Warehouse w){
        cityWarehouse = w;
        w.location = this;
    }
    
    public void setClosestCities(ArrayList<City> cityList){
        closestCities = cityList;
    }
}
