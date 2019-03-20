package by.epam.javatraining.rassolko.maintask01.model.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayWorkerTest
{

    @Test
    public void testSubarray()
    {
        double[] arr = new double[] {144, 18, 115, 897, 31, 22, 44, 555, 7};
        double[] subarray = ArraysWorker.subarray(arr, 2, 6);
        double[] expected = new double[] {115, 897, 31, 22};
        
        assertArrayEquals(expected, subarray, 0);
    }

    @Test
    public void testSortAsc()
    {
        double[] arr = new double[] {144, 18, 115, 897, 31, 22, 44, 555, 7};
        double[] expected = new double[] {7, 18, 22, 31, 44, 115, 144, 555, 897};
        ArraysWorker.sort(arr, ArraysWorker.Order.Ascending);
        assertArrayEquals(expected, arr, 0);
    }
    
    @Test
    public void testSortDesc()
    {
        double[] arr = new double[] {144, 18, 115, 897, 31, 22, 44, 555, 7};
        double[] expected = new double[] {897, 555, 144, 115, 44, 31, 22, 18, 7};
        ArraysWorker.sort(arr, ArraysWorker.Order.Descending);
        assertArrayEquals(expected, arr, 0);
    }

    
}
