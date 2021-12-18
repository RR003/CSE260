package week8;

public class DyckWord {
    private final String word;

    public DyckWord(String word) {
        if (isDyckWord(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a valid Dyck word.", word));
    }

    private static boolean isDyckWord(String word) {
        int counter = 0;
        if (word == null)
            return false;
        //for (char c : word.toCharArray()) {
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (Operator.isOperator(c))
                continue;
            try {
                Operator op = Operator.of(c);
                if (op == Operator.LEFT_PARENTHESIS)
                    counter++;
                else if (op == Operator.RIGHT_PARENTHESIS) {
                    if (counter <= 0) return false;
                    else counter--;
                }
            }
            catch (IllegalArgumentException err){
                return false;
            }
        }

        return counter == 0;
    }


    public String getWord() {
        return word;
    }

    public static void main(String[] args) {
        System.out.println(isDyckWord("(12)"));
    }
}

enum Operator {

    MULTIPLICATION('*', 1),
    DIVISION('/', 1),
    ADDITION('+', 2),
    SUBTRACTION('-', 2),
    LEFT_PARENTHESIS('(', 3),
    RIGHT_PARENTHESIS(')', 3);

    private final char symbol;
    private final int  rank;

    Operator(char c, int rank) {
        this.symbol = c;
        this.rank = rank;
    }

    public char getSymbol() { return symbol; }

    public int getRank() { return rank; }

    public static boolean isOperator(char c) {
        return c == ADDITION.symbol || c == SUBTRACTION.symbol || c == MULTIPLICATION.symbol || c == DIVISION.symbol;
    }

    public static boolean isOperator(String c) {
        return isOperator(c.charAt(0));
    }

    public static Operator of(char c) {
        if (c == LEFT_PARENTHESIS.symbol)
            return LEFT_PARENTHESIS;
        if (c == RIGHT_PARENTHESIS.symbol)
            return RIGHT_PARENTHESIS;
        if (c == MULTIPLICATION.symbol)
            return MULTIPLICATION;
        if (c == DIVISION.symbol)
            return DIVISION;
        if (c == ADDITION.symbol)
            return ADDITION;
        if (c == SUBTRACTION.symbol)
            return SUBTRACTION;
        throw new IllegalArgumentException(String.format("%c is not a valid character for an operator.", c));
    }

    public static Operator of(String c) {
        return Operator.of(c.charAt(0));
    }
}
