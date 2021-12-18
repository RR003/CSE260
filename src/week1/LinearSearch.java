package week1;

public class LinearSearch {
    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
        for (int i = 0; i < list.length; i++) {
            if (key.compareTo(list[i]) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer[] list1 = {1,2,3,4,5};
        String[] list2 = {"Ab", "Cd", "Foo", "Science"};

        System.out.println(Max.max(list1));
        System.out.println(BinarySearch.binarySearch(list1, 2));
        System.out.println(LinearSearch.linearSearch(list1, 5));

        System.out.println(Max.max(list2));
        System.out.println(BinarySearch.binarySearch(list2, "Foo"));
        System.out.println(LinearSearch.linearSearch(list2, "Science"));
    }
}
