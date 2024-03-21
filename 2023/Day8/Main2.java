import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {
        String instructionsLR = "LLRLRRRLLRRRLRRLRRLRLRRRLRRRLRLLRLRRLRRLRLLRRLRRRLRRLRLRLRLRRRLRRLRLLLRRLRRRLLLRLRRRLRRRLLRRLRRRLRLRRRLLLRRLLRRLRRLLLRRRLRRRLRRRLRRLLRLRLRLRRRLRLRLRRLRRLRLRRRLRRLRRRLRRRLLLRLRRLRRLRLLRRLLRRLRRLLRLRRLRRLRLRLLLRLLRRLRRLRRRLLRRLLRRRLRRLRRRLRRRLLRRRLRRRLLRRRLRLRLLRRLRLRLRRRR";
        ArrayList<StringWithPair> ListAAA = new ArrayList<>();
        //Reads Input file int String line by line
        String filename = "C:/Users/fzesb/Documents/AdventofCode/08/input.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = splitAndTrim(line);
                if (parts.length == 3) {
                    String Name = parts[0].trim();
                    String leftValue = parts[1].trim();
                    String rightValue = parts[2].trim();

                    StringPair pairOfString = new StringPair(leftValue,rightValue);
                    StringWithPair NameWithPair = new StringWithPair(Name,pairOfString);
                    ListAAA.add(NameWithPair);

                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int stepsTillZZZ = 0;
        boolean reachedZZZ = false;
        StringWithPair start;
        ArrayList<StringWithPair> listOfXXA = new ArrayList<>();
        for (StringWithPair stringWithPair : ListAAA) {
            if(stringWithPair.getName().charAt(2) == 'A'){
                start  = stringWithPair;
                listOfXXA.add(start);
            }
        }
        while(!reachedZZZ){
            for(int i=0;i<instructionsLR.length();i++) {
                char instructionChar = instructionsLR.charAt(i);
                for(int j=0;j<listOfXXA.size();j++){
                    String temp = switch (instructionChar) {
                        case 'L' -> listOfXXA.get(j).getPair().getLeft();
                        case 'R' -> listOfXXA.get(j).getPair().getRight();
                        default -> null;
                    };

                    boolean flag = false;
                    for (StringWithPair stringWithPair : ListAAA) {
                        assert temp != null;
                        if (temp.equals(stringWithPair.getName())) {
                            listOfXXA.set(j,stringWithPair);
                            flag = true;

                        }
                        if (flag) {
                            break;
                        }
                    }
                }
                //check if all XXZ
                boolean allZZZ = true;
                for (StringWithPair withPair : listOfXXA) {
                    if (withPair.getName().charAt(2) != 'Z') {
                        allZZZ = false;
                        break; // No need to check further if one element does not meet the condition
                    }
                }

                stepsTillZZZ += 1;
                if (allZZZ) {
                    reachedZZZ = true;
                    break;
                }
            }

        }
        System.out.println(stepsTillZZZ);
    }
    private static String[] splitAndTrim(String input) {
        // Split the input string into parts
        String[] parts = input.split("=");

        if (parts.length != 2) {
            return new String[0]; // Invalid input format
        }

        // Extract class name, left value, and right value
        String Name = parts[0].trim();
        String pairContent = parts[1].trim().replaceAll("[() ]", "");
        String[] pairParts = pairContent.split(",");

        if (pairParts.length != 2) {
            return new String[0]; // Invalid pair format
        }

        String leftValue = pairParts[0].trim();
        String rightValue = pairParts[1].trim();

        return new String[]{Name, leftValue, rightValue};
    }
}