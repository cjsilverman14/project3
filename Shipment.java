import java.util.*;
/**
 * Write a description of class Shipment here.
 *
 * @author (your name)
 * @version 11/30/18
 */
public class Shipment
{
    City destination;
    ArrayList<Cargo> shipmentCargo = new ArrayList<Cargo>();
    int weight;
    int distance;
    public Shipment(City d){
        destination = d;
        weight = 0;
    }
    @Override
    public String toString(){
        String cargoLine = "";
        for(int i = 0;i<shipmentCargo.size();i++){
            cargoLine+= shipmentCargo.get(i).toString();
            if(i!=shipmentCargo.size()-1){
                cargoLine+=", ";
            }
        }
        return("Deliver to warehouse " + destination.name + "total weight: " + weight + "([" + cargoLine + "]) dist: ");
    }
    public boolean addCargo(Cargo c){
        if(weight + c.weight > 500){
            return false;
        }
        shipmentCargo.add(c);
        return true;
    }
    public void setDistance(){
        distance = destination.dist;
    }
    public int getReturnDist(){
        return destination.centerDist;
    }
}
