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
    int loggedDistance = 0;
    boolean truckReady = false;
    int weight = 0;
    ArrayList<Shipment> shipList = new ArrayList<Shipment>();
    public Truck(int t){
        truckNumber = t;
    }

    public ArrayList<String> printStrings(){
        String shipmentLines = "";
        ArrayList<String> output = new ArrayList<String>();
        output.add("Truck " + truckNumber + ":");
        for(Shipment s : shipList){
            output.add(s.toString());
            distanceTraveled += s.distance;
        }
        if(loggedDistance ==0){
            loggedDistance = distanceTraveled;
        }
        output.add("Distance Traveled: " + loggedDistance);
        return output;

    }

    /*public void addDist(int x){
    distanceTraveled += x;
    }*/
    public void addShipment(Shipment s){
        shipList.add(s);
        weight+=s.weight;
    }

    public void returnTrip(){
        distanceTraveled+= shipList.get(shipList.size()-1).getReturnDist();
    }
    //Test test test
}
