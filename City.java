import java.util.*;
/**
 * Taken from the original graph.java class
 */
public class City implements Comparable<City>
{
    String name;
    List<Road> nbs = new ArrayList<Road>();
    int dist = Integer.MAX_VALUE;
    int centerDist = Integer.MAX_VALUE;
    boolean visited = false;
    boolean hasWarehouse = false;
    boolean shortestSet = false;
    Warehouse cityWarehouse;
    ArrayList<City> closestCities = new ArrayList<City>();
    City prev;
    public City(String n){
        name = n;
    }
    
    @Override
    public int compareTo(City c){//Sorts cities by distance, then by name
        if(dist - c.dist!=0){
            return dist-c.dist;
        }
        else{
            return name.compareTo(c.name);
        }
    }
    
    public void setWarehouse(Warehouse w){//Links a city with its warehouse
        cityWarehouse = w;
        w.location = this;
        hasWarehouse = true;
    }
    
    public void setClosestCities(ArrayList<City> cityList){//Sets the closestCities arrayList with all other cities in order of distance
        closestCities = cityList;
        shortestSet = true;
    }
}
