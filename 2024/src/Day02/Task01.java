package Day02;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) throws IOException {
        File file = new File("2024/src/Day02/Input.txt");
        Scanner scanner = new Scanner(file);
        int result = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<String> input = List.of(line.split("\\s+".trim()));

            boolean increasing = false;
            boolean invalid = false;
            for (int i = 0; i < input.size() - 1; i++) {
                int baseNumber = Integer.parseInt(input.get(i));
                int compareNumber = Integer.parseInt(input.get(i + 1));
                int calculation = baseNumber - compareNumber;
                if (calculation == 0 || calculation < -3 || calculation > 3) {
                    invalid = true;
                    break;
                }
                if (i == 0) {
                    increasing = calculation > 0;
                }
                if ((increasing && calculation < 0) || (!increasing && calculation > 0)) {
                    invalid = true;
                    break;
                }
            }
            if (!invalid) {
                result++;
            }
        }

        System.out.println(result);
    }
}
