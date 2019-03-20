package by.epam.javatraining.rassolko.maintask01.model.logic;

public class ArrayWorker 
{
    public static enum Order 
        { Ascending, Descending };
    public static enum SortType 
        { QuickSort, InsertionSort, BubbleSort, SelectionSort, MergeSort };
    
    /** 
     * returns part of array
     * @param array source array
     * @param from copy from ... index
     * @param to copy to ... index
     * @return new array containing part of source array according 
     * to given parameters */
    public static double[] subarray(double[] array, int from, int to)
    {
        double[] responce = new double[to - from];
        for(int i = 0, j = from; j < to; j++, i++)
        {
            responce[i] = array[j];
        }
        
        return responce;
    }
    
    /** 
     * Sort method #1: simpliest method, will sort whole array, ascending, 
     * using default sort type.
     * @param array array to be sorted */
    public static void sort(double[] array)
    {
        sort(array, Order.Ascending);
    }
    
    /** 
     * Sort method #2: sort whole array, sort order can be picked.
     * @param array array to be sorted
     * @param order Order enum member, represents sort order */
    public static void sort(double[] array, Order order)
    {
        sort(array, 0, array.length, order, SortType.BubbleSort);
    }
    
    /** 
     * Sort method #3: sort whole array, sort order can be picked, 
     * sort type can be picked.
     * @param array array to be sorted
     * @param order Order enum member, represents sort order
     * @param sortType SortType enum member, represents sort type */
    public static void sort(double[] array, Order order, SortType sortType)
    {
        sort(array, 0, array.length, order, sortType);
    }
    
    /** 
     * Sort method #4: sort part of given array (set by parameters), ascending, 
     * using default sort type.
     * @param array array to be sorted
     * @param from sort in range from ... index
     * @param length length of subarray that must be sorted */
    public static void sort(double[] array, int from, int length)
    {
        sort(array, from, length, Order.Ascending, SortType.BubbleSort);
    }
    
    /** 
     * Sort method #5: sort part of given array (set by parameters), 
     * using default sort type (selection sort).
     * @param array array to be sorted
     * @param from sort in range from ... index
     * @param length length of subarray that must be sorted
     * @param order Order enum member, represents sort order */
    public static void sort(double[] array, int from, int length, Order order)
    {
        sort(array, from, length, order, SortType.BubbleSort);
    }
    
    /** 
     * Sort method #6: sort part of given array.
     * @param array array to be sorted
     * @param from sort in range from ... index
     * @param length length of subarray that must be sorted
     * @param order Order enum member, represents sort order
     * @param sortType SortType enum member, represents sort type */
    // every other sort method eventually call this method
    public static void sort(double[] array, int from, int length, Order order, SortType sortType)
    {
        switch(sortType)
        {
            case BubbleSort:
            {
                bubbleSort(array, from, length, order);
                break;
            }
            case InsertionSort:
            {
                insertionSort(array, from, length, order);
                break;
            }
            case MergeSort:
            {
                mergeSort(array, from, length, order);
                break;
            }
            case SelectionSort:
            {
                selectionSort(array, from, length, order);
                break;
            }
            case QuickSort:
            {
                quickSort(array, from, length, order);
                break;
            }
            default:
            {
                quickSort(array, from, length, order);
                break;
            }
        }
    }
    
    
    ////// HIDDEN MACHINERY //////
    
    // -- bubble sort -- //
    private static void bubbleSort(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortBubbleDesc(array, from, length);
        }
        else
        {
            sortBubbleAsc(array, from, length);
        }
    }
    
    private static void sortBubbleAsc(double[] array, int from, int length)
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
    
    private static void sortBubbleDesc(double[] array, int from, int length)
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
    private static void insertionSort(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortInsertionDesc(array, from, length);
        }
        else
        {
            sortInsertionAsc(array, from, length);
        }
    }
    
    private static void sortInsertionAsc(double[] array, int from, int length)
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
    
    private static void sortInsertionDesc(double[] array, int from, int length)
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
        private static void selectionSort(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortSelectionDesc(array, from, length);
        }
        else
        {
            sortSelectionAsc(array, from, length);
        }
    }
    
    private static void sortSelectionAsc(double[] array, int from, int length)
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
    
    private static void sortSelectionDesc(double[] array, int from, int length)
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
    private static void mergeSort(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortMergeDesc(array, from, length);
        }
        else
        {
            sortMergeAsc(array, from, length);
        }
    }
    
    private static void sortMergeAsc(double[] array, int from, int length)
    {
        if(array.length > 2)
        {
            double[] part1 = subarray(array, from, length / 2);
            double[] part2 = subarray(array, length / 2, length);
            
            sortMergeAsc(part1, 0, part1.length);
            sortMergeAsc(part2, 0, part2.length);

            
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
    
    private static void sortMergeDesc(double[] array, int from, int length)
    {
        if(array.length > 2)
        {
            double[] part1 = subarray(array, from, length / 2);
            double[] part2 = subarray(array, length / 2, length);
            
            sortMergeAsc(part1, 0, part1.length);
            sortMergeAsc(part2, 0, part2.length);
            
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
    private static void quickSort(double[] array, int from, int length, Order order)
    {
        if(order == Order.Descending)
        {
            sortQuickDesc(array, from, length);
        }
        else
        {
            sortQuickAsc(array, from, length);
        }
    }
    
    private static void sortQuickAsc(double[] array, int from, int length)
    {
        throw new sun.reflect.generics.reflectiveObjects.NotImplementedException();
    }
    
    private static void sortQuickDesc(double[] array, int from, int length)
    {
        throw new sun.reflect.generics.reflectiveObjects.NotImplementedException();
    }
}
