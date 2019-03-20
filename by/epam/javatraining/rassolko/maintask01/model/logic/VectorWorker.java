package by.epam.javatraining.rassolko.maintask01.model.logic;

import package by.epam.javatraining.rassolko.maintask01.model.entity.Vector;

public class VectorWorker 
{
    /** 
     * create copy of array with occupied cells only
     * (with length = local length field).
     * @param v Vector object that needs to be transformed to array
     * @return  array representation of the Vector */
    public static double[] toArray(Vector v)
    {
        double[] responce = new double[v.length()];
        
        for(int i = 0; i < v.length(); i++)
        {
            responce[i] = v.get(i);
        }
        
        return responce;
    }
    
    /**
     * Checks if Vector is marked as "Sorted".<br> 
     * In case it isn't - Vector will be scanned value by value. 
     * Then the result of this check will be returned.<br>
     * If the check finds out that the Vector is <b>actually sorted</b> - 
     * it will be marked as "Sorted".
     * @param v Vector object that needs to be checked
     * @return if Vector is sorted (ascendind or descendind - doesn't matter) 
     * or not. */
    public static boolean isSorted(Vector v)
    {
        return v.isSorted() || scanIfSorted(v);
    }
    
    /**
     * Scans if Vector is sorted (ascending or descending - doesn't matter) or not.
     * @param v Vector object that needs to be checked
     * @return if Vector is sorted or not */
    private static boolean scanIfSorted(Vector v)
    {
        // 2 and 1 items arrays are already kind of sorted
        if(v.length() < 3)
        {
            return true;
        }
        
        // Searching for two prev differest values in our storage.
        // In case if this way we reach the end of our storage - this would means
        // that all elements inside are equal -> our Vector is kind of sorted
        int prev = 0; int next = 1;
        while(v.get(prev) == v.get(next))
        {
            prev++;
            next++;
            if(next == v.length())
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
            while(v.get(prev) <= v.get(next) && next < v.length())
            {
                prev++;
                next++;
            }
        }
        else
        {
            while(v.get(prev) >= v.get(next) && next < v.length())
            {
                prev++;
                next++;
            }
        }
        
        // marking vector as "Sorted" (or not) according to the scan results
        v.setSorted(next < v.length());
        
        return next < v.length();
    }
    
    /**
     * returns the maximum value that is contained by given Vector
     * @param v Vector that needs to be processed
     * @return maximal value */
    public static double getMax(Vector v)
    {
        if(v.isSorted())
        {
            return v.get(0) > v.get(v.length() - 1) ? 
                   v.get(0) : v.get(v.length() - 1);
        }

        return getMaxLinear(v);
    }
    
    /**
     * returns the minimum value that is contained by given Vector
     * @param v Vector that needs to be processed
     * @return minumal value */
    public static double getMin(Vector v)
    {
        if(v.isSorted())
        {
            return v.get(0) < v.get(v.length() - 1) ? 
                   v.get(0) : v.get(v.length() - 1);
        }

        return getMinLinear(v);
    }
    
    /**
     * returns the maximum value that is contained by given Vector. 
     * Used to search in Vectors that aren't marked as "Sorted".
     * @param v Vector that needs to be processed
     * @return maximal value */
    private static double getMaxLinear(Vector v)
    {
        double max = v.get(0);
        for(int i = 1; i < v.length(); i++)
        {
            if(v.get(i) > max)
            {
                max = v.get(i);
            }
        }
        
        return max;
    }
    
    /**
     * returns the minimal value that is contained by given Vector. 
     * Used to search in Vectors that aren't marked as "Sorted".
     * @param v Vector that needs to be processed
     * @return minimal value */
    private static double getMinLinear(Vector v)
    {
        double min = v.get(0);
        for(int i = 1; i < v.length(); i++)
        {
            if(v.get(i) < min)
            {
                min = v.get(i);
            }
        }
        
        return min;
    }
    
    /**
     * Calculated arythmetic mean of given Vector
     * @param v Vector that needs to be processed
     * @return arythmetic mean of Vector */
    public static double calcArithmeticMean(Vector v)
    {
        // in case our vector is empty
        if(v.length() == 0)
        {
            return 0;
        }
        
        double sum = 0;
        for(int i = 0; i < v.length(); i++)
        {
            sum += v.get(i);
        }
        
        return sum / v.length();
    }
    
    /**
     * Calculated geometric mean of given Vector
     * @param v Vector that needs to be processed
     * @return geometric mean of Vector */
    public static double calcGeometricMean(Vector v)
    {
        // in case our vector is empty
        if(v.length() == 0)
        {
            return 0;
        }
        
        double mult = 1;
        for(int i = 0; i < v.length(); i++)
        {
            mult *= v.get(i);
        }
        
        return Math.pow(mult, 1.0 / v.length());
    }
    
    /**
     * Find first local maximal element position of given Vector
     * @param v - Vector that needs to be processed
     * @return first local maximal element position or -1 if there isn't one. */
    public static int getFirstLocalMaxPosition(Vector v)
    {
        for(int i = 0; i < v.length(); i++)
        {
            // "Fake" elements for storage[-1] and storage[length] are created 
            // to create opportunity to work with border elements of the Vector 
            // without overcomplicating code with additional branches for first 
            // and last elements
            double prev = i > 0 ? v.get(i - 1) : Double.MIN_VALUE;
            double next = i < v.length() - 1 ? v.get(i + 1) : Double.MIN_VALUE;
            
            if(v.get(i) > prev && v.get(i) > next)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Find first local minimal element position of given Vector
     * @param v - Vector that needs to be processed
     * @return first local minimal element position or -1 if there isn't one. */
    public static int getFirstLocalMinPosition(Vector v)
    {
        for(int i = 0; i < v.length(); i++)
        {
            // "Fake" elements for storage[-1] and storage[length] are created 
            // to create opportunity to work with border elements of the Vector 
            // without overcomplicating code with additional branches for first 
            // and last elements
            double prev = i > 0 ? v.get(i - 1) : Double.MAX_VALUE;
            double next = i < v.length() - 1 ? v.get(i + 1) : Double.MAX_VALUE;
            
            if(v.get(i) < prev && v.get(i) < next)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Reverse Vector's elements order
     * @param v Vector that needs to be processed */
    public static void reverse(Vector v)
    {
        int mirror;
        double tmp;
        int limit = v.length() / 2;
        for(int i = 0; i < limit; i++)
        {
            mirror = v.length() - 1 - i;
            tmp = v.get(i);
            v.set(v.get(mirror), i);
            v.set(tmp, mirror);
        }
    }
}
