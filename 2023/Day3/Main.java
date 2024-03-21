import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/fzesb/Desktop/InputText3.txt"));
        ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('*', '-', '%', '$', '=', '@', '&', '+', '/', '#'));
        ArrayList<ArrayList<Character>> schematic = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            ArrayList<Character> charList = new ArrayList<>();
            for (char ch : line.toCharArray()) {
                charList.add(ch);
            }
            schematic.add(charList);
        }
        reader.close();
        int total = 0;
        for (int rowIndex = 0; rowIndex < schematic.size(); rowIndex++) {
            ArrayList<Character> row = schematic.get(rowIndex);
            for (int colIndex = 0; colIndex < row.size(); colIndex++) {
                Character ch = row.get(colIndex);
                char ch2,ch3;
                DigitLocation d1 = null,d2 = null,d3 = null;
                boolean ch1Full=false,ch2Full=false,ch3Full=false;
                ArrayList<DigitLocation> numbers = new ArrayList<>();
                if (Character.isDigit(ch)) {
                    d1 = new DigitLocation(ch, rowIndex, colIndex);
                    ch1Full = true;
                    if (colIndex + 1 < row.size() && Character.isDigit(row.get(colIndex+1))) {
                        colIndex++;
                        ch2 = row.get(colIndex);
                        d2 = new DigitLocation(ch2, rowIndex, colIndex);
                        ch2Full = true;
                        if (colIndex + 1 < row.size() && Character.isDigit(row.get(colIndex+1))) {
                            colIndex++;
                            ch3 = row.get(colIndex);
                            d3 = new DigitLocation(ch3, rowIndex, colIndex);
                            ch3Full = true;
                            colIndex++;
                        }
                    }
                }

                int trueCount = 0;
                if (ch1Full) trueCount++;
                if (ch2Full) trueCount++;
                if (ch3Full) trueCount++;
                switch (trueCount) {
                    case 0:
                        break;
                    case 1:
                        numbers = sequenceNumbers(d1);
                        break;
                    case 2:
                        numbers = sequenceNumbers(d1,d2);
                        break;
                    case 3:
                        numbers = sequenceNumbers(d1,d2,d3);
                        break;
                    default:
                        System.out.println("Unexpected case.");
                }

                for (DigitLocation number : numbers) {
                    if (isAdjacentToSymbol(number, schematic, symbols)) {
                        String connectedNumber = numberBesidesSymbol(numbers);
                        int convertedNumber = Integer.parseInt(connectedNumber);
                        System.out.println("number that is accepted "+convertedNumber);
                        total += convertedNumber;
                        break;
                    }
                }
            }
        }

        System.out.println("Total count "+total);
    }
    public static String numberBesidesSymbol(ArrayList<DigitLocation> numbers){
        String numberBesidesSymbol = null;
        switch (numbers.size()) {
            case 1 -> numberBesidesSymbol = "" + numbers.get(0).getDigit();
            case 2 -> numberBesidesSymbol = "" + numbers.get(0).getDigit() + numbers.get(1).getDigit();
            case 3 ->
                    numberBesidesSymbol = "" + numbers.get(0).getDigit() + numbers.get(1).getDigit() + numbers.get(2).getDigit();
            default -> System.out.println("Something wen wrong with the size of numbers");
        }
        return numberBesidesSymbol;
    }
    private static boolean isAdjacentToSymbol(DigitLocation number, ArrayList<ArrayList<Character>> schematic, ArrayList<Character> symbols) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = number.getRowIndex() + i;
                int newCol = number.getColIndex() + j;
                if (newRow >= 0 && newRow < schematic.size() && newCol >= 0 && newCol < schematic.get(newRow).size()) {
                    if (symbols.contains(schematic.get(newRow).get(newCol))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static ArrayList<DigitLocation> sequenceNumbers(DigitLocation... digitLocations){
        ArrayList<DigitLocation> tempNumbers = new ArrayList<>();
        Collections.addAll(tempNumbers, digitLocations);
        return  tempNumbers;
    }
}
