package org.example.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {

    public static void printDataToFile(ItemData itemData, String filePath) {

        try (PrintWriter printWriter = new PrintWriter(filePath)) {
            printWriter.println(itemData.items().length + " " + itemData.capacity());

            for (Item item : itemData.items()) {
                printWriter.println(item.value() + " " + item.size());
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }


    }


    public static ItemData loadDataFromFile(String filePath) {

        int capacity;
        Item[] items; //Array to store items

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            String[] valuesArr = line.split(" ");
            int n = Integer.parseInt(valuesArr[0]); // Number of items
            capacity = Integer.parseInt(valuesArr[1]);

            items = new Item[n];

            for (int i = 0; i < n; i++) {
                line = br.readLine();
                valuesArr = line.split(" ");
                items[i] = new Item(Integer.parseInt(valuesArr[0]), Integer.parseInt(valuesArr[1]), i);

            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }

        return new ItemData(capacity, items);

    }

    public static ItemData generateExampleData(int numberOfItems) {

        Item[] items; //Array to store items

        items = new Item[numberOfItems];

        int capacity = 0;
        for (int i = 0; i < numberOfItems; i++) {
            int value = (1 + (int) (Math.random() * 5)) * 5;
            int size = 1 + (int) (Math.random() * 10);

            items[i] = new Item(value, size, i);

            capacity += size - (int) (Math.random() * 5);
        }

        return new ItemData(capacity, items);
    }

    public static List<int[]> generate01Combinations(int n) {

        List<int[]> combinations = new ArrayList<>();

        int[] current = new int[n];
        current[0] = 0;
        generate01Combinations(n, current, 1,combinations);

        current = new int[n];
        current[0] = 1;
        generate01Combinations(n, current, 1,combinations);

        return combinations;

    }

    private static void generate01Combinations(int n, int[] current, int index, List<int[]> combinations) {

        if (index == n) {
            combinations.add(current.clone());
            return;
        }

        current[index] = 0;
        generate01Combinations(n, current, index + 1, combinations);

        current[index] = 1;
        generate01Combinations(n, current, index + 1, combinations);


    }

    public static String display2dArray(int[][] array) {


        StringBuilder sb = new StringBuilder();
        //This function formats the 2d array nicely and keeps the spacing consistent. To do so, it first finds the largest number in the array
        //We find the max value by using streams api
        int max = Arrays.stream(array).flatMapToInt(Arrays::stream).max().orElseThrow();

        //Now we know the maximum number of digits we need to display. We can use this to create a format string
        String formatString = "%"+(int)(Math.log10(max)+1)+"d ";

        //Now we can iterate over the array and print each value using the format string
        for(int[] row : array){
            for(int value : row){
                sb.append(String.format(formatString, value));
            }
            sb.append("\n");
        }




        return sb.toString();
    }
}
