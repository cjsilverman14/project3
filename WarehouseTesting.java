

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WarehouseTesting.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WarehouseTesting
{
    @Test
    public void testWarehouseCompareTo(){
        City c1 = new City("Boston");
        City c2 = new City("Newton");
        
        Warehouse w1 = new Warehouse("Boston");
        Warehouse w2 = new Warehouse("Newton");
        
        c1.setWarehouse(w1);
        c2.setWarehouse(w2);
    
        w1.compareTo(w2);
        
    }
}
