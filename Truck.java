import java.util.*;
/**
 * The Truck class
 * 
 * Stores shipments that are going on the same truck. Uses an integer to represent the max weight that can be on a truck
 * 
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

    public ArrayList<String> printStrings(){//Converts the truck data into an ArrayList of strings in order to make it easier to print to a file
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

    public void addShipment(Shipment s){//Adds a shipment to a truck
        shipList.add(s);
        weight+=s.weight;
    }

    public void returnTrip(){//Adds the distance it takes to get back to the center
        distanceTraveled+= shipList.get(shipList.size()-1).getReturnDist();
    }
}
