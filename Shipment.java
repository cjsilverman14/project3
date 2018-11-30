import java.util.*;
/**
 * Write a description of class Shipment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shipment
{
    String destination;
    ArrayList<Cargo> shipmentCargo = new ArrayList<Cargo>();
    int weight;
    public Shipment(String d){
        destination = d;
        weight = 0;
    }
    public boolean addCargo(Cargo c){
        if(weight + c.weight > 500){
            return false;
        }
        shipmentCargo.add(c);
        return true;
    }
}
