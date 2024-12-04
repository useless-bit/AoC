package Day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2024/src/Day04/Input.txt");
        Scanner scanner = new Scanner(file);
        int result = 0;
        List<List<String>> field = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            field.add(new ArrayList<>(List.of(line.split(""))));
        }

        for (int y = 0; y < field.size(); y++) {
            for (int x = 0; x < field.get(y).size(); x++) {
                if (field.get(y).get(x).equalsIgnoreCase("X")) {

                    if (checkForward(field, x, y)) {
                        result++;
                    }
                    if (checkBackward(field, x, y)) {
                        result++;
                    }
                    if (checkUp(field, x, y)) {
                        result++;
                    }
                    if (checkDown(field, x, y)) {
                        result++;
                    }
                    if (checkVerticalLT(field, x, y)) {
                        result++;
                    }
                    if (checkVerticalLB(field, x, y)) {
                        result++;
                    }
                    if (checkVerticalRT(field, x, y)) {
                        result++;
                    }
                    if (checkVerticalRB(field, x, y)) {
                        result++;
                    }

                }
            }
        }

        System.out.println(result);
    }

    static boolean checkForward(List<List<String>> field, int x, int y) {
        return field.get(y).size() > x + 1 && field.get(y).get(x + 1).equalsIgnoreCase("M") && field.get(y).size() > x + 2 && field.get(y).get(x + 2).equalsIgnoreCase("A") && field.get(y).size() > x + 3 && field.get(y).get(x + 3).equalsIgnoreCase("S");
    }

    static boolean checkBackward(List<List<String>> field, int x, int y) {
        return x >= 3 && field.get(y).get(x - 1).equalsIgnoreCase("M") && field.get(y).get(x - 2).equalsIgnoreCase("A") && field.get(y).get(x - 3).equalsIgnoreCase("S");
    }

    static boolean checkVerticalRB(List<List<String>> field, int x, int y) {
        return field.size() > y + 1 && field.get(y + 1).size() > x + 1 && field.get(y + 1).get(x + 1).equalsIgnoreCase("M") && field.size() > y + 2 && field.get(y + 2).size() > x + 2 && field.get(y + 2).get(x + 2).equalsIgnoreCase("A") && field.size() > y + 3 && field.get(y + 3).size() > x + 3 && field.get(y + 3).get(x + 3).equalsIgnoreCase("S");
    }

    static boolean checkVerticalRT(List<List<String>> field, int x, int y) {
        return y >= 3 && field.get(y - 1).size() > x + 1 && field.get(y - 1).get(x + 1).equalsIgnoreCase("M") && field.get(y - 2).size() > x + 2 && field.get(y - 2).get(x + 2).equalsIgnoreCase("A") && field.get(y - 3).size() > x + 3 && field.get(y - 3).get(x + 3).equalsIgnoreCase("S");
    }

    static boolean checkVerticalLT(List<List<String>> field, int x, int y) {
        return y >= 3 && x >= 3 && field.get(y - 1).get(x - 1).equalsIgnoreCase("M") && field.get(y - 2).get(x - 2).equalsIgnoreCase("A") && field.get(y - 3).get(x - 3).equalsIgnoreCase("S");
    }

    static boolean checkVerticalLB(List<List<String>> field, int x, int y) {
        return x >= 3 && field.size() > y + 1 && field.get(y + 1).get(x - 1).equalsIgnoreCase("M") && field.size() > y + 2 && field.get(y + 2).get(x - 2).equalsIgnoreCase("A") && field.size() > y + 3 && field.get(y + 3).get(x - 3).equalsIgnoreCase("S");
    }

    static boolean checkUp(List<List<String>> field, int x, int y) {
        return y >= 3 && field.get(y - 1).get(x).equalsIgnoreCase("M") && field.get(y - 2).get(x).equalsIgnoreCase("A") && field.get(y - 3).get(x).equalsIgnoreCase("S");
    }

    static boolean checkDown(List<List<String>> field, int x, int y) {
        return field.size() > y + 3 && field.get(y + 1).get(x).equalsIgnoreCase("M") && field.get(y + 2).get(x).equalsIgnoreCase("A") && field.get(y + 3).get(x).equalsIgnoreCase("S");
    }
}
