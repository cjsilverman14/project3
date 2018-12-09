import java.util.*;
/**
 * The Shipment Class
 * 
 * Stores an arrayList of cargo that is going on the same truck to the same place
 * 
 * @version 11/30/18
 */
public class Shipment
{
    City destination;
    String location;
    ArrayList<Cargo> shipmentCargo = new ArrayList<Cargo>();
    int weight;
    int maxWeight;
    int distance;
    public Shipment(String l,int max){//Creates the new shipment object with relevant data
        location = l;
        maxWeight = max;
        weight = 0;
    }
    @Override
    public String toString(){//Converts a shipment to a string in the same format as shown in the result.txt file
        String cargoLine = "";
        for(int i = 0;i<shipmentCargo.size();i++){
            cargoLine+= shipmentCargo.get(i).toString();
            if(i!=shipmentCargo.size()-1){
                cargoLine+=", ";
            }
        }
        return("Deliver to warehouse " + destination.name + " total weight: " + weight + "([" + cargoLine + "]) dist: " + distance);
    }
    public boolean addCargo(Cargo c){//Adds cargo to the shipment if there is room, or simply returns false if there is not
        if(weight + c.weight > maxWeight){
            return false;
        }
        weight+=c.weight;
        shipmentCargo.add(c);
        return true;
    }
    public void setDestination(City d){//Sets the city that this shipment is going to
        destination = d;
        //distance += d.dist;
    }
    public void setDistance(){//Sets the distance this shipment has to go based on the city. Must be called after setDestination
        distance = destination.dist;
    }
    public int getReturnDist(){//Gets the distance it takes to get back to the center
        return destination.centerDist;
    }
}
