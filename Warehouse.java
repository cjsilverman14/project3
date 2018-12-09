import java.util.*;
/**
 * Write a description of class Warehouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Warehouse implements Comparable<Warehouse>
{
    String city;
    City location;//This is set upon creation of the warehouse object
    ArrayList<Cargo> incomingCargo = new ArrayList<Cargo>();
    boolean complete = false;
    public Warehouse(String c){//Creates the new warehouse object. The string gives it a name representing the city it is going to
        city = c;
    }
    public void addCargo(Cargo c){//Adds cargo to be delivered to the warehouse
        incomingCargo.add(c);
    }
    public void cargoSort(){//Sorts the cargo to be delivered to the warehouse
        Collections.sort(incomingCargo);
    }
    public int compareTo(Warehouse w){//A compareTo method that sorts first by distance, then by name. 
        if(location.centerDist-w.location.centerDist != 0){
            return location.centerDist - w.location.centerDist;
        }
        else{
            return city.compareTo(w.city);
        }
    }
}
