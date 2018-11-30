
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
    public Truck(int t){
        truckNumber = t;
    }
    /*@Override
    public String toString(){
        
    }*/
    public void addDist(int x){
        distanceTraveled += x;
    }
}
