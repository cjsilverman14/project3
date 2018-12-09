

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

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
    
        assert(w1.compareTo(w2)<0);
    }
    
    @Test
    public void testWarehouseAddCargo(){
        City c1 = new City("Boston");
        Warehouse w1 = new Warehouse("Boston");
        c1.setWarehouse(w1);
    
        w1.addCargo(new Cargo(10, "CJ", 1));
        w1.addCargo(new Cargo(20, "Silverman", 2));
        w1.addCargo(new Cargo(30, "Addison", 3));
        w1.addCargo(new Cargo(40, "Butka", 4));
        w1.addCargo(new Cargo(50, "Goldwait", 5));
        Object [] theAns = w1.getIncomingCargo().toArray();
        
        ArrayList<Cargo> truAns = new ArrayList<Cargo>();
        truAns.add(new Cargo(10, "CJ", 1));
        truAns.add(new Cargo(20, "Silverman", 2));
        truAns.add(new Cargo(30, "Addison", 3));
        truAns.add(new Cargo(40, "Butka", 4));
        truAns.add(new Cargo(50, "Goldwait", 5));
        Object [] trueAns = truAns.toArray();
        
        assertArrayEquals(theAns, trueAns);
    }
    
    @Test
    public void testWarehouseSortCargo(){
        City c1 = new City("Boston");
        Warehouse w1 = new Warehouse("Boston");
        c1.setWarehouse(w1);
    
        w1.addCargo(new Cargo(10, "CJ", 1));
        w1.addCargo(new Cargo(20, "Silverman", 2));
        w1.addCargo(new Cargo(30, "Addison", 3));
        w1.addCargo(new Cargo(40, "Butka", 4));
        w1.addCargo(new Cargo(50, "Goldwait", 5));
        w1.cargoSort();
        Object [] theAns = w1.getIncomingCargo().toArray();
        
        ArrayList<Cargo> ans = new ArrayList<Cargo>();
        ans.add(new Cargo(10, "CJ", 1));
        ans.add(new Cargo(20, "Silverman", 2));
        ans.add(new Cargo(30, "Addison", 3));
        ans.add(new Cargo(40, "Butka", 4));
        ans.add(new Cargo(50, "Goldwait", 5));
        Collections.sort(ans);
        Object [] truAns = ans.toArray();
        
        assertArrayEquals(theAns, truAns);
    }
}
