
/**
 * Write a description of class Cargo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Cargo
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
        return(destination + "[" + ID + "] " + weight);
    }
    
    //Test Test Test
}
