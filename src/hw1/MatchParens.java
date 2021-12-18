package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MatchParens {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(args[0]);
        String fileName = input.nextLine();
        File f = new File(fileName);
        String string = "";
        Scanner in = new Scanner(f);

        while (in.hasNext()) string += in.nextLine() + "\n";
        in.close();

        Stack<Character> stack = new Stack<>();

        boolean result = true;
        boolean inComment = false;
        boolean inString = false;

        for (int i = 0; i < string.length(); i++) {
            char s = string.charAt(i);
            if (inComment) {
                if (s == '*' && string.charAt(i+1) == '/') {
                    inComment = false;
                    i++;
                    continue;
                }else continue;
            }else if (inString) {
                if (s == '"') {
                    inString = false;
                    continue;
                }else continue;
            }

            if (s == '/' && string.charAt(i+1) == '*' ) {
                inComment = true;
                continue;
            }
            if (s == '"') {
                inString = true;
                continue;
            }

            if (s == '(' || s == '[' || s == '{') stack.push(s);
            else if (s == ')' || s == ']' || s == '}') {
                if (stack.isEmpty()) {
                    result = false;
                    break;
                }else {
                    char prev = stack.peek();
                    if (s == ')' && prev == '(') stack.pop();
                    else if (s == ']' && prev == '[') stack.pop();
                    else if (s == '}' && prev == '{') stack.pop();
                    else {
                        result = false;
                        break;
                    }
                }
            }else if (s == '*' && string.charAt(i + 1) == '/') {
                result = false;
                break;
            }


            else continue;
        }

        if (!stack.isEmpty()) result = false;
        if (inComment || inString) result = false;

        if (result) System.out.println("valid input");
        else System.out.println("invalid input");
    }
}
