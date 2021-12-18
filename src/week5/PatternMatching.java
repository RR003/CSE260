package week5;

import java.util.Scanner;

public class PatternMatching {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("enter a string ");
        String s1 = input.nextLine();
        System.out.println("enter a string ");
        String s2 = input.nextLine();

        boolean found = false;
        int match = 0;

        for (int i = 0; i < s1.length() && !found; i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                match = i;
                for (int j = 1; j < s2.length(); j++) {
                    if (s2.charAt(j) == s1.charAt(i + 1) && j == s2.length() - 1) found = true;
                    else if (s2.charAt(j) != s1.charAt(i + 1)) break;
                }
            }
        }

        if (found) System.out.println("result match at index: " + match);
        else System.out.println("no match");
    }
}
