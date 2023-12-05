import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class MainPart2 {

    public static void main(String[] args) {

        //create List to put how many Numbers Match and how many Games will have copies, class GameCounter
        ArrayList<GameCounter> listOfGames = new ArrayList<>();

        //Reads Input file int String line by line
        String filename = "C:/Users/fzesb/Documents/Uni/AOC4.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String newline =null;

            int matches;
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
                matches = compareNumbers(chosenNumbersInInt,randomNumbersInInt);

                //create GameCounter object give the right amount of Matches and init with 1 Copy
                GameCounter gameX = new  GameCounter(matches,1);
                listOfGames.add(gameX);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Counts how many Cards there are in total with all copies
        int totalCards = 0;
        for(int i=0;i<listOfGames.size();i++){
            for(int j=0;j<listOfGames.get(i).getNumbersMatch();j++){
                int copyNumber = listOfGames.get(i+j+1).getCopies();
                listOfGames.get(i+j+1).setCopies(copyNumber+listOfGames.get(i).getCopies());
            }
            totalCards += listOfGames.get(i).getCopies();

        }
        System.out.println(totalCards);


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

        for (int k : array1) {
            for (int i : array2) {
                if (k == i && !(k == 0)) {
                    if (matches > 0) {
                        points = points * 2;
                    }

                    matches++;
                }
            }
        }
        System.out.println("Points made this Game "+points);
        System.out.println("The Count "+matches);
        return matches;
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