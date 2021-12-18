package week8;

import java.util.*;

public class BinPacking {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("enter the number of objects: ");
        int numObjects = input.nextInt();
        input.nextLine();

        ArrayList<Integer> objects = new ArrayList<>();
        System.out.println("enter the weights of the objects: ");
        String[] weights = input.nextLine().split(" ");

        for (int i = 0; i < numObjects; i++) {
            objects.add(Integer.parseInt(weights[i]));
        }

        System.out.println("With first fit: ");
        binPackingFirstFit((ArrayList<Integer>) objects.clone());
        System.out.println();

        System.out.println("With smallest first: ");
        binPackingSmallestFirst((ArrayList<Integer>) objects.clone());
        System.out.println();

        System.out.println("With largest first: ");
        binPackingLargestFirst((ArrayList<Integer>) objects.clone());
        System.out.println();

        System.out.println("Optimal: ");
        binPackingOptimal((ArrayList<Integer>) objects.clone());
        System.out.println();
    }

    public static void binPackingFirstFit(ArrayList<Integer> objects) {
        int numContainers = 1;
        while (!objects.isEmpty()) {
            System.out.println("Container " + numContainers + " contains object with weight " + getBinFirstFit(objects, 10));
            numContainers++;
        }
    }

    public static String getBinFirstFit(ArrayList<Integer> objects, int space) {
        String listOfWeights = "";

        for (int i = 0; i < objects.size(); i++) {
            int current = objects.get(i);
            if (current <= space) {
                listOfWeights += (current + "  ");
                objects.remove(i);
                return listOfWeights + getBinFirstFit(objects, space - current);
            }
        }

        return listOfWeights;
    }

    public static void binPackingSmallestFirst(ArrayList<Integer> objects) {
        Collections.sort(objects);

        int numContainers = 1;
        while (!objects.isEmpty()) {
            System.out.println("Container " + numContainers + " contains object with weight " + getBinSmallestFirst(objects, 10));
            numContainers++;
        }
    }

    public static String getBinSmallestFirst(ArrayList<Integer> objects, int space) {
        String listOfWeights = "";

        for (int i = 0; i < objects.size(); i++) {
            int current = objects.get(i);
            if (current <= space) {
                listOfWeights += (current + " ");
                objects.remove(i);
                return listOfWeights + getBinSmallestFirst(objects, space-current);
            }
        }

        return listOfWeights;
    }

    public static void binPackingLargestFirst(ArrayList<Integer> objects) {
        Collections.sort(objects);
        Collections.reverse(objects);

        int numContainers = 1;
        while (!objects.isEmpty()) {
            System.out.println("Container " + numContainers + " contains object with weight " + getBinLargestFirst(objects, 10));
            numContainers++;
        }
    }

    public static String getBinLargestFirst(ArrayList<Integer> objects, int space) {
        String listOfWeights = "";

        for (int i = 0; i < objects.size(); i++) {
            int current = objects.get(i);
            if (current <= space) {
                listOfWeights += (current + " ");
                objects.remove(i);
                return listOfWeights + getBinLargestFirst(objects, space - current);
            }
        }

        return listOfWeights;
    }

    public static void binPackingOptimal(ArrayList<Integer> objects) {
        int numContainers = 1;
        while (!objects.isEmpty()) {
            System.out.println("Container " + numContainers + " contains object with weight " + getBinOptimal(objects, 10));
            numContainers++;
        }
    }

    public static String getBinOptimal(ArrayList<Integer> objects, int space) {
        String listOfWeights = "";

        for (int i = 0; i < objects.size(); i++) {
            int current = objects.get(i);
            if (current <= space) {
                listOfWeights += (current + " ");
                objects.remove(i);
                return listOfWeights + continueBinOptimal(objects,space - current);
            }
        }

        return listOfWeights;
    }

    public static String continueBinOptimal(ArrayList<Integer> objects, int space) {
        String listOfWeights = "";

        for (int j = space; j>= 0; j--) {
            if (objects.contains(j)) {
                int current = objects.get(objects.indexOf(j));
                listOfWeights += (current + " ");
                objects.remove(objects.indexOf(j));
                return listOfWeights + continueBinOptimal(objects, space - current);
            }
        }

        return listOfWeights;
    }
}
