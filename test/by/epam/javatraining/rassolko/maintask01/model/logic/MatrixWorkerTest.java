package package by.epam.javatraining.rassolko.maintask01.model.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixWorkerTest
{

    @Test
    public void testGetMin()
    {
        Matrix mt = new Matrix(5, 5);
        mt.set(
        new double[][]
        {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 110, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20}
        });
        
        double expected = 1.0;
        assertEquals(expected, MatrixWorker.getMin(mt), 0);
    }

    @Test
    public void testGetMax()
    {
        Matrix mt = new Matrix(5, 5);
        mt.set(
        new double[][]
        {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 110, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20}
        });
        
        double expected = 110;
        assertEquals(expected, MatrixWorker.getMax(mt), 0);
    }

    @Test
    public void testIsSymmetricFalse()
    {
        Matrix mt = new Matrix(5, 5);
        mt.set(
        new double[][]
        {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 110, 12},
            {13, 14, 15, 16},
            {17, 18, 19, 20}
        });
        
        boolean expected = false;
        assertEquals(expected, MatrixWorker.isSymmetric(mt));
    }
    
    @Test
    public void testIsSymmetricTrue()
    {
        Matrix mt = new Matrix(5, 5);
        mt.set(
        new double[][]
        {
            {1, 2, 3, 4, 20},
            {2, 6, 7, 8, 17},
            {3, 7, 110, 12, 5},
            {4, 8, 12, 2, 20},
            {20, 17, 5, 20, 1}
        }
        );
        
        boolean expected = true;
        assertEquals(expected, MatrixWorker.isSymmetric(mt));
    }

    @Test
    public void testGetFirstLocalMinPosition()
    {
        Matrix mt = new Matrix(5, 5);
        mt.set(
        new double[][]
        {
            {9, 2, 3, 4, 20},
            {2, 6, 7, 8, 17},
            {3, 7, 110, 12, 5},
            {4, 8, 12, 2, 20},
            {20, 17, 5, 20, 1}
        }
        );
        
        int expected = 25;
        assertEquals(expected, MatrixWorker.getFirstLocalMinPosition(mt));
    }

    @Test
    public void testGetFirstLocalMaxPosition()
    {
        Matrix mt = new Matrix(5, 5);
        mt.set(
        new double[][]
        {
            {9, 2, 3, 4, 20},
            {2, 6, 7, 8, 17},
            {3, 7, 110, 12, 5},
            {4, 8, 12, 2, 20},
            {20, 17, 5, 20, 1}
        }
        );
        
        int expected = 1;
        assertEquals(expected, MatrixWorker.getFirstLocalMaxPosition(mt));
    }
    
}
