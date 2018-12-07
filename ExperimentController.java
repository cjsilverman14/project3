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
    String roadsFile;
    String centerFile;
    String warehouseFile;
    boolean smallData = false;
    ArrayList<Warehouse> cargoList;
    public ExperimentController(){
        center = "A";
        roadsFile = "roads.txt";
        centerFile = "center.txt";
        warehouseFile = "warehouses.txt";
        smallData = true;
    }

    public void smallData(){
        roadsFile = "roadsS.txt";
        centerFile = "centerS.txt";
        warehouseFile = "warehousesS.txt";
    }

    public void shortestPath(){
        cityMap.shortestPath(center);
    }

    public void setCenterShortestPath(){
        cityMap.shortestPath(center,0);
    }

    public static void main(String[] args){
        ExperimentController ec = new ExperimentController();

        ec.cargoList = ec.mapCity();
        ec.setCenterShortestPath();
        ec.cityMap.shortestPath(ec.cityMap.getVertex(ec.center));
        Collections.sort(ec.cargoList);

        ArrayList<Truck> dispatch = new ArrayList<Truck>();
        int truckNumber=1;
        int incompleteWarehouses = ec.cargoList.size();
        int startingWarehouse = 0;
        while(incompleteWarehouses != 0){
            Truck t = new Truck(truckNumber);
            truckNumber++;
            if (truckNumber == 5) {
                int r = 5;
            }

            while(ec.cargoList.get(startingWarehouse).incomingCargo.isEmpty()){
                startingWarehouse++;
            }
            Warehouse currentWarehouse = ec.cargoList.get(startingWarehouse);
            Shipment s = new Shipment(currentWarehouse.city,500);
            ec.shortestPath();
            s.setDestination(ec.cityMap.getVertex(s.location));
            s.setDistance();

            while(!t.truckReady){
                s.destination.setClosestCities(ec.cityMap.shortestPath(s.destination));
                boolean shipmentCheck = false;
                while(!shipmentCheck){
                    //This needs to be changed not only to access the first warehouse
                    //In the list
                    if(currentWarehouse.incomingCargo.isEmpty()){
                        //warehouseNumber++;
                        currentWarehouse.location.hasWarehouse = false;
                        shipmentCheck = true;
                        if(!currentWarehouse.complete){
                            incompleteWarehouses--;
                        }
                        currentWarehouse.complete = true;
                        continue;
                    }
                    Cargo c = currentWarehouse.incomingCargo.remove(0);
                    if(!s.addCargo(c)){
                        currentWarehouse.incomingCargo.add(0,c);
                        shipmentCheck = true;
                    }
                }
                if(!s.shipmentCargo.isEmpty()){
                    t.addShipment(s);
                }
                shipmentCheck = false;
                t.truckReady = true;
                ArrayList<City> closest = s.destination.closestCities;
                for(int i = 0;i<closest.size();i++){
                    if(closest.get(i).hasWarehouse){
                        if(!closest.get(i).cityWarehouse.incomingCargo.isEmpty() && closest.get(i).cityWarehouse.incomingCargo.get(0).weight <= 500-t.weight){
                            t.truckReady = false;
                            City c = closest.get(i);
                            ec.cityMap.shortestPath(s.destination);
                            s = new Shipment(closest.get(i).name,500-t.weight);
                            //ec.cityMap.shortestPath(c);

                            s.setDestination(ec.cityMap.getVertex(s.location));
                            //ec.cityMap.shortestPath(s.destination);
                            s.setDistance();
                            currentWarehouse = closest.get(i).cityWarehouse;
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
                int y = 6;
            }
            t.distanceTraveled += ec.cityMap.getVertex(ec.center).dist;
            dispatch.add(t);
        }
        int totalDist = 0;
        try{
            PrintWriter output = new PrintWriter("output.txt");
            for(Truck t : dispatch){

                ArrayList<String> truckLines = t.printStrings();
                for(String s : truckLines){
                    output.write(s);
                    output.println();
                }
                output.println();
                totalDist += t.distanceTraveled;
            }
            //System.out.println("Total Distance: " + totalDist);
            output.write("Total Distance: " + totalDist);
            output.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public ArrayList<Warehouse> mapCity(){
        try{
            Scanner fileInput = new Scanner(new FileReader(roadsFile));
            Scanner fileInput2 = new Scanner(new FileReader(centerFile));
            Scanner fileInput3 = new Scanner(new FileReader(warehouseFile));
            if(!smallData){
                fileInput.nextLine();
            }
            while(fileInput.hasNextLine()){
                String line = fileInput.nextLine();
                if(line.length() < 3){
                    line = fileInput.nextLine();
                }
                String v1 = line.substring(0,line.indexOf(" "));
                line = line.substring(line.indexOf(" ")+1);
                String v2 = line.substring(0,line.indexOf(" "));
                line = line.substring(line.indexOf(" ")+1);
                int w = Integer.parseInt(line);
                cityMap.addEdge(v1,v2,w);
            }
            center = fileInput2.nextLine();
            ArrayList<Warehouse> cargoList = new ArrayList<Warehouse>();
            if(!smallData){
                fileInput3.nextLine();
            }
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
