import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("C:/Users/fzesb/Documents/Uni/AdventOfCode/Day1/Input.txt"));
        List<Integer> leftlist = new ArrayList<>();
        List<Integer> rightlist = new ArrayList<>();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            leftlist.add(Integer.parseInt(parts[0]));
            rightlist.add(Integer.parseInt(parts[1]));
        }
        Collections.sort(leftlist);
        Collections.sort(rightlist);

        int Total = 0;
        for (int i=0;i<leftlist.size();i++){
            Total += Math.abs(leftlist.get(i)-rightlist.get(i));
        }
        System.out.println(Total);
        int Totalb = 0;
        for (Integer value : leftlist) {
            int count = 0;
            for (Integer integer : rightlist) {
                if (value.equals(integer)) {
                    count++;
                }
            }
            Totalb += value * count;
        }
        System.out.println(Totalb);
    }

}