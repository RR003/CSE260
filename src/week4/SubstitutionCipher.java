package week4;

import java.util.*;

public class SubstitutionCipher {

    public static String encode(String message, HashMap<Character, Character> substitutionMap) {
        String encoded = "";
        for (int i = 0; i < message.length(); i++) {
            encoded = encoded + substitutionMap.get(message.charAt(i));
        }
        return encoded;
    }

    public static String decode(String message, HashMap<Character, Character> substitutionMap) {
        HashMap<Character, Character> reverseMap = new HashMap<>();
        for (Character c : substitutionMap.keySet()) {
            reverseMap.put(substitutionMap.get(c), c);
        }

        String decoded = "";
        for (int i = 0; i < message.length(); i++) {
            decoded = decoded + reverseMap.get(message.charAt(i));
        }
        return decoded;
    }

    public static void main(String[] args) {
        String message = "Locked out of Blackboard";

        ArrayList<Character> original = new ArrayList<>();
        ArrayList<Character> origCopy = new ArrayList<>();
        ArrayList<Character> shuffled = new ArrayList<>();

        for (int i = 0; i < message.length(); i++) {
            if (!original.contains(message.charAt(i))) {
                original.add(message.charAt(i));
                origCopy.add(message.charAt(i));
            }
        }

        // shuffle an array
        Random rand = new Random();
        while (!origCopy.isEmpty()) {
            int randInt = rand.nextInt(origCopy.size());
            shuffled.add(origCopy.remove(randInt));
        }

        HashMap<Character, Character> substituitionMap = new HashMap<>();
        for (int i = 0; i < original.size(); i++) {
            substituitionMap.put(original.get(i), shuffled.get(i));
        }

        String encoded = encode(message, substituitionMap);
        String decoded = decode(message, substituitionMap);
        System.out.println(encoded);
        System.out.println(decoded);
    }
}
