import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TruckTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TruckTest
{
    /**
     * Default constructor for test class TruckTest
     */
    public TruckTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    @Test
    public void createTruck(){
        Truck t = new Truck(5);
        assertEquals(t.truckNumber,5);
    }
    
    @Test
    public void loadTruck(){
        City c = new City("A");
        c.dist = 5;
        c.centerDist = 5;
        Cargo order = new Cargo(10,"A",1);
        Shipment s = new Shipment("A",50);
        s.setDestination(c);
        s.setDistance();
        s.addCargo(order);
        Truck t = new Truck(1);
        t.addShipment(s);
        t.distanceTraveled+=5;
        t.returnTrip();
        assert(t.weight == 10 && t.distanceTraveled == 10);
    }
    
    @Test
    public void printTruck(){
        City c = new City("A");
        c.dist = 5;
        c.centerDist = 5;
        Cargo order = new Cargo(10,"A",1);
        Shipment s = new Shipment("A",50);
        s.setDestination(c);
        s.setDistance();
        s.addCargo(order);
        Truck t = new Truck(1);
        t.addShipment(s);
        t.returnTrip();
        ArrayList<String> answer = new ArrayList<String>();
        answer.add("Truck 1:");
        answer.add(s.toString());
        answer.add("Distance Traveled: 10");
        ArrayList<String> test = t.printStrings();
        boolean check = true;
        for(int i = 0;i<test.size();i++){
            System.out.println(answer.get(i));
            System.out.println(test.get(i));
            if(!test.get(i).equals(answer.get(i))){
                check = false;
            }
        }
        assert(check);
    }
    
    @Test
    public void multiShipmentTruck(){
        City c1 = new City("A");
        City c2 = new City("B");
        c1.dist = 6;
        c1.centerDist = 6;
        c2.dist = 7;
        c2.centerDist = 7;
        Cargo order1 = new Cargo(10,"A",1);
        Cargo order2 = new Cargo(20,"B",2);
        Shipment s1 = new Shipment("A",50);
        Shipment s2 = new Shipment("B",40);
        s1.setDestination(c1);
        s1.setDistance();
        s1.addCargo(order1);
        s2.setDestination(c2);
        s2.setDistance();
        s2.addCargo(order2);
        Truck t = new Truck(1);
        t.addShipment(s1);
        t.addShipment(s2);
        t.distanceTraveled+=s1.distance;
        t.distanceTraveled+=s2.distance;
        t.returnTrip();
        assertEquals(t.distanceTraveled, 20);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
