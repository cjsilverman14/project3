

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ShipmentTesting.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ShipmentTesting
{
    /**
     * Default constructor for test class ShipmentTesting
     */
    public ShipmentTesting()
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
    public void createShipment(){
        Shipment s = new Shipment("Town Name", 67);
        assert(s.maxWeight == 67 && s.location.equals("Town Name"));
    }
    
    @Test
    public void cityShipment(){
        City c = new City("Townsville");
        c.dist = 5;
        Shipment s = new Shipment("Townsville",67);
        s.setDestination(c);
        s.setDistance();
        assert(s.distance == 5 && s.destination.equals(c));
    }
    
    @Test
    public void loadCargo(){
        Cargo c1 = new Cargo(56,"A",1);
        Cargo c2 = new Cargo(67,"A",3);
        Cargo c3 = new Cargo(123,"A",2);
        Shipment s = new Shipment("A",200);
        assert(s.addCargo(c1) && s.addCargo(c2) && !s.addCargo(c3));
    }
    
    @Test
    public void printShipment(){
        Cargo c1 = new Cargo(56,"A",1);
        Cargo c2 = new Cargo(67,"A",2);
        Cargo c3 = new Cargo(123,"A",3);
        Shipment s = new Shipment("A",300);
        City c = new City("A");
        c.dist = 5;
        s.setDestination(c);
        s.setDistance();
        s.addCargo(c1);
        s.addCargo(c2);
        s.addCargo(c3);
        String answer = ("Deliver to warehouse A total weight: " + (56+67+123) + "([A(1): 56, A(2): 67, A(3): 123]) dist: 5");
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
