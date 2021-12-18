package week6;

import java.util.Comparator;

public class GenericQuickSort {
    public static <E extends Comparable<E>> void quickSort(E[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] list, int first, int last) {
        if(last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public static <E extends Comparable<E>> int partition(E[] list, int first, int last) {
        E pivot = list[first];
        int low = first + 1;
        int high = last;
        while (high> low) {

            while (low <= high && list[low].compareTo(pivot) <= 0) {
                low++;
            }
            while (low <= high && list[high].compareTo(pivot) > 0) {
                high--;
            }

            if (high >low) {
                E temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high].compareTo(pivot) >= 0) {
            high--;
        }
        if (pivot.compareTo(list[high]) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }else return first;
    }
}
