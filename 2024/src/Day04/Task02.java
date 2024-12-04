package Day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task02 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2024/src/Day04/Input.txt");
        Scanner scanner = new Scanner(file);
        List<String> commonA = new ArrayList<>();
        List<List<String>> field = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            field.add(new ArrayList<>(List.of(line.split(""))));
        }

        for (int y = 0; y < field.size(); y++) {
            for (int x = 0; x < field.get(y).size(); x++) {
                if (field.get(y).get(x).equalsIgnoreCase("M")) {

                    if (checkVerticalLT(field, x, y)) {
                        commonA.add((x - 1) + ";" + (y - 1));
                    }
                    if (checkVerticalLB(field, x, y)) {
                        commonA.add((x - 1) + ";" + (y + 1));
                    }
                    if (checkVerticalRT(field, x, y)) {
                        commonA.add((x + 1) + ";" + (y - 1));
                    }
                    if (checkVerticalRB(field, x, y)) {
                        commonA.add((x + 1) + ";" + (y + 1));
                    }

                }
            }
        }

        int result = 0;
        Set<String> entries = new HashSet<>(commonA);
        for (String entry : entries) {
            if (Collections.frequency(commonA, entry) == 2) {
                result++;
            }
        }
        System.out.println(result);
    }

    static boolean checkVerticalRB(List<List<String>> field, int x, int y) {
        return field.size() > y + 1 && field.get(y + 1).size() > x + 1 && field.get(y + 1).get(x + 1).equalsIgnoreCase("A") && field.size() > y + 2 && field.get(y + 2).size() > x + 2 && field.get(y + 2).get(x + 2).equalsIgnoreCase("S");
    }

    static boolean checkVerticalRT(List<List<String>> field, int x, int y) {
        return y >= 2 && field.get(y - 1).size() > x + 1 && field.get(y - 1).get(x + 1).equalsIgnoreCase("A") && field.get(y - 2).size() > x + 2 && field.get(y - 2).get(x + 2).equalsIgnoreCase("S");
    }

    static boolean checkVerticalLT(List<List<String>> field, int x, int y) {
        return y >= 2 && x >= 2 && field.get(y - 1).get(x - 1).equalsIgnoreCase("A") && field.get(y - 2).get(x - 2).equalsIgnoreCase("S");
    }

    static boolean checkVerticalLB(List<List<String>> field, int x, int y) {
        return x >= 2 && field.size() > y + 1 && field.get(y + 1).get(x - 1).equalsIgnoreCase("A") && field.size() > y + 2 && field.get(y + 2).get(x - 2).equalsIgnoreCase("S");
    }
}
