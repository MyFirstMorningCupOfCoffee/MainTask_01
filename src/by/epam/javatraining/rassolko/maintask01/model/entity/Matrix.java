package by.epam.javatraining.rassolko.maintask01.model.entity;

public class Matrix 
{
    double[][] storage;
    
    int rowQuantity;
    int columnQuantity;
    
    /**
     * Constructor that creates empty Matrix of given size
     * @param a - heights of the Matrix (rows quantity)
     * @param b - width of the Matrix (columns quantity) */
    Matrix(int a, int b)
    {
        int rowQuantity    = a > 0 ? a : 0;
        int columnQuantity = b > 0 ? b : 0;
        
        storage = new double[rowQuantity][columnQuantity];
        
        this.rowQuantity = rowQuantity;
        this.columnQuantity = columnQuantity;
        
    }
    
    /**
     * Constructor that creates empty Matrix that guaranteed contains all values
     * from the array sent in parameters. In case if that array has second level 
     * arrays with various lengths, extra cells in Matrix will be filled with "0".
     * If given array is complitely broken - matrix will have 0*0 size
     * @param arr - array that needs to be used as Matrix source */
    Matrix(double[][] arr)
    {
        int rowQuantity = 0;
        int columnQuantity = 0;
        
        if(arr.length > 0)
        {
            rowQuantity = arr.length;
            
            for(double[] column : arr)
            {
                columnQuantity = column.length > columnQuantity 
                               ? column.length : columnQuantity;
            }   
        }
        
        storage = new double[rowQuantity][columnQuantity];
        
        this.rowQuantity = rowQuantity;
        this.columnQuantity = columnQuantity;
        
        for(int row = 0; row < arr.length; row++)
        {
            for(int column = 0; column < arr[row].length; column++)
            {
                storage[row][column] = arr[row][column];
            }
        } 
    }
    
    /**
     * Can be used to create copy of already existing Matrix object
     * @param source Matrix to be copied */
    Matrix(Matrix source)
    {
        columnQuantity = source.columnQuantity;
        rowQuantity = source.rowQuantity;
        storage = new double[rowQuantity][columnQuantity];
        
        for(int row = 0; row < rowQuantity; row++)
        {
            for(int column = 0; column < columnQuantity; column++)
            {
                this.storage[row][column] = source.storage[row][column];
            }
        }
    }
    
    
    /**
     * @param row current Matrix row index starting from 0
     * @param column current Matrix column index starting from 0
     * @return value from cell by given coordinates */
    public double get(int row, int column)
    {
        if(row < rowQuantity && column < columnQuantity)
        {
            return storage[row][column];
        }
        
        throw new IndexOutOfBoundsException();
    }
    
    /**
     * @return quantity of rows of the Matrix */
    public int rows()
    {
        return rowQuantity;
    }
    
    /**
     * @return quantity of columns of the Matrix */
    public int columns()
    {
        return columnQuantity;
    }
    
    /** 
     * Set given value at given coordinates cell.
     * @param row row index of current Matrix, starting from 0
     * @param column column indexof current Matrix, starting from 0
     * @param value value that need to be placed by given coordinates
     * @return true if given coordinates fit local storage and the value was set, 
     * false if not */
    public boolean set(int row, int column, double value)
    {
        if(row >= 0 && row < rowQuantity)
        {
            if(column >= 0 && column < columnQuantity)
            {
                storage[row][column] = value;
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Works the very same way as similar constructor - except creating new object. 
     * Creates empty Matrix that guaranteed contains all values
     * from the array sent in parameters. In case if that array has second level 
     * arrays with various lengths, extra cells in Matrix will be filled with "0".
     * If given array is complitely broken - matrix will have 0*0 size.
     * @param arr - array that needs to be used as Matrix source */
    public void set(double[][] arr)
    { 
        int rowQuantity = 0;
        int columnQuantity = 0;
        
        if(arr.length > 0)
        {
            rowQuantity = arr.length;
            
            for(double[] column : arr)
            {
                columnQuantity = column.length > columnQuantity 
                               ? column.length : columnQuantity;
            }   
        }
        
        storage = new double[rowQuantity][columnQuantity];
        
        this.rowQuantity = rowQuantity;
        this.columnQuantity = columnQuantity;
        
        for(int row = 0; row < arr.length; row++)
        {
            for(int column = 0; column < arr[row].length; column++)
            {
                storage[row][column] = arr[row][column];
            }
        } 
    }
   
    /**
     * Creates copy of Matrix in form of array. If actions upon this array need
     * to act on this Matrix - after these actions the array must be returned 
     * to Matrix using method <b>set(double[][] arr)</b>
     * @return array representation of this object */
    public double[][] toArray()
    {
        double[][] responce = new double[rowQuantity][columnQuantity];
        System.arraycopy(storage, 0, responce, 0, storage.length);
        return responce;
    }
    
    /** 
     * Transpose Matrix x*y
     * Uses separate methods for cases where x = y and where x != y
     * @return link to current Matrix object. Can be used in method chaining */
    public Matrix transpose()
    {
        if(rowQuantity == columnQuantity)
        {
            transposeSquare();
        }
        else
        {
            transposeNonSquare();
        }
        
        int tmp = rowQuantity;
        rowQuantity = columnQuantity;
        columnQuantity = tmp;
        
        return this;
    }
    
    /**
     * Transpose Matrix x*y where x == y. No memory for new inner storage object 
     * required. */
    private void transposeSquare()
    {
        double tmp;
        
        for(int i = 0; i < rowQuantity; i++)
        {
            for(int j = i; j < columnQuantity; j++)
            {
                tmp = storage[i][j];
                storage[i][j] = storage[j][i];
                storage[j][i] = tmp;
            }
        }
    }
    
    /**
     * Transpose Matrix x*y where x != y. Required additional memory for new 
     * inner storage object. */
    private void transposeNonSquare()
    {
        double[][] transpondedMatrix = new double[columnQuantity][rowQuantity];
        for(int i = 0; i < columnQuantity; i++)
        {
            for(int j = 0; j < rowQuantity; j++)
            {
                transpondedMatrix[i][j] = storage[j][i];
            }
        }
        
        storage = transpondedMatrix;
    }
    
    /**
     * @return String representation of an object */
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
