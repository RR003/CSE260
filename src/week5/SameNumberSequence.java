package week5;

import java.util.ArrayList;
import java.util.Scanner;

public class SameNumberSequence {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> seq = new ArrayList<>();

        System.out.print("Enter a series of numbers ending with zero");

        String[] array = input.nextLine().split(" ");

        for (String s : array) {
            if (Integer.parseInt(s) == 0) {
                seq.add(Integer.parseInt(s));
                break;
            }else {
                seq.add(Integer.parseInt(s));
            }
        }

        int startSeq = 0;
        int start = 0;
        int max = 0;

        for (int i = 0; i < seq.size(); i++) {
            if (i == start) continue;

            if (seq.get(i) != seq.get(i - 1)) {
                if ((i - start) > max) {
                    startSeq = start;
                    max = i - start;
                }
                start = i;
            }
        }

        System.out.print("index starts at " + startSeq + " with " + max + " values of " + seq.get(startSeq));
    }
}
