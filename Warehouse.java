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
    City location;
    ArrayList<Cargo> incomingCargo = new ArrayList<Cargo>();
    boolean complete = false;
    public Warehouse(String c){
        city = c;
    }
    public void addCargo(Cargo c){
        incomingCargo.add(c);
    }
    public void cargoSort(){
        Collections.sort(incomingCargo);
    }
    public int compareTo(Warehouse w){
        if(location.centerDist-w.location.centerDist != 0){
            return location.centerDist - w.location.centerDist;
        }
        else{
            return city.compareTo(w.city);
        }
    }
}
