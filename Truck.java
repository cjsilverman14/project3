import java.util.*;
/**
 * Write a description of class Truck here.
 *
 * @author (your name)
 * @version 11/30/18
 */
public class Truck
{
    int truckNumber;
    int distanceTraveled;
    ArrayList<Shipment> shipList = new ArrayList<Shipment>();
    public Truck(int t){
        truckNumber = t;
    }
    @Override
    public String toString(){
        String shipmentLines = "";
        for(Shipment s : shipList){
            shipmentLines+=s.toString();
            shipmentLines+="\n";
        }
        return("Truck " + truckNumber + ":\n" + shipmentLines);
    }
    public void addDist(int x){
        distanceTraveled += x;
    }
    public void addShipment(Shipment s){
        shipList.add(s);
    }
    public int returnTrip(){
        return shipList.get(shipList.size()-1).getReturnDist();
    }
}
