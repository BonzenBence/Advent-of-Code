import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MainPart1 {

    public static void main(String[] args) {

        //Reads Input file int String line by line
        String filename = "C:/Users/fzesb/Documents/Uni/AOC4.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String newline =null;

            int totalPoints = 0;
            while ((line = br.readLine()) != null) {

                //Delete "Card X:" from every line of the String
                int colonIndex = line.indexOf(":");
                if (colonIndex != -1 && colonIndex + 1 < line.length()) {
                    newline = line.substring(colonIndex + 1).trim();
                }

                //Split String into Chosen and Winning Numbers
                assert newline != null;
                String[] cardChoices = newline.split("\\|");
                String[] chosenNumbers = splitAndTrim(cardChoices[0]);
                String[] randomNumbers = splitAndTrim(cardChoices[1]);


                //Converts String to Int
                int[] chosenNumbersInInt = convertStringNumbersToIntNumbers(chosenNumbers);
                int[] randomNumbersInInt = convertStringNumbersToIntNumbers(randomNumbers);

                //Counts totalPoints of all Cards
                totalPoints += compareNumbers(chosenNumbersInInt,randomNumbersInInt);
                System.out.println("Points after every Game played Sum up "+totalPoints);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    /**
     * Converts from String Array into Int Array
     * @param strings Array with Numbers
     * @return Array with numbers but as Int
     */
    public static int[] convertStringNumbersToIntNumbers(String[] strings){
        int[] numbers = new int[30];
        for (int i = 0; i < strings.length; i++) {
            numbers[i] = Integer.parseInt(strings[i]);
        }
        return numbers;
    }
    /**
     * Compares the Numbers of two Arrays
     * Compare Chosen and Winning numbers
     * @param array1 Chosen Numbers
     * @param array2 Winning Numbers
     * @return How many Points for each Match
     * 0 Match - 0 Points
     * 1 Match - 1 Point
     * 2 Match - 2 Points
     * 3 Match - 4 Points etc.
     */
    public static int compareNumbers(int[] array1, int[] array2) {
        int points = 1;
        int matches = 0;

        for (int j : array1) {
            for (int k : array2) {
                if (j == k && !(j == 0)) {
                    if (matches > 0) {
                        points = points * 2;
                    }

                    matches++;
                }
            }
        }
        System.out.println("Points made this Game "+points);
        System.out.println("The Count "+matches);
        if (matches==0){
            return 0;
        }else {
            return points;
        }
    }

    /**
     * Splits Chosen and Winning Numbers
     * @param input The whole string with both Numbers
     * @return String Array with two Parts -> Chosen Numbers and Winning Numbers
     */
    private static String[] splitAndTrim(String input) {
        String[] parts = input.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return Arrays.stream(parts)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }
}