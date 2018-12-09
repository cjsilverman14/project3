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
        //Constructs the new ExperimentController object using the large set of data
        center = "A";
        roadsFile = "roads.txt";
        centerFile = "center.txt";
        warehouseFile = "warehouses.txt";
        smallData = false;
    }

    public void smallData(){
        //Changes the data to use the small set of data (used in unit tests)
        roadsFile = "roadsS.txt";
        centerFile = "centerS.txt";
        warehouseFile = "warehousesS.txt";
        smallData = true;
    }

    public void shortestPath(){
        //Runs the shortest path method on the center
        cityMap.shortestPath(center);
    }

    public void setCenterShortestPath(){
        //Sets the center distance of each city (used to make it a bit easier and faster)
        cityMap.shortestPath(center,0);
    }

    public static void main(String[] args){
        ExperimentController ec = new ExperimentController();

        ec.cargoList = ec.mapCity();//Constructs the graph
        ec.setCenterShortestPath();//Sets the centerDist of each city
        ec.cityMap.shortestPath(ec.cityMap.getVertex(ec.center));//Sets the first dist of each city
        Collections.sort(ec.cargoList);//Sorts the warehouses again (just to be sure)

        ArrayList<Truck> dispatch = new ArrayList<Truck>();//The array list of trucks that will eventually be printed
        int truckNumber=1;//Used for creating trucks
        int incompleteWarehouses = ec.cargoList.size();//Used to keep track of how long to run the loops
        int startingWarehouse = 0;//Keeps track of the closest warehouse that still needs orders
        while(incompleteWarehouses != 0){
            Truck t = new Truck(truckNumber);
            truckNumber++;
            if (truckNumber == 5) {//Used in debugging
                int r = 5;
            }

            while(ec.cargoList.get(startingWarehouse).incomingCargo.isEmpty()){//Increments the startingWarehouse if the current startingWarehouse is empty
                startingWarehouse++;
            }
            Warehouse currentWarehouse = ec.cargoList.get(startingWarehouse);
            Shipment s = new Shipment(currentWarehouse.city,500);//Creates a new shipment going to the current startingWarehouse
            ec.shortestPath();//Sets the dist values
            s.setDestination(ec.cityMap.getVertex(s.location));//Sets the shipment's variables
            s.setDistance();

            while(!t.truckReady){//Continues until the truck can not take any more cargo
                s.destination.setClosestCities(ec.cityMap.shortestPath(s.destination));//Sets the city's arrayList of every other city in order
                boolean shipmentCheck = false;
                while(!shipmentCheck){//Run until the shipment can not be added to
                    
                    if(currentWarehouse.incomingCargo.isEmpty()){//Increments the values that keep the program running
                        //warehouseNumber++;
                        currentWarehouse.location.hasWarehouse = false;
                        shipmentCheck = true;
                        if(!currentWarehouse.complete){
                            incompleteWarehouses--;
                        }
                        currentWarehouse.complete = true;
                        continue;
                    }
                    Cargo c = currentWarehouse.incomingCargo.remove(0);//Removes a cargo each iteration until a cargo can not be added to the shipment
                    if(!s.addCargo(c)){
                        currentWarehouse.incomingCargo.add(0,c);
                        shipmentCheck = true;
                    }
                }
                if(!s.shipmentCargo.isEmpty()){
                    //Occasionally an empty shipment could make it through the loop above. This makes sure that it does not
                    //get added to the truck and mess up the distances
                    t.addShipment(s);
                }
                shipmentCheck = false;
                t.truckReady = true;
                ArrayList<City> closest = s.destination.closestCities;
                for(int i = 0;i<closest.size();i++){
                    /*
                     * This loop and the if statements look for a warehouse that has a shipment that could fit into the truck. It will stop and break
                     * the loop when it finds the closest city that has a viable order. otherwise, it will skip right to the next city
                     */
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
            }
            t.distanceTraveled += ec.cityMap.getVertex(ec.center).dist;//Once it reaches the end, the distance from the center is added to the truck's total distance
            dispatch.add(t);
        }
        int totalDist = 0;
        try{
            PrintWriter output = new PrintWriter("output.txt");
            for(Truck t : dispatch){

                ArrayList<String> truckLines = t.printStrings();
                for(String s : truckLines){//All of the trucks run the printStrings method which creates an array list. Each arrayList is printed to the file
                    output.write(s);
                    output.println();
                }
                output.println();
                totalDist += t.distanceTraveled;
            }
            //System.out.println("Total Distance: " + totalDist);
            output.write("Total Distance: " + totalDist);
            output.close();
            System.out.println("All data successfully printed to output.txt");
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public ArrayList<Warehouse> mapCity(){
        //Takes the input and constructs the graph
        try{
            Scanner fileInput = new Scanner(new FileReader(roadsFile));
            Scanner fileInput2 = new Scanner(new FileReader(centerFile));
            Scanner fileInput3 = new Scanner(new FileReader(warehouseFile));
            if(!smallData){
                fileInput.nextLine();//The large set of data needs to skip the first line in order to run
            }
            while(fileInput.hasNextLine()){//Takes each line and divides it up into the two cities it connects and the weight, then adds that edge and vertices
                String line = fileInput.nextLine();
                if(line.length() < 3){//This was created for unit tests as it was having a hard time reading the small input files
                    line = fileInput.nextLine();
                }
                String v1 = line.substring(0,line.indexOf(" "));
                line = line.substring(line.indexOf(" ")+1);
                String v2 = line.substring(0,line.indexOf(" "));
                line = line.substring(line.indexOf(" ")+1);
                int w = Integer.parseInt(line);
                cityMap.addEdge(v1,v2,w);
            }
            center = fileInput2.nextLine();//Sets the center
            ArrayList<Warehouse> cargoList = new ArrayList<Warehouse>();
            if(!smallData){
                fileInput3.nextLine();//The large set of data needs to skip the first line in order to run
            }
            while(fileInput3.hasNextLine()){
                //Takes each line from the warehouses file and grabs all the different orders, assigns them id numbers, then adds them 
                //to a warehouse object which is returned in an array list
                String line = fileInput3.nextLine();
                String destination = line.substring(0,line.indexOf(" "));
                cityMap.hasWarehouse(destination);
                Warehouse w = new Warehouse(destination);
                line = line.substring(line.indexOf(" ")+1);
                int id = 1;
                while(line.indexOf(" ")!=-1){//This loop runs until there are no more orders for this warehouse
                    int weight = Integer.parseInt(line.substring(0,line.indexOf(" ")));
                    Cargo c = new Cargo(weight,destination,id);
                    id++;
                    w.addCargo(c);
                    line = line.substring(line.indexOf(" ")+1);
                }
                int weight = Integer.parseInt(line);//These 3 lines are to grab the last one (after there are no more space characters left)
                Cargo c = new Cargo(weight,destination,id);
                w.addCargo(c);
                w.cargoSort();//Sorts the cargo in the warehouse (original order is stored with id number)
                cargoList.add(w);
                cityMap.setWarehouse(w);//Links the city and warehouse

            }
            Collections.sort(cargoList);//Sorts the warehouse list by distance from center
            return cargoList;
        }
        catch(Exception e){

            System.out.println(e);
            return null;
        }

    }
}
