package by.epam.javatraining.rassolko.maintask01.model.logic;

public class ArraysWorker 
{
    public static enum Order {ASCENDING, DESCENDING};
    
    
    public static void sort(double[] array)
    {
        sort(array, Order.ASCENDING);
    }
    
    public static void sort(double[] array, Order order)
    {
        sort(array, 0, array.length, order);
    }
    
    
    public static void sort(double[] array, int from, int length)
    {
        sort(array, from, length, Order.ASCENDING);
    }
    
    public static void sort(double[] array, int from, int length, Order order)
    {
        sort_insertion(array, from, length, order);
    }
    
    public static void sort_bubble(double[] array, int from, int length, Order order)
    {
        boolean isAscending = order == Order.ASCENDING;
        double tmp;
        
        for(int i = length - 1; i > from; i--)
        {
            for(int j = from; j < i; j++)
            {
                if((isAscending && array[j] > array[j + 1])
                      ||
                   (!isAscending && array[j] < array[j + 1]))        
                {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
    
    public static void sort_insertion(double[] array, int from, int length, Order order)
    {
        boolean isAscending = order == Order.ASCENDING;
        double tmp;
        
        for(int i = from + 1; i < length; i++)
        {
            int j = i;
            tmp = array[j];
            
            while(j > 0 && ((isAscending && tmp < array[j - 1])
                            ||
                           (!isAscending && tmp > array[j - 1])))
            {
                array[j] = array[j - 1];
                j--;
            }
            
            array[j] = tmp;
        }
    }
    
    public static void sort_selection(double[] array, int from, int length, Order order)
    {
        boolean isAscending = order == Order.ASCENDING;
        double tmp;
        int max;
        int min;
        
        for(int i = from; i < length / 2; i++)
        {
            max = i;
            min = i;
            
            for(int j = i; j < length - i; j++)
            {
                if(array[j] > array[max])
                {
                    max = j;
                }
                if(array[j] < array[min])
                {
                    min = j;
                }
            }
            
            tmp = array[i];
            array[i] = isAscending ? array[min] : array[max];
            array[isAscending ? min : max] = tmp;
            
            tmp = array[length - 1 - i];
            array[length - 1 - i] = isAscending ? array[max] : array[min];
            array[isAscending ? max : min] = tmp;
        }
    }
    
    public static void sort_merge(double[] array, int from, int length, Order order)
    {
        throw new NotImplementedException();
    }
    
    public static void sort_quick(double[] array, int from, int length, Order order)
    {
        throw new NotImplementedException();
    }
}
