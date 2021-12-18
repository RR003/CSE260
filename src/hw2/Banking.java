package hw2;

import java.util.HashMap;

public class Banking {

    public static void transaction(HashMap<String, Integer> database, String customer, String transactionType, int transactionAmount) throws Exception {
        if (database.containsKey(customer)) {
            int balance = database.get(customer);
            if (transactionType.equals("withdraw")) {
                if (balance - transactionAmount <= 0) throw new Exception("balance not sufficient for transaction");
                if (transactionAmount <= 0) throw new Exception("transaction amount is below zero");
                else database.replace(customer, balance - transactionAmount);
            }else if (transactionType.equals("deposit")) {
                database.replace(customer, balance + transactionAmount);
            }
        }else {
            if (transactionType.equals("deposit") && transactionAmount > 0) {
                database.put(customer, transactionAmount);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Integer> dict1 = new HashMap<>();
        dict1.put("Abe", 1000);
        dict1.put("Bob", 500);
        // System.out.println(dict1);
        transaction(dict1, "Abe", "withdraw", 200);
        // System.out.println(dict1);
        transaction(dict1, "Abe", "deposit", 300);
        // System.out.println(dict1);

        // transaction(dict1, "Abe", "withdraw", -500);
        // System.out.println(dict1);

        transaction(dict1, "Cain", "deposit", 200);
        System.out.println(dict1);

    }


}
