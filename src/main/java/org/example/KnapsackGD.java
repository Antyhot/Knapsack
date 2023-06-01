package org.example;

import org.example.util.Backpack;
import org.example.util.Item;
import org.example.util.ItemData;
import org.example.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class KnapsackGD {

    public static void main(String[] args) {
        ItemData generatedData = Utility.generateExampleData(5);

        Utility.printDataToFile(generatedData, "generatedData.txt");
        generatedData = Utility.loadDataFromFile("generatedData.txt");


        if (generatedData == null) return;

        System.out.printf("Knapsack capacity: %d%n", generatedData.capacity());
        System.out.printf("Number of items: %d%n", generatedData.items().length);
        ArrayList<Item> backpack = greedyBackpack(generatedData);
        int capacity = generatedData.capacity();

        //Calculate the sum of the sizes of the items in the backpack
        int sum = backpack.stream().mapToInt(Item::size).sum();
        int value = backpack.stream().mapToInt(Item::value).sum();


        System.out.printf("Backpack filled at %d/%d of full capacity%n", sum, capacity);
        System.out.printf("Backpack value is %d%n", value);


        System.out.println("Backpack contents:");
        backpack.forEach(item -> System.out.printf("\tId: %d, Value: %d, Size: %d%n", item.id(), item.value(), item.size()));


    }

    public static Backpack greedyBackpack(ItemData itemData) {
        Item[] items = itemData.items();
        int capacity = itemData.capacity();

        Item[] sortedItems = Arrays.stream(items)
                .sorted(Comparator.comparingDouble(value -> -1d * value.value() / value.size()))
                .toArray(Item[]::new);


        int remainingCapacity = capacity;

        Backpack backpack = new Backpack();
        for (Item item : sortedItems) {

            if (item.size() <= remainingCapacity) {
                backpack.add(item);
                remainingCapacity -= item.size();
            }

        }

        return backpack;
    }


}
