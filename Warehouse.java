import java.util.*;
/**
 * Write a description of class Warehouse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Warehouse
{
    String city;
    ArrayList<Cargo> incomingCargo = new ArrayList<Cargo>();
    public Warehouse(String c){
        city = c;
    }
    public void addCargo(Cargo c){
        incomingCargo.add(c);
    }
}
