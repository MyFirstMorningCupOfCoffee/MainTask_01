package by.epam.javatraining.rassolko.maintask01.model;

public class Vector
{
    private double[] storage;
    private int length;
    private boolean isSorted;
    
    // By default our inner storage can hold 10 elements
    Vector()
    {
        this(10);
    }
    
    Vector(int size)
    {
        storage = new double[size];
        length = 0;
        isSorted = false;
    }
    
    // Adds new element to inner storage.
    // Increases inner storage size if needed.
    // isSorted will be set to false since after we added new element we can't
    // be sure if our array is sorted (in case it was sorted before insert)
    public void add(int value)
    {
        if(length == storage.length - 1)
        {
            increaseSize();
        }
        
            storage[length] = value;
            length++;
            isSorted = false;
    }
    
    // Adds new element to storage.
    // DOES NOT increase inner storage size or change isSorted property.
    // Is used inside other "add" methods, which ones should do this job.
    // Thus we can avoid doing extra useless actions during multiple elements inserts
    private void addUnsafely(int value)
    {
        storage[length] = value;
        length++;
    }
    
    
    // Adds entire array, no need for element by element insertion
    public void addArray(int[] values)
    {
        if(length + values.length > storage.length - 1)
        {
            increaseSize(values.length);
            isSorted = false;
        }
        
        for(int val : values)
        {
            addUnsafely(val);
        }
    }
    
    // Replace inner storage with new array
    public void set(double[] data)
    {
        this.storage = data;
        this.length = data.length;
        this.isSorted = false;
    }
    
    // Add 10 new slots to inner storage
    private void increaseSize()
    {
        increaseSize(10);
    }
    
    // Add N new slots to inner storage
    private void increaseSize(int add)
    {
        double[] incrStorage = new double[storage.length + add];
        for(int i = 0; i < storage.length; i++)
        {
            incrStorage[i] = storage[i];
        }
        
        this.storage = incrStorage;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for(double val : storage)
        {
            sb.append(val);
            sb.append(", ");
        }
        
        // removes extra ", "
        if(this.length > 0)
        {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        
        sb.append("]");
                
        return sb.toString();
    }
    
    // create copy of array with occupied cells only
    // (with length = local length field)
    public double[] asArray()
    {
        double[] responce = new double[length];
        for(int i = 0; i < length; i++)
        {
            responce[i] = storage[i];
        }
        
        return responce;
    }
    
    
    public boolean isSorted()
    {
        return isSorted || scanIfSorted();
    }
    
    private boolean scanIfSorted()
    {
        // 2 and 1 items arrays are already kind of sorted
        if(length < 3)
        {
            return true;
        }
        
        // Searching for two prev differest values in our storage.
        // In case if this way we reach the end of our storage - this means that
        // all elements inside are equal -> our Vector is kind of sorted
        int prev = 0; int next = 1;
        while(storage[prev] == storage[next])
        {
            prev++;
            next++;
            if(next == length)
            {
                return true;
            }
        }
        
        // Checking if our vector is ascending or descending so far
        // (according to 2 found elements).
        // Keep checking elements. If during this procedure we find out that 
        // rules has changed and current little part of our vector becomes 
        // !isAscending - this thing is not a valid sorted vector.
        // Otherwise - if we reach the end of our storage and nothing has changed - 
        // the vector is sorted
        boolean isAscending = next > prev;
        if(isAscending)
        {
            while(storage[prev] <= storage[next] && next < length)
            {
                prev++;
                next++;
            }
        }
        else
        {
            while(storage[prev] >= storage[next] && next < length)
            {
                prev++;
                next++;
            }
        }
        
        this.isSorted = next < length;
        return this.isSorted;
    }
    
    public double getMax()
    {
        if(isSorted)
        {
            return storage[0] > storage[length - 1] ? 
                   storage[0] : storage[length];
        }

        return getMax_linear();
    }
    
    public double getMin()
    {
        if(isSorted)
        {
            return storage[0] < storage[length - 1] ? 
                   storage[0] : storage[length];
        }

        return getMin_linear();
    }

    private double getMax_linear()
    {
        double max = storage[0];
        for(int i = 1; i < length; i++)
        {
            if(storage[i] > max)
            {
                max = storage[i];
            }
        }
        
        return max;
    }
    
    private double getMin_linear()
    {
        double min = storage[0];
        for(int i = 1; i < length; i++)
        {
            if(storage[i] < min)
            {
                min = storage[i];
            }
        }
        
        return min;
    }
    
    public int search(double elem)
    {
        // there is no point in bynary search in small arrays
        // and bynary search simply doesn't work for unsorted arrays
        if(isSorted && length > 4)
        {
            return search_bynary(elem);
        }
        
        return search_linear(elem);
    }
    
    private int search_linear(double elem)
    {
        for(int i = 0; i < this.length; i++)
        {
            if(elem == storage[i])
            {
                return i;
            }
        }
        
        return -1;
    }
    
    private int search_bynary(double elem)
    {
        // in case all elements in our vector are equal
        if(storage[0] == storage[length - 1])
        {
            if(elem == storage[0])
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
        
        boolean isAscending = storage[0] < storage[length - 1];
        
        // in case the value we seek for is out of range of our sorted array
        if((isAscending && (elem < storage[0] || elem > storage[length - 1]))
                ||
          (!isAscending && (elem > storage[0] || elem < storage[length - 1])))
        {
            return -1;
        }
        
        int left = 0; 
        int right = length - 1; 
        int key = left + (right - left) / 2;
        
        // doing search until we find proper value or exhaust suitable storage elements
        while(storage[key] != elem && left != right)
        {
            left = (isAscending && storage[key] > elem) ||
                  (!isAscending && storage[key] < elem)
                    ? left : key + 1;
            
            right = (isAscending && storage[key] < elem) ||
                   (!isAscending && storage[key] > elem)
                    ? right : key - 1;
            
            key = left + (right - left) / 2;
        }
        
        if(storage[key] == elem)
        {
            return key;
        }
        else
        {
            return -1;
        }
        
    }
    
    public double calcArithmeticMean()
    {
        // in case our vector is empty
        if(length == 0)
        {
            return 0;
        }
        
        double sum = 0;
        for(double elem : storage)
        {
            sum += elem;
        }
        
        return sum / length;
    }
    
    public double calcGeometricMean()
    {
        // in case our vector is empty
        if(length == 0)
        {
            return 0;
        }
        
        double mult = 1;
        for(double elem : storage)
        {
            mult *= elem;
        }
        
        return Math.pow(mult, 1.0 / length);
    }
    
    public Vector reverse()
    {
        int mirror;
        double tmp;
        int limit = length / 2;
        for(int i = 0; i < limit; i++)
        {
            mirror = length - 1 - i;
            tmp = storage[i];
            storage[i] = storage[mirror];
            storage[mirror] = tmp;
        }
        
        return this;
    }
    
    // we create "fake" elements for storage[-1] and storage[length] to be able
    // to work with border elements of our vector without overcomplicating
    // our code with additional branches for first and last elements
    public int getFirstLocalMaxPosition()
    {
        for(int i = 0; i < length; i++)
        {
            double prev = i > 0 ? storage[i - 1] : Double.MIN_VALUE;
            double next = i < length - 1 ? storage[i + 1] : Double.MIN_VALUE;
            
            if(storage[i] > prev && storage[i] > next)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    // we create "fake" elements for storage[-1] and storage[length] to be able
    // to work with border elements of our vector without overcomplicating
    // our code with additional branches for first and last elements
    public int getFirstLocalMinPosition()
    {
        for(int i = 0; i < length; i++)
        {
            double prev = i > 0 ? storage[i - 1] : Double.MAX_VALUE;
            double next = i < length - 1 ? storage[i + 1] : Double.MAX_VALUE;
            
            if(storage[i] < prev && storage[i] < next)
            {
                return i;
            }
        }
        
        return -1;
    }
}
