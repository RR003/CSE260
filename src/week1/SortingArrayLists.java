package week1;

import java.util.ArrayList;

public class SortingArrayLists {
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        E currentMin;
        int currentMinIndex;

        for (int i = 0; i < list.size() - 1; i++ ) {
            currentMin = list.get(i);
            currentMinIndex = i;

             for (int j = i + 1; j < list.size(); j++) {
                 if (currentMin.compareTo(list.get(j)) > 0) {
                     currentMin = list.get(j);
                     currentMinIndex = j;
                 }
             }

             if (currentMinIndex != i) {
                 list.set(currentMinIndex, list.get(i));
                 list.set(i, currentMin);
             }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(5);
        list.add(1);
        list.add(3);
        sort(list);
        System.out.println(list);
    }
}
