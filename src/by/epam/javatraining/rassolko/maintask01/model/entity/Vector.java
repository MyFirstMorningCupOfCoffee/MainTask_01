package by.epam.javatraining.rassolko.maintask01.model.entity;

public class Vector
{
    private double[] storage;
    private int length;
    private boolean sorted;
    
    /**
     * Creates Vector with inner storage size == default (10) */
    Vector()
    {
        this(10);
    }
    
    /**
     * Creates Vector with specified inner storage size
     * @param size size of inner Vector storage */
    Vector(int size)
    {
        storage = new double[size];
        length = 0;
        sorted = false;
    }
    
    /**
     * Creates Vector using given array as basis
     * @param array this array will be used as created Vector data */
    Vector(double[] array)
    {
        storage = array;
        length = array.length;
        sorted = false;
    }
    
    /**
     * Can be used to create copy of already existing Vector object
     * @param source Vector object to be copied */
    Vector(Vector source)
    {
        storage = new double[source.length];
        System.arraycopy(source.storage, 0, storage, 0, length);
        
        length = source.length;
        sorted = source.sorted;
    }
    
    
    /** 
     * Returns element that is situated at given position
     * @param position position number
     * @return proper element from inner Vector storage */
    public double get(int position)
    {
        if(position >= length)
        {
            throw new IndexOutOfBoundsException();
        }
        
        return storage[position];
    }
    
    /** 
     * Can be used to set new value to given position
     * @param value new value to be set
     * @param position position number */
    public void set(double value, int position)
    {
        if(position >= length)
        {
            throw new IndexOutOfBoundsException();
        }
        
        storage[position] = value;
    }
    
    /**
     * Can be used to get virtual (quantity of added elements) length of the Vector.
     * Actual length of inner storage is hidden from users.
     * @return virtual length of the Vector */
    public int length()
    {
        return this.length;
    }
    
    /**
     * Returns the state of local boolean field that shows if this Vector is marked 
     * as "Sorted". <br> 
     * <b>False</b> responce doesn't actually mean that Vector is not sorted.
     * Same thing for <b>True</b> value since this field can be set manually.<br>
     * This flag is used in search methods since bynary search requires sorted storage.
     * @return if this Vector is marked as "Sorted" */
    public boolean isSorted()
    {
        return isSorted;
    }
    
    /**
     * Mark Vector as "Sorted" (parameter == true) 
     * or "Not sorted" (parameter == false).
     * @param param new value of the flag that indicates id the Vector 
     * is marked as "Sorted" */
    public void setSorted(boolean param)
    {
        isSorted = param;
    }
    
    /** Replace inner storage with new array.
     * <b>Warning!</b>: Do not use this to work directly with inner Vector storage.
     * Vector can change inner storage object without any notification.
     * @param data array that replaces inner Vector storage 
     * @deprecated Not really usefull. Better use proper constructor instead */
    @Deprecated
    public void setStorage(double[] data)
    {
        storage = data;
        length = data.length;
        isSorted = false;
    }
    
    
    /** Adds new element to inner storage.
     * Increases inner storage size if needed.<br>
     * Vector will be marked as not sorted since after we added new element we can't
     * be sure if our array is sorted (in case it was sorted before insert).
     * @param value the value that will be added to Vector's storage's end */
    public void add(double value)
    {
        if(length == storage.length)
        {
            increaseSize();
        }
        
            storage[length] = value;
            length++;
            isSorted = false;
    }
    
    /** 
     * Adds new element to storage.<br>
     * DOES NOT increase inner storage size or change isSorted property.
     * Is used inside other "add" methods, which ones should do this job.
     * Thus we can avoid doing extra useless actions during multiple 
     * elements inserts */
    private void addUnsafely(int value)
    {
        storage[length - 1] = value;
        length++;
    }
    
    
    /** 
     * Adds entire array, no need for element by element insertion.
     * @param values the values that will be added to the Vector's storage's end. */
    public void addArray(int[] values)
    {
        if(length + values.length > storage.length)
        {
            increaseSize(values.length);
            isSorted = false;
        }
        
        for(int val : values)
        {
            addUnsafely(val);
        }
    }
    
    /** 
     * Adds default quantity (== 10) slots to inner storage */
    private void increaseSize()
    {
        increaseSize(10);
    }
    
    /** 
     * Add N new slots to inner storage.
     * @param add quantity of slots that need to be added */
    private void increaseSize(int add)
    {
        double[] incrStorage = new double[storage.length + add];
        System.arraycopy(storage, 0, incrStorage, 0, storage.length);
        
        this.storage = incrStorage;
    }
    
    /**
     * Search for set element position. Depending on if this Vector is marked as
     * "Sorted" linear or bynary search can be used. 
     * @param elem the value that needs to be found
     * @return element's position or -1 in case if proper value is missing. */
    public int getElementPosition(double elem)
    {
        // there is no point in bynary search in small arrays
        // and bynary search simply doesn't work for unsorted arrays
        if(isSorted && length > 4)
        {
            return search_bynary(elem);
        }
        
        return search_linear(elem);
    }
    
    /**
     * Linear search for not sorted Vector.
     * @param elem the element that needs to be found
     * @return element's position or -1 in case if proper value is missing. */
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
    
    /**
     * Bynary search for Vector that marked as "Sorted".
     * @param elem the element that needs to be found
     * @return element's position or -1 in case if proper value is missing. */
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
    
 
    /**
     * @return a string representation of the Vector. */
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
}




