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
        for(Warehouse w : ec.cargoList){
            System.out.println(w.city);
        }
        Collections.sort(ec.cargoList);
        for(Warehouse w : ec.cargoList){
            System.out.println(w.city);
        }
        ec.setCenterShortestPath();
        ArrayList<Truck> dispatch = new ArrayList<Truck>();
        int truckNumber=1;
        while(!ec.cargoList.isEmpty()){
            Truck t = new Truck(truckNumber);
            truckNumber++;
            Shipment s = new Shipment(ec.cargoList.get(0).city,500);
            while(!t.truckReady){
                boolean shipmentCheck = false;
                s.setDestination(ec.cityMap.getVertex(s.location));
                s.destination.setClosestCities(ec.cityMap.shortestPath(s.destination));
                while(!shipmentCheck){
                    if(ec.cargoList.get(0).incomingCargo.isEmpty()){
                        ec.cargoList.remove(0);
                        shipmentCheck = true;
                        continue;
                    }
                    Cargo c = ec.cargoList.get(0).incomingCargo.remove(0);
                    if(!s.addCargo(c)){
                        ec.cargoList.get(0).incomingCargo.add(0,c);
                        shipmentCheck = true;
                    }
                }
                t.addShipment(s);
                t.truckReady = true;
                for(City c : s.destination.closestCities){
                    if(c.hasWarehouse){
                        if(c.cityWarehouse.incomingCargo.get(0).weight <= 500-t.weight){
                            t.truckReady = false;
                            s = new Shipment(c.name,500-t.weight);
                        }
                    }
                }
                
            }
            t.returnTrip();
            dispatch.add(t);
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
    public void sortWarehouses(){
        LinkedList<Warehouse> l1 = new LinkedList<Warehouse>();
        LinkedList<Warehouse> l2 = new LinkedList<Warehouse>();
        int count = 0;
        for(int i = 0;i<(cargoList.size()/2);i++){
            l1.add(cargoList.get(count));
            count++;
        }
        for(int i = 0;i<(cargoList.size()-l1.size());i++){
            l2.add(cargoList.get(count));
            count++;
        }
        cargoList = mergeSort(l1,l2);
    }
}
