package by.epam.javatraining.rassolko.maintask01.model.logic;

import by.epam.javatraining.rassolko.maintask01.model.entity.Matrix;

public class MatrixWorker 
{
    /**
     * @param matrix The Matrix that needs to be processed
     * @return the smallest value of processed Matrix */
    public static double getMin(Matrix matrix)
    {
        double min = Double.MAX_VALUE;
        
        for(int row = 0; row < matrix.rows(); row++)
        {
            for(int column = 0; column < matrix.columns(); column++)
            {
                if(min > matrix.get(row, column))
                {
                    min = matrix.get(row, column);
                }
            }
        }
        
        return min;  
    }
    
    /**
     * @param matrix The Matrix that needs to be processed
     * @return the smallest value of processed Matrix */
    public static double getMax(Matrix matrix)
    {
        double max = Double.MIN_VALUE;
        
        for(int row = 0; row < matrix.rows(); row++)
        {
            for(int column = 0; column < matrix.columns(); column++)
            {
                if(max < matrix.get(row, column))
                {
                    max = matrix.get(row, column);
                }
            }
        }
        
        return max;  
    }
    
    public static boolean isSymmetric(Matrix matrix)
    {
        if(matrix.rows() != matrix.columns())
        {
            return false;
        }

        return isSymmetricMainDiagonale(matrix) || isSymmetricSecondaryDiagonale(matrix);
    }
    
    /**
     * Checks main diagonale symmetry */
    private static boolean isSymmetricMainDiagonale(Matrix matrix)
    {
        for(int row = 0; row < matrix.rows() - 1; row++)
        {
            for(int column = row + 1; column < matrix.columns(); column++)
            {
                if(matrix.get(row, column) != matrix.get(column, row))
                {
                    return false;
                }
            }
        }

        return true;
    }
    
    /**
     * Checks secondary diagonale symmetry */
    private static boolean isSymmetricSecondaryDiagonale(Matrix matrix)
    {
        for(int row = 0; row < matrix.rows() - 1; row++)
        {
            for(int column = row + 1; column < matrix.columns(); column++)
            {
                if( matrix.get(row, column) 
                    != 
                    matrix.get(matrix.columns() - 1 - column, matrix.rows() - 1 - row) )
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * @param matrix Matrix to be processed
     * @return position of first by order local minimum */
    public static int getFirstLocalMinPosition(Matrix matrix)
    {
        for(int row = 0; row < matrix.rows(); row++)
        {
            for(int column = 0; column < matrix.columns(); column++)
            {
                double[] neighbours = getNeighbours(matrix, row, column, Double.MAX_VALUE);
                
                // by default we think that current element is local minimum
                boolean isLocalMin = true;
                for(double n : neighbours)
                {
                    if(matrix.get(row, column) >= n)
                    {
                        isLocalMin = false;
                    }
                }
                
                if(isLocalMin)
                {
                    return row * matrix.columns() + column + 1;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * @param matrix Matrix to be processed
     * @return position of first by order local mmaximum */
    public static int getFirstLocalMaxPosition(Matrix matrix)
    {
        for(int row = 0; row < matrix.rows(); row++)
        {
            for(int column = 0; column < matrix.columns(); column++)
            {
                double[] neighbours = getNeighbours(matrix, row, column, Double.MIN_VALUE);
                //System.out.println(matrix.get(row, column) + " -> " + java.util.Arrays.toString(neighbours));
                // by default we think that curren element is local minimum
                boolean isLocalMax = true;
                for(double n : neighbours)
                {
                    if(matrix.get(row, column) <= n)
                    {
                        isLocalMax = false;
                    }
                }
                
                if(isLocalMax)
                {
                    return row * matrix.columns() + column + 1;
                }
            }
        }
        return -1;
    }
    
    /** Gets us near-by elements to compare to.
     * Used to find local minimum or maximum */
    private static double[] getNeighbours(Matrix matrix, int row, int column, double filler)
    {
        double[] neighbours = new double[8];
        
        int neighbourIndex = 0;
        for(int i = row - 1; i <= row + 1; i++)
        {
            for(int j = column - 1; j <= column + 1; j++)
            {
                if(i == row && j == column)
                {
                    continue;
                }
                
                if( (i < 0 || j < 0) || (i >= matrix.rows() || j >= matrix.columns()) )
                {
                    neighbours[neighbourIndex++] = filler;
                }
                else
                {
                    neighbours[neighbourIndex++] = matrix.get(i, j);
                }
            }
        }
        
        return neighbours;
    }
}
