

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class UnitTesting.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CargoTesting
{
    @Test
    //Cargo Class & Methods
    public void testCargoCompareTo(){
        Cargo c1 = new Cargo(100, "Houston", 1);
        Cargo c2 = new Cargo(200, "Austin", 2);
        int theAns = c1.compareTo(c2);
        assert(theAns<0);
    }
}
