

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GraphTesting.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GraphTesting
{
    /**
     * Default constructor for test class GraphTesting
     */
    public GraphTesting()
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
    public void fillCityTest(){//Tests the creation of a graph (using the small data set)
        ExperimentController ec = new ExperimentController();
        ec.smallData();
        ec.mapCity();
        ec.cityMap.shortestPath(ec.cityMap.getVertex(ec.center));
        assertEquals(ec.cityMap.vertexMap.size(),6);
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
