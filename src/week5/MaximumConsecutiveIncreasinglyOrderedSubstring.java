package week5;

import java.util.Scanner;

public class MaximumConsecutiveIncreasinglyOrderedSubstring {
    public static String maxSubstring(String str) {
        String sub = "";
        int start = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i == start) continue;

            if (!(str.charAt(i) > str.charAt(i - 1))) {
                if ((i - start) > (sub.length())) {
                    sub = str.substring(start, i);
                }
                start = i;
            }else if (i == str.length() - 1){
                if ((i - start) > (sub.length())) {
                    sub = str.substring(start);
                }
            }
        }

        return sub;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a string");
        String in = input.nextLine();

        System.out.println("Result = " + maxSubstring(in));
    }
}
