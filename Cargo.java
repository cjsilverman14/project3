
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
    public Cargo(int w,String d, int i){//Creates the cargo object using relevant data
        weight = w;
        destination = d;
        ID = i;
    }
    
    @Override
    public String toString(){//Prings out the cargo object in the format shown in the result.txt file
        return(destination + "(" + ID + "): " + weight);
    }
    
    public int compareTo(Cargo c){//Compares cargo by weight
        return weight-c.weight;
    }
}
