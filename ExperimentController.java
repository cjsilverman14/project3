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
        ec.mapCity();
        ec.setCenterShortestPath();
        
    }
    public void mapCity(){
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
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
