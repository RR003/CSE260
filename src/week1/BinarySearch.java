package week1;

public class BinarySearch {
    public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (key.compareTo(list[mid]) < 0) {
                high = mid - 1;
            }else if (key.equals(list[mid])) {
                return mid;
            }else {
                low = mid + 1;
            }
        }

        return -low - 1;
    }

    public static void main(String[] args) {
        Integer[] array = {1,3,4,5,6,7};
        int index = binarySearch(array, 3);
        System.out.println(index);
    }
}
