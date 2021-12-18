package week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Letters {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enter the location of the text file: ");

        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        File f = new File(fileName);
        Scanner in = new Scanner(f);

        HashMap<Character, Integer> letters = new HashMap<>();
        for (int i = 0,j=65; i < 26; i++,j++) {
            letters.put((char)j,0);
        }

        String s = "";
        while (in.hasNext()) s += in.nextLine() + "\n";
        in.close();
        System.out.println(s);
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                char ch = Character.toUpperCase(s.charAt(i));
                letters.put(ch, letters.get(ch) + 1);
            }
        }

        System.out.println(letters.toString());
    }
}
