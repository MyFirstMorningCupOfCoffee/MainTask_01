package by.epam.javatraining.rassolko.maintask01.model.logic;

import org.junit.Test;
import static org.junit.Assert.*;


public class VectorWorkerTest
{

    @Test
    public void testIsSortedFalse()
    {
        Vector vector = new Vector( new double[] {44.0, 951.7, 30.0, 80.2, 40.3} );
        boolean expected = false;
        
        assertEquals(expected, VectorWorker.isSorted(vector));
    }
    
    @Test
    public void testIsSortedTrue()
    {
        Vector vector = new Vector( new double[] {44.0, 95.7, 911, 1054, 5000} );
        boolean expected = true;
        
        assertEquals(expected, VectorWorker.isSorted(vector));
    }

    @Test
    public void testGetMax()
    {
        Vector vector = new Vector( new double[] {44.0, 95.7, 911, 1054, -5000} );
        double expected = 1054;
        
        assertEquals(expected, VectorWorker.getMax(vector), 0);
    }

    @Test
    public void testGetMin()
    {
        Vector vector = new Vector( new double[] {44.0, 95.7, 911, 1054, -5000} );
        double expected = -5000;
        
        assertEquals(expected, VectorWorker.getMin(vector), 0);
    }

    @Test
    public void testCalcArithmeticMean()
    {
        Vector vector = new Vector( new double[] {44.0, 95.7, 911, 1054, -5000} );
        double expected = -579.06;
        
        assertEquals(expected, VectorWorker.calcArithmeticMean(vector), 0.001);
    }

    @Test
    public void testCalcGeometricMean()
    {
        Vector vector = new Vector( new double[] {44.0, 9.7, 91, 154, 50} );
        double expected = 49.562283;
        
        assertEquals(expected, VectorWorker.calcGeometricMean(vector), 0.001);
    }

    @Test
    public void testGetFirstLocalMaxPosition()
    {
        Vector vector = new Vector( new double[] {44.0, 95.7, 911, 1054, -5000} );
        double expected = 3;
        
        assertEquals(expected, VectorWorker.getFirstLocalMaxPosition(vector), 0);
    }

    @Test
    public void testGetFirstLocalMinPosition()
    {
        Vector vector = new Vector( new double[] {44.0, 95.7, 911, 1054, -5000} );
        double expected = 0;
        
        assertEquals(expected, VectorWorker.getFirstLocalMinPosition(vector), 0);
    }

    @Test
    public void testReverse()
    {
        Vector vector = new Vector( new double[] {44.0, 95.7, 911, 1054, -5000} );
        VectorWorker.reverse(vector);
        double[] expected = new double[] {-5000, 1054, 911, 95.7, 44.0};
        
        assertArrayEquals(expected, vector.asArray(), 0);
    }
    
}
