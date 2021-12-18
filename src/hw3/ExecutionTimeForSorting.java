package hw3;

import java.util.Arrays;

public class ExecutionTimeForSorting {
    public static void main(String[] args) {
        int[] array1 = generateArray(50000);
        int[] array2 = generateArray(100000);
        int[] array3 = generateArray(250000);
        int[] array4 = generateArray(500000);
        int[] array5 = generateArray(1000000);

        /*System.out.println(selectionSortTime(array2));
        System.out.println(selectionSortTime(array3));
        System.out.println(selectionSortTime(array4));
        System.out.println(selectionSortTime(array5));*/

        System.out.println("Array size SelectionSort BubbleSort MergeSort QuickSort HeapSort RadixSort");
        System.out.println("50,000    " + selectionSortTime(array1) + "  " + bubbleSortTime(array1) + "   " + mergeSortTime(array1) + "   " +
                 + quickSortTime(array1) + "   " + heapSortTime(array1) + "  " + radixSortTime(array1));
        System.out.println("100,000    " + selectionSortTime(array2) + "  " + bubbleSortTime(array2) + "   " + mergeSortTime(array2) + "   " +
                quickSortTime(array2) + "   " +heapSortTime(array2) + "  " + radixSortTime(array2));
        System.out.println("250,000    " + selectionSortTime(array3) + "  " + bubbleSortTime(array3) + "   " + mergeSortTime(array3) + "   " +
                quickSortTime(array3) + "   " +heapSortTime(array3) + "  " + radixSortTime(array3));
        System.out.println("500,000    " + selectionSortTime(array4) + "  " + bubbleSortTime(array4) + "   " + mergeSortTime(array4) + "   " +
                quickSortTime(array4) + "   " +heapSortTime(array4) + "  " + radixSortTime(array4));
        System.out.println("1,000,000    " + selectionSortTime(array5) + "  " + bubbleSortTime(array5) + "   " + mergeSortTime(array5) + "   " +
                quickSortTime(array5) + "   " +heapSortTime(array5) + "  " + radixSortTime(array5));
    }
    public static int[] generateArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            int value = (int) (Math.random() * n);
            array[i] = value;
        }

        return array;
    }

    public static long selectionSortTime(int[] array) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int index=  i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    index = j;
                }
            }
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long bubbleSortTime(int[] array) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long mergeSortTime(int[] array) {
        long startTime = System.currentTimeMillis();
        mergeSort(array);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void mergeSort(int[] list) {
        if (list.length > 1) {
// Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list,0,firstHalf,0,list.length / 2);
            mergeSort(firstHalf);
// Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list,list.length / 2,secondHalf,0,
                    secondHalfLength);
            mergeSort(secondHalf);
// Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);
        }
    }
    public static void merge(int[] list1, int[] list2, int[] temp){
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp
        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2])
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }
        while (current1 < list1.length)
            temp[current3++] = list1[current1++];
        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }


    public static long quickSortTime(int[] array) {
        long startTime = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(array));
        return endTime - startTime;
    }

    public static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            System.out.println(partitionIndex);
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }

    public static long heapSortTime(int[] array) {
        long startTime = System.currentTimeMillis();
        MinHeapSort.sort(array); // from previously defined class hw3.MinHeapSort
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long radixSortTime(int[] array) {
        long startTime = System.currentTimeMillis();
        randomInitiate(array);
        for(int i=0; i<6; i++) {
            radix = i;
            bucketSort(array);
        }
        System.out.println(Arrays.toString(array));
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static void randomInitiate(int[] a) {
        for (int i = 0; i < a.length; i++)
            a[i] = (int) (Math.random() * a.length);
    }

    static int radix = 1;
    public static int getKey(int n) {
        for(int i=0; i<radix; i++)
            n = n / 10;
        return n % 10;
    }
    static int t = 10;

    public static void bucketSort(int[] list) {
        java.util.ArrayList<Integer>[] bucket =new java.util.ArrayList[t+1];
// Distribute the elements from list to buckets
        for (int i = 0; i < list.length; i++) {
// Assume element has the getKey() method
            int key = getKey(list[i]);
            if (bucket[key] == null)
                bucket[key] = new java.util.ArrayList<Integer>();
            bucket[key].add(list[i]);
        }
// Now move the elements from the buckets back to list
        int k = 0; // k is an index for list
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != null) {
                for (int j = 0; j < bucket[i].size(); j++)
                    list[k++] = (int)(bucket[i].get(j));
            }
        }
    }

}
