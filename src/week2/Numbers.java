package week2;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Numbers {
    public static void main(String[] args) {
        LinkedList<Double> nums = new LinkedList<>();

        for (String s : args) {
            if (!nums.contains(Double.parseDouble(s))) {
                nums.add(Double.parseDouble(s));
            }
        }

        Collections.sort(nums);
        System.out.println("In numerical order:");

        for (Double d: nums) {
            System.out.print(d + " ");
        }

        Collections.shuffle(nums);
        System.out.println("\n" + "In shuffled order:");

        for (Double d: nums) {
            System.out.print(d + " ");
        }

        Collections.sort(nums);
        Collections.reverse(nums);
        System.out.println("\n" + "In reverse order");

        for (Double d : nums) {
            System.out.print(d + " ");
        }
    }
}
