// Import necessary IO and utility classes.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) throws IOException {
        // Set up a BufferedReader to read from a file.
        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/fzesb/Desktop/InputText2.txt"));
        // Initialize variables.
        String line;
        // Create a list to hold color names.
        ArrayList<String> colours = new ArrayList<>();
        // Populate the color list.
        colours.add("green");
        colours.add("blue");
        colours.add("red");
        // Create a regex pattern to match digits followed by color names, with optional whitespace.
        Pattern pattern = Pattern.compile("(\\d{1,2})\\s*(green|blue|red)");
        // Initialize counter for the total games.
        int totalGames = 0;

        // Read each line of the file.
        while ((line = reader.readLine()) != null) {
            // Initialize variables to keep track of the maximum number found for each color.
            int maxGreen = 0, maxBlue = 0, maxRed = 0;

            // Match the pattern within the line.
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                // Parse the number associated with the color.
                int number = Integer.parseInt(matcher.group(1));
                // Update the maximum number for the corresponding color.
                switch (matcher.group(2)) {
                    case "green":
                        maxGreen = Math.max(maxGreen, number);
                        break;
                    case "blue":
                        maxBlue = Math.max(maxBlue, number);
                        break;
                    case "red":
                        maxRed = Math.max(maxRed, number);
                        break;
                }
            }
            // Calculate the product of the maximum numbers for each color.
            int temp = maxGreen * maxBlue * maxRed;
            // Sum this product to the total games count.
            totalGames += temp;
        }
        // Close the reader after all lines have been processed.
        reader.close();
        // Print the calculated total games.
        System.out.println("Total: " + totalGames);
    }
}
