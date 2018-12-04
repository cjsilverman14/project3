import java.util.*;
import java.io.*;
/**
 * Write a description of class ExperimentController here.
 *
 * @author (your name)
 * @version 11/30/18
 */
public class ExperimentController
{
    Graph cityMap = new Graph();
    String center;
    ArrayList<Warehouse> cargoList;
    public ExperimentController(){
        center = "A";
    }
    public void shortestPath(String city){
        cityMap.shortestPath(center);
    }
    public void setCenterShortestPath(){
        cityMap.shortestPath(center,0);
    }
    public static void main(String[] args){
        ExperimentController ec = new ExperimentController();
        
        ec.cargoList = ec.mapCity();
        ec.setCenterShortestPath();
        Collections.sort(ec.cargoList);
        /**
         * 
         * CHANGE WAREHOUSE NUMBER TO FIND THE WAREHOUSE USING BINARY SEARCH
         */

        ArrayList<Truck> dispatch = new ArrayList<Truck>();
        int truckNumber=1;
        int incompleteWarehouses = ec.cargoList.size();
        while(incompleteWarehouses != 0){
            Truck t = new Truck(truckNumber);
            truckNumber++;
            if (truckNumber == 8) {
                int r = 5;
            }
            Shipment s = new Shipment(ec.cargoList.get(0).city,500);
            int warehouseNumber =0;
            while(!t.truckReady){
                boolean shipmentCheck = false;
                s.setDestination(ec.cityMap.getVertex(s.location));
                
                s.destination.setClosestCities(ec.cityMap.shortestPath(s.destination));
                int limit = ec.cargoList.size();
                while(!shipmentCheck && warehouseNumber <limit){
                    //This needs to be changed not only to access the first warehouse
                    //In the list
                    if(ec.cargoList.get(warehouseNumber).incomingCargo.isEmpty()){
                        Warehouse w = ec.cargoList.get(warehouseNumber);
                        //warehouseNumber++;
                        System.out.println(w);
                        w.location.hasWarehouse = false;
                        shipmentCheck = true;
                        if(!w.complete){
                            incompleteWarehouses--;
                        }
                        w.complete = true;
                        continue;
                    }
                    Cargo c = ec.cargoList.get(warehouseNumber).incomingCargo.remove(0);
                    if(!s.addCargo(c)){
                        ec.cargoList.get(warehouseNumber).incomingCargo.add(0,c);
                        shipmentCheck = true;
                    }
                }
                if(!s.shipmentCargo.isEmpty()){
                    t.addShipment(s);
                }
                shipmentCheck = false;
                t.truckReady = true;
                ArrayList<City> closest = s.destination.closestCities;
                for(int i = warehouseNumber+1;i<closest.size();i++){
                    if(closest.get(i).hasWarehouse){
                        if(!closest.get(i).cityWarehouse.incomingCargo.isEmpty() && closest.get(i).cityWarehouse.incomingCargo.get(0).weight <= 500-t.weight){
                            t.truckReady = false;
                            s = new Shipment(closest.get(i).name,500-t.weight);
                            warehouseNumber = i;
                            break;
                        }
                        else{
                            
                        }
                    }
                    else{
                        closest.remove(i);
                        i--;
                    }
                }
                
            }
            t.returnTrip();
            dispatch.add(t);
            System.out.println("Truck sent");
        }
        for(Truck t : dispatch){
            System.out.println(t);
        }
    }
    public ArrayList<Warehouse> mapCity(){
        try{
            Scanner fileInput = new Scanner(new FileReader("roads.txt"));
            Scanner fileInput2 = new Scanner(new FileReader("center.txt"));
            Scanner fileInput3 = new Scanner(new FileReader("warehouses.txt"));
            while(fileInput.hasNextLine()){
                String line = fileInput.nextLine();
                String v1 = line.substring(0,line.indexOf(" "));
                line = line.substring(line.indexOf(" ")+1);
                String v2 = line.substring(0,line.indexOf(" "));
                line = line.substring(line.indexOf(" ")+1);
                int w = Integer.parseInt(line);
                cityMap.addEdge(v1,v2,w);
            }
            center = fileInput2.nextLine();
            ArrayList<Warehouse> cargoList = new ArrayList<Warehouse>();
            while(fileInput3.hasNextLine()){
                String line = fileInput3.nextLine();
                String destination = line.substring(0,line.indexOf(" "));
                cityMap.hasWarehouse(destination);
                Warehouse w = new Warehouse(destination);
                line = line.substring(line.indexOf(" ")+1);
                int id = 1;
                while(line.indexOf(" ")!=-1){
                    int weight = Integer.parseInt(line.substring(0,line.indexOf(" ")));
                    Cargo c = new Cargo(weight,destination,id);
                    id++;
                    w.addCargo(c);
                    line = line.substring(line.indexOf(" ")+1);
                }
                int weight = Integer.parseInt(line);
                Cargo c = new Cargo(weight,destination,id);
                w.addCargo(c);
                w.cargoSort();
                cargoList.add(w);
                cityMap.setWarehouse(w);
                
            }
            Collections.sort(cargoList);
            return cargoList;
        }
        catch(Exception e){
            
            System.out.println(e);
            return null;
        }
    
    }
}
