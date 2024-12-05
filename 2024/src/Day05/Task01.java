package Day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2024/src/Day05/Input.txt");
        Scanner scanner = new Scanner(file);
        List<String> rules = new ArrayList<>();
        List<String> orders = new ArrayList<>();
        boolean readRules = true;
        while (scanner.hasNextLine()) {
            while (readRules) {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    readRules = false;
                    break;
                }
                rules.add(line);
            }

            String line = scanner.nextLine();
            orders.add(line);
        }


        int result = 0;
        for (String order : orders) {
            boolean valid = true;
            List<String> entries = new ArrayList<>(List.of(order.split(",")));
            for (String rule : rules) {
                int orderOne = entries.indexOf(rule.split("\\|")[0]);
                int orderTwo = entries.indexOf(rule.split("\\|")[1]);

                if (orderTwo > -1 && orderOne > orderTwo) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                result += Integer.parseInt(entries.get(entries.size() / 2));
            }
        }
        System.out.println(result);
    }
}
