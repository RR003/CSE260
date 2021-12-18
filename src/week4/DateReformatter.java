package week4;

import java.util.HashMap;

public class DateReformatter {
    public static String reformat_date(String date) {
        HashMap<String, String> months = new HashMap<>();

        months.put("1", "JAN");
        months.put("2", "FEB");
        months.put("3", "MAR");
        months.put("4", "APR");
        months.put("5", "MAY");
        months.put("6", "JUN");
        months.put("7", "JUL");
        months.put("8", "AUG");
        months.put("9", "SEP");
        months.put("10", "OCT");
        months.put("11", "NOV");
        months.put("12", "DEC");

        String[] format = date.split("/");
        String reformat = "";

        reformat = reformat + format[1] + "-";
        reformat = reformat + months.get(format[0]) + "-";
        reformat = reformat + format[2].substring(2);

        return reformat;
    }

    public static void main(String[] args) {
        System.out.println(reformat_date("5/17/2022"));
        System.out.println(reformat_date("12/31/2030"));
        System.out.println(reformat_date("9/8/2023"));
        System.out.println(reformat_date("8/5/2023"));
    }
}
