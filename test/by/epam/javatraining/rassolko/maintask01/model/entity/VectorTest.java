package by.epam.javatraining.rassolko.maintask01.model.entity;

import org.junit.Test;
import static org.junit.Assert.*;


public class VectorTest
{

    @Test
    public void testAdd()
    {
        double expected = 153.2;
        Vector v = new Vector( new double[] {155, 411, 814, 555} );
        v.add(expected);
        assertEquals(expected, v.get(4), 0);
    }
    
    @Test
    public void testAddArray()
    {
        double[] addMe = new double[] {255.0, 844};
        Vector v = new Vector( new double[] {155, 411, 814, 555} );
        v.addArray(addMe);
        assertArrayEquals(v.asArray(), new double[] {155, 411, 814, 555, 255.0, 844}, 0); 
    }
    
    @Test
    public void testSet()
    {
        Vector v = new Vector( new double[] {44, 411, 814, 555, 255.0, 844} );
        v.set(255, 0);
        assertArrayEquals(v.asArray(), new double[] {255, 411, 814, 555, 255.0, 844}, 0);
    }
    
    @Test
    public void testGetElementPosition()
    {
        Vector v = new Vector( new double[] {44, 411, 814, 555, 255.0, 844} );
        int pos = v.getElementPosition(555);
        assertEquals(3, pos);
    }
    
}
