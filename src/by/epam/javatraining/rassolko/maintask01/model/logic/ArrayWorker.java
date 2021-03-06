package by.epam.javatraining.rassolko.maintask01.model.logic;

public class ArraysWorker 
{
    private static final SortType DEFAULT_SORT_TYPE = SortType.MERGE_SORT;
    
    public static enum Order 
        { ASCENDING, DESCENDING };
    public static enum SortType 
        { QUICK_SORT, INSERTION_SORT, BUBBLE_SORT, SELECTION_SORT, MERGE_SORT };
    
        
    
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
        sort(array, Order.ASCENDING);
    }
    
    /** 
     * Sort method #2: sort whole array, sort order can be picked.
     * @param array array to be sorted
     * @param order Order enum member, represents sort order */
    public static void sort(double[] array, Order order)
    {
        sort(array, 0, array.length, order, DEFAULT_SORT_TYPE);
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
        sort(array, from, length, Order.ASCENDING, DEFAULT_SORT_TYPE);
    }
    
    /** 
     * Sort method #5: sort part of given array (set by parameters), 
     * using default sort type.
     * @param array array to be sorted
     * @param from sort in range from ... index
     * @param length length of subarray that must be sorted
     * @param order Order enum member, represents sort order */
    public static void sort(double[] array, int from, int length, Order order)
    {
        sort(array, from, length, order, DEFAULT_SORT_TYPE);
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
            case BUBBLE_SORT:
            {
                bubbleSort(array, from, length, order);
                break;
            }
            case INSERTION_SORT:
            {
                insertionSort(array, from, length, order);
                break;
            }
            case MERGE_SORT:
            {
                mergeSort(array, from, length, order);
                break;
            }
            case SELECTION_SORT:
            {
                selectionSort(array, from, length, order);
                break;
            }
            case QUICK_SORT:
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
        if(order == Order.DESCENDING)
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
        if(order == Order.DESCENDING)
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
        if(order == Order.DESCENDING)
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
        int min;
        
        for(int i = from; i < length; i++)
        {
            min = i;
            
            for(int j = i; j < length; j++)
            {
                if(array[j] < array[min])
                {
                    min = j;
                }
            }
            
            tmp = array[i];
            array[i] = array[min];
            array[min] = tmp;

        }
    }
    
    private static void sortSelectionDesc(double[] array, int from, int length)
    {
        double tmp;
        int max;
        
        for(int i = from; i < length; i++)
        {
            max = i;
            
            for(int j = i; j < length; j++)
            {
                if(array[j] > array[max])
                {
                    max = j;
                }
            }
            
            tmp = array[i];
            array[i] = array[max];
            array[max] = tmp;
        }
    }
    
    // -- merge sort -- //
    private static void mergeSort(double[] array, int from, int length, Order order)
    {
        if(order == Order.DESCENDING)
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
            double[] part2 = subarray(array, from + length / 2, length);
            
            System.out.print("initial array: ");
            System.out.println(Arrays.toString(subarray(array, from, length)));
            System.out.print("1st array: ");
            System.out.println(Arrays.toString(subarray(array, from, length / 2)));
            System.out.print("2nd array: ");
            System.out.println(Arrays.toString(subarray(array, from + length / 2, length)));
            System.out.println("*: " + (length - length / 2));
            
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
        if(order == Order.DESCENDING)
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
        throw new NotImplementedException();
    }
    
    private static void sortQuickDesc(double[] array, int from, int length)
    {
        throw new NotImplementedException();
    }
