import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
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
        StringWithPair start = ListAAA.get(0);
        for (StringWithPair stringWithPair : ListAAA) {
            if(stringWithPair.getName().equals("AAA")){
                start  = stringWithPair;
            }
        }
        while(!reachedZZZ){
            for(int i=0;i<instructionsLR.length();i++){
                char instructionChar = instructionsLR.charAt(i);

                String temp = switch (instructionChar) {
                    case 'L' -> start.getPair().getLeft();
                    case 'R' -> start.getPair().getRight();
                    default -> null;
                };
                stepsTillZZZ +=1;

                System.out.println(start.getName());
                System.out.println(stepsTillZZZ);
                boolean flag = false;
                for (StringWithPair stringWithPair : ListAAA) {
                    assert temp != null;
                    if (temp.equals(stringWithPair.getName())) {
                        flag = true;
                        start = stringWithPair;
                        if (start.getName().equals("ZZZ")) {
                            reachedZZZ = true;
                        }
                    }
                    if(flag){
                        break;
                    }
                }
                if(reachedZZZ){
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