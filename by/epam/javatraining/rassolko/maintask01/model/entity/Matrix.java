package by.epam.javatraining.rassolko.maintask01.model.entity;

public class Matrix 
{
    double[][] storage;
    
    Matrix(int a, int b)
    {
        // tbd: throw an exception in case if a or b < 2
        // so far we kind of don't know about exceptions
        storage = new double[a][b];
    }
    
    Matrix(double[][] arr)
    {
        storage = arr;
    }
    
    // replaces inner storage with given array
    // returns true if operation is successful
    public boolean set(double[][] arr)
    {
        if(arr.length >= 2 && arr[0].length >= 2)
        {
            int width = arr[0].length;
            for(double[] subval : arr)
            {
                if(subval.length != width)
                {
                    return false;
                }
            }
            
            storage = arr;
            return true;
        }
        
        return false;
    }
    
    // set given value at given coordinates cell
    // method returns true only in case if given coordinates fit local storage
    public boolean set(int xPos, int yPos, double value)
    {
        if(xPos >= 0 && xPos < storage.length)
        {
            if(yPos >= 0 && yPos < storage[0].length)
            {
                storage[xPos][yPos] = value;
                return true;
            }
        }
        
        return false;
    }
    
    // returns the smallest value in local storage
    public double getMin()
    {
        double min = Double.MAX_VALUE;
        
        for(double[] row : storage)
        {
            for(double element : row)
            {
                if(min > element)
                {
                    min = element;
                }
            }
        }
        
        return min;  
    }
    
    // returns the biggest value in local storage
    public double getMax()
    {
        double max = Double.MIN_VALUE;
        
        for(double[] row : storage)
        {
            for(double element : row)
            {
                if(max < element)
                {
                    max = element;
                }
            }
        }
        
        return max;  
    }
    
    // checks if matrix is symmetric - from p.o.w. of main or secondary diagonale
    // our matrix needs to be square and it's size must be 2x2 at the very least
    // if these conditions aren't fullfilled - false will be returned
    public boolean isSymmetric()
    {
        if(storage.length < 2 || storage.length != storage[0].length)
        {
            return false;
        }

        return isSymmetricMain() || isSymmetricSecondary();
    }
    
    // check main diagonale symmetry
    private boolean isSymmetricMain()
    {
        for(int i = 0; i < storage.length - 1; i++)
        {
            for(int j = i + 1; j < storage.length; j++)
            {
                if(storage[i][j] != storage[j][i])
                {
                    return false;
                }
            }
        }

        return true;
    }
    
    // check secondary diagonale symmetry
    private boolean isSymmetricSecondary()
    {
        for(int i = 0; i < storage.length - 1; i++)
        {
            for(int j = 0; j < storage.length - 1 - i; j++)
            {
                if( storage[i][j] 
                    != 
                    storage[storage.length - 1 - j][storage.length - 1 - i])
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // Transpose Matrix x*y
    // Uses separate methods for cases where x = y and x != y
    public Matrix transpose()
    {
        if(storage.length == storage[0].length)
        {
            transposeSquare();
        }
        else
        {
            transposeNonSquare();
        }
        
        return this;
    }
    
    private void transposeSquare()
    {
        double tmp;
        
        for(int i = 0; i < storage.length; i++)
        {
            for(int j = i; j < storage.length; j++)
            {
                tmp = storage[i][j];
                storage[i][j] = storage[j][i];
                storage[j][i] = tmp;
            }
        }
    }
    
    private void transposeNonSquare()
    {
        double[][] transpondedMatrix = new double[storage[0].length][storage.length];
        for(int i = 0; i < storage[0].length; i++)
        {
            for(int j = 0; j < storage.length; j++)
            {
                transpondedMatrix[i][j] = storage[j][i];
            }
        }
        
        storage = transpondedMatrix;
    }
    
    public int getFirstLocalMinPosition()
    {
        for(int i = 0; i < storage.length; i++)
        {
            for(int j = 0; j < storage[0].length; j++)
            {
                double[] neighbours = getNeighbours(i, j, Double.MAX_VALUE);
                
                // by default we think that curren element is local minimum
                boolean isLocalMin = true;
                for(double n : neighbours)
                {
                    if(storage[i][j] >= n)
                    {
                        isLocalMin = false;
                    }
                }
                
                if(isLocalMin)
                {
                    return i + j * storage[0].length + 1;
                }
            }
        }
        
        return -1;
    }
    
    public int getFirstLocalMaxPosition()
    {
        for(int i = 0; i < storage.length; i++)
        {
            for(int j = 0; j < storage[0].length; j++)
            {
                double[] neighbours = getNeighbours(i, j, Double.MIN_VALUE);
                
                // by default we think that curren element is local minimum
                boolean isLocalMax = true;
                for(double n : neighbours)
                {
                    if(storage[i][j] <= n)
                    {
                        isLocalMax = false;
                    }
                }
                
                if(isLocalMax)
                {
                    return i + j * storage[0].length + 1;
                }
            }
        }
        return -1;
    }
    
    // Gets us near-by element to compare to.
    // Can be used to find local min or max
    private double[] getNeighbours(int xPos, int yPos, double filler)
    {
        double[] neighbours = new double[8];
        
        int k = 0;
        for(int i = xPos - 1; i <= xPos + 1; i++)
        {
            for(int j = yPos - 1; j <= yPos + 1; j++)
            {
                if(i == xPos && j == yPos)
                {
                    continue;
                }
                
                if( (i < 0 || j < 0) || (i >= storage.length || j >= storage[0].length) )
                {
                    neighbours[k++] = filler;
                }
                else
                {
                    neighbours[k++] = storage[i][j];
                }
            }
        }
        
        // debug
        // System.out.println(storage[xPos][yPos] + " -> " + Arrays.toString(neighbours));
        return neighbours;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < storage.length; i++)
        {
            for(int j = 0; j < storage[0].length; j++)
            {
                sb.append(storage[i][j]);
                sb.append("\t");
            }
            sb.append("\n");
        }
        
        sb.replace(sb.length() - 1, sb.length(), "");
        
        return sb.toString();
    }
}
