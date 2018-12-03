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
    Graph cityMap = new Graph();;
    String center;
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
        ArrayList<Warehouse> cargoList = ec.mapCity();
        Collections.sort(cargoList);
        ec.setCenterShortestPath();
        int truckNumber=1;
        while(!cargoList.isEmpty()){
            Truck t = new Truck(truckNumber);
            while(!t.truckReady){
                boolean shipmentCheck = false;
                Shipment s = new Shipment(cargoList.get(0).city,500);
                s.destination.setClosestCities(ec.cityMap.shortestPath(s.destination));
                while(!shipmentCheck){
                    if(cargoList.get(0).incomingCargo.isEmpty()){
                        cargoList.remove(0);
                        shipmentCheck = true;
                        continue;
                    }
                    Cargo c = cargoList.get(0).incomingCargo.remove(0);
                    if(!s.addCargo(c)){
                        cargoList.get(0).incomingCargo.add(0,c);
                        shipmentCheck = true;
                    }
                }
                t.addShipment(s);
            }
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
                return cargoList;
            }
        }
        catch(Exception e){
            
            System.out.println(e);
            return null;
        }
        return null;
    
    }
}
