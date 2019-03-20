package by.epam.javatraining.rassolko.maintask01.model.entity;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest
{

    @Test
    public void testGet()
    {
        Matrix mt = new Matrix( new double[][] 
        {{98, 94, 73, 111.5},
         {115, 94, 7, 81},
         {77, 11, 5, 4},
         {8, 7, 55, 4}});
        
        double expected = 77;
        double result = mt.get(2, 0);
        
        assertEquals(expected, result, 0);
    }


    @Test
    public void testSetByCoords()
    {
        Matrix mt = new Matrix( new double[][] 
        {{98, 94, 73, 111.5},
         {115, 94, 7, 81},
         {77, 11, 5, 4},
         {8, 7, 55, 4}});
        
        double setMe = 118;
        mt.set(3, 2, setMe);
        double result = mt.get(3, 2);
        
        assertEquals(setMe, result, 0);
    }

    @Test
    public void testSetArray()
    {
        Matrix mt = new Matrix( new double[][] 
        {{98, 94, 73, 111.5},
         {115, 94, 7, 81},
         {77, 11, 5, 4},
         {8, 7, 55, 4}});
        
        mt.set( new double[][] { {1, 8}, {5, 4} });
        assertEquals(8, mt.get(0, 1), 0);
    }

    @Test
    public void testTranspose()
    {
        Matrix mt = new Matrix( new double[][] {
         {98, 94, 73, 111.5},
         {115, 94, 7, 81},
         {77, 11, 5, 4},
         {8, 7, 55, 4}});
        
        mt.transpose();
        
        double[][] expected = new double[][] {
            {98, 115, 77, 8},
            {94, 94, 11, 7},
            {73, 7, 5, 55},
            {111.5, 81, 4, 4}};
        
        assertArrayEquals(expected, mt.toArray());
    }

    
}
