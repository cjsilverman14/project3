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
    Warehouse cityWarehouse;
    City prev;
    public City(String n){
        name = n;
    }
    
    @Override
    public int compareTo(City c){
        return dist - c.dist;
    }
    
    public void setWarehouse(Warehouse w){
        
    }
}
