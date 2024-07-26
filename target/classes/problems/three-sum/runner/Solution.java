import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        String filePath = "/dev/stdin";
        List<String> lines = readLinesFromFile(filePath);
        int size_arr = Integer.parseInt(lines.get(0).trim());
        List<Integer> arr = new ArrayList<>(size_arr);
        String[] inputStr_arr = lines.get(1).split("\s+");
        for (String str : inputStr_arr) {
            arr.add(Integer.parseInt(str));
        }

        List<Integer> result = ThreeSum.threeSum(arr);
        System.out.println(result);
    }
    public static List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
class ThreeSum {
    public static List<Integer> threeSum(List<Integer> arr) {
        // Implementation goes here
        return result;
    }
}
