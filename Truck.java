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
    boolean truckReady = false;
    int weight = 0;
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
            distanceTraveled += s.distance;
        }
        return("Truck " + truckNumber + ":\n" + shipmentLines + "Distance Traveled: " + distanceTraveled + "\n");
        
    }
    /*public void addDist(int x){
        distanceTraveled += x;
    }*/
    public void addShipment(Shipment s){
        System.out.println(s.distance);
        shipList.add(s);
        weight+=s.weight;
    }
    public void returnTrip(){
        distanceTraveled+= shipList.get(shipList.size()-1).getReturnDist();
    }
    //Test test test
}
