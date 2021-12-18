package week5;

public class MaximumIncreasinglyOrderedSubsequence {
    public static String getIncreasingSubsequence(String str, int start) {
        String subseq = "" + str.charAt(start);

        for (int i = start + 1; i < str.length(); i++) {
            if (subseq.charAt(subseq.length() - 1) < str.charAt(i)) {
                subseq += str.charAt(i);
            }
        }

        return subseq;
    }

    public static String checkAllSubsequences(String string) {
        String maxSubseq = "";

        for (int i = 0; i < string.length(); i++) {
            String thisSubseq = getIncreasingSubsequence(string, i);
            if (thisSubseq.length() > maxSubseq.length()) {
                maxSubseq = thisSubseq;
            }
        }
        return maxSubseq;
    }

    public static void main(String[] args) {
        String s = checkAllSubsequences("Welcome");
        System.out.println(s);
    }
}
