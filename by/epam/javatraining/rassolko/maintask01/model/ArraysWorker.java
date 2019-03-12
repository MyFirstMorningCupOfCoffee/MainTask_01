package by.epam.javatraining.rassolko.maintask01.model.util;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ArraysWorker 
{
    public static enum Order 
        { Ascending, Descending };
    public static enum SortType 
        { QuickSort, InsertionSort, BubbleSort, SelectionSort, MergeSort };
        
    
    // returns part of array
    public static double[] subarray(double[] array, int from, int to)
    {
        double[] responce = new double[to - from];
        for(int i = 0, j = from; j < to; j++, i++)
        {
            responce[i] = array[j];
        }
        
        return responce;
    }
    
    // #1: simpliest method, will sort whole array, ascending, 
    // using default sort type (currently - selection sort) 
    public static void sort(double[] array)
    {
        sort(array, Order.Ascending);
    }
    
    // #2: method #1 + sort order can be picked 
    public static void sort(double[] array, Order order)
    {
        sort(array, 0, array.length, order, SortType.InsertionSort);
    }
    
    // #3: method #2 + sort method can be picked
    public static void sort(double[] array, Order order, SortType sortType)
    {
        sort(array, 0, array.length, order, sortType);
    }
    
    // #4: will sort part of given array (set by parameters), ascending, 
    // using default sort type (currently - selection sort) 
    public static void sort(double[] array, int from, int length)
    {
        sort(array, from, length, Order.Ascending, SortType.SelectionSort);
    }
    
    // #5: method #4 + sort order can be picked 
    public static void sort(double[] array, int from, int length, Order order)
    {
        sort(array, from, length, order, SortType.SelectionSort);
    }
    
    // #6: method #5 + sort method can be picked
    // every other sort methods eventually call this method
    public static void sort(double[] array, int from, int length, Order order, SortType sortType)
    {
        switch(sortType)
        {
            case BubbleSort:
            {
                sort_bubble(array, from, length, order);
                break;
            }
            case InsertionSort:
            {
                sort_insertion(array, from, length, order);
                break;
            }
            case MergeSort:
            {
                sort_merge(array, from, length, order);
                break;
            }
            case SelectionSort:
            {
                sort_selection(array, from, length, order);
                break;
            }
            case QuickSort:
            {
                sort_quick(array, from, length, order);
                break;
            }
            default:
            {
                sort_quick(array, from, length, order);
                break;
            }
        }
    }
    
    
    ////// HIDDEN MACHINERY //////
    
    // -- bubble sort -- //
    private static void sort_bubble(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortDesc_bubble(array, from, length);
        }
        else
        {
            sortAsc_bubble(array, from, length);
        }
    }
    
    private static void sortAsc_bubble(double[] array, int from, int length)
    {
        double tmp;
        
        for(int i = length - 1; i > from; i--)
        {
            for(int j = from; j < i; j++)
            {
                if(array[j] > array[j + 1])        
                {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
    
    private static void sortDesc_bubble(double[] array, int from, int length)
    {
        double tmp;
        
        for(int i = length - 1; i > from; i--)
        {
            for(int j = from; j < i; j++)
            {
                if(array[j] < array[j + 1])        
                {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
    
    // -- insertion sort -- //
    private static void sort_insertion(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortDesc_insertion(array, from, length);
        }
        else
        {
            sortAsc_insertion(array, from, length);
        }
    }
    
    private static void sortAsc_insertion(double[] array, int from, int length)
    {
        double tmp;
        
        for(int i = from + 1; i < length; i++)
        {
            int j = i;
            tmp = array[j];
            
            while(j > 0 && tmp < array[j - 1])
            {
                array[j] = array[j - 1];
                j--;
            }
            
            array[j] = tmp;
        }
    }
    
    private static void sortDesc_insertion(double[] array, int from, int length)
    {
        double tmp;
        
        for(int i = from + 1; i < length; i++)
        {
            int j = i;
            tmp = array[j];
            
            while(j > 0 && tmp > array[j - 1])
            {
                array[j] = array[j - 1];
                j--;
            }
            
            array[j] = tmp;
        }
    }
    
    // -- selecton sort -- //
        private static void sort_selection(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortDesc_selection(array, from, length);
        }
        else
        {
            sortAsc_selection(array, from, length);
        }
    }
    
    private static void sortAsc_selection(double[] array, int from, int length)
    {
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
            array[i] = array[min];
            array[min] = tmp;
            
            tmp = array[length - 1 - i];
            array[length - 1 - i] = array[max];
            array[max] = tmp;
        }
    }
    
    private static void sortDesc_selection(double[] array, int from, int length)
    {
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
            array[i] = array[max];
            array[max] = tmp;
            
            tmp = array[length - 1 - i];
            array[length - 1 - i] = array[min];
            array[min] = tmp;
        }
    }
    
    // -- merge sort -- //
    private static void sort_merge(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortDesc_merge(array, from, length);
        }
        else
        {
            sortAsc_merge(array, from, length);
        }
    }
    
    private static void sortAsc_merge(double[] array, int from, int length)
    {
        if(array.length > 2)
        {
            double[] part1 = subarray(array, from, length / 2);
            double[] part2 = subarray(array, length / 2, length);
            
            sortAsc_merge(part1, 0, part1.length);
            sortAsc_merge(part2, 0, part2.length);

            
            for(int i = 0, j = 0, k = 0; i < array.length; i++)
            {
                if(j == part1.length)
                {
                    while(k < part2.length)
                    {
                        array[i] = part2[k];
                        i++;
                        k++;
                    }
                }
                else if(k == part2.length)
                {
                    while(j < part1.length)
                    {
                        array[i] = part1[j];
                        i++;
                        j++;
                    }
                }
                else
                {
                    if(part1[j] <= part2[k])
                    {
                        array[i] = part1[j];
                        j++;
                    }
                    else
                    {
                        array[i] = part2[k];
                        k++;
                    }
                }
            }
        }
    }
    
    private static void sortDesc_merge(double[] array, int from, int length)
    {
        if(array.length > 2)
        {
            double[] part1 = subarray(array, from, length / 2);
            double[] part2 = subarray(array, length / 2, length);
            
            sortAsc_merge(part1, 0, part1.length);
            sortAsc_merge(part2, 0, part2.length);
            
            for(int i = 0, j = 0, k = 0; i < array.length; i++)
            {
                if(j == part1.length)
                {
                    while(k < part2.length)
                    {
                        array[i] = part2[k];
                        i++;
                        k++;
                    }
                }
                else if(k == part2.length)
                {
                    while(j < part1.length)
                    {
                        array[i] = part1[j];
                        i++;
                        j++;
                    }
                }
                else
                {
                    if(part1[j] > part2[k])
                    {
                        array[i] = part1[j];
                        j++;
                    }
                    else
                    {
                        array[i] = part2[k];
                        k++;
                    }
                }
            }
        }
    }
    
    // -- quick sort -- //
    private static void sort_quick(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortDesc_quick(array, from, length);
        }
        else
        {
            sortAsc_quick(array, from, length);
        }
    }
    
    private static void sortAsc_quick(double[] array, int from, int length)
    {
        throw new NotImplementedException();
    }
    
    private static void sortDesc_quick(double[] array, int from, int length)
    {
        throw new NotImplementedException();
    }
    
    
    ////// ANCILLARY METHODS //////
    
    // empty so far
}
