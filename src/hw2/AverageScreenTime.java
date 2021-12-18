package hw2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AverageScreenTime {
    public static double calculate_avg_screentime(String textFile, String startDate, String endDate) throws IOException {
        File file = new File(textFile);

        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> hours = new ArrayList<>();

        String string = "";
        while ((string = br.readLine()) != null) {
            String[] array = string.split(" ");
            dates.add(array[0]);
            hours.add(array[1]);
        }

        double result = 0;
        int days = 0;

        boolean startCount = false;
        for (int i = 0; i < dates.size(); i++) {

            String date = dates.get(i).substring(0, dates.get(i).length() - 1);
            if (startCount) {
                result += Double.parseDouble(hours.get(i));
                days++;
                if (date.equals(endDate)) {
                    break;
                }
            }
            if (date.equals(startDate)) {
                startCount = true;
                result += Double.parseDouble(hours.get(i));
                days++;
            }
        }
        return result / days;
    }

    public static void main(String[] args) throws IOException {
        double d = calculate_avg_screentime("/Users/rahul/CSE260/src/screen.txt", "9/6/2021", "9/10/2021");
        System.out.println(d);
    }
}
