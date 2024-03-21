// Import necessary IO and utility classes.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        // Set up a BufferedReader to read from a file.
        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/fzesb/Desktop/InputText2.txt"));
        // Read the first line from the file.
        String line = reader.readLine();
        // Create a list to store color names.
        ArrayList<String> colours = new ArrayList<>();
        colours.add("green");
        colours.add("blue");
        colours.add("red");
        // Create a regex pattern for the colors, expecting a number before the color.
        String colourPattern = String.join("|", colours);
        Pattern pattern = Pattern.compile("(\\d{1,2})(" + colourPattern + ")");
        // Initialize counters for games.
        int gameCount = 0;
        int totalGames = 0;

        // Process each line of the file.
        while (line != null) {
            gameCount++;
            // Initialize sums for each color.
            int sumGreen = 0, sumBlue = 0, sumRed = 0;
            // Clean up the line by removing certain substrings and non-alphanumeric characters.
            String line1 = line.replaceAll("Game \\d+: ", "").replaceAll("[^a-zA-Z0-9]", "");
            // Assume the game is possible until proven otherwise.
            boolean gamePossible = true;

            // Find all occurrences of numbers followed by color names.
            Matcher matcher = pattern.matcher(line1);
            while (matcher.find()) {
                // Parse the number preceding the color name.
                int number = Integer.parseInt(matcher.group(1));
                // Depending on the color, add the number to the respective sum.
                switch (matcher.group(2)) {
                    case "green" -> sumGreen += number;
                    case "blue" -> sumBlue += number;
                    case "red" -> sumRed += number;
                }
                // If any sum exceeds a certain threshold, the game is not possible.
                if(sumGreen > 13 || sumRed > 12 || sumBlue > 14) {
                    gamePossible = false;
                    break;
                }
            }
            // If the game is possible, add the current game count to the total.
            if(gamePossible){ totalGames += gameCount; }

            // Move to the next line in the file.
            line = reader.readLine();
        }
        // Close the file reader.
        reader.close();
        // Print the total number of possible games.
        System.out.println(totalGames);
    }
}
