
/**
 * Write a description of class Cargo here.
 *
 * @author (your name)
 * @version 11/30/18
 */
public class Cargo implements Comparable<Cargo>
{
    int weight;
    String destination;
    int ID;
    public Cargo(int w,String d, int i){
        weight = w;
        destination = d;
        ID = i;
    }
    
    @Override
    public String toString(){
        return(destination + "(" + ID + "): " + weight);
    }
    
    public int compareTo(Cargo c){
        return weight-c.weight;
    }
}
