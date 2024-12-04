package Day01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) throws IOException {
        File file = new File("2024/src/Day01/Input.txt");
        Scanner scanner = new Scanner(file);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> input = List.of(line.split("\\s+".trim()));
            left.add(Integer.parseInt(input.getFirst()));
            right.add(Integer.parseInt(input.getLast()));
        }

        int result = 0;
        for (int i = 0; i < left.size(); i++) {
            int leftNumber = left.get(i);
            int countRight = Collections.frequency(right, leftNumber);
            int calculation = leftNumber * countRight;
            result += calculation;
        }

        System.out.println(result);
    }
}
