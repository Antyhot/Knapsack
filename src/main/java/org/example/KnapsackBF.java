package org.example;

import org.example.util.Backpack;
import org.example.util.Item;
import org.example.util.ItemData;
import org.example.util.Utility;

import java.util.*;
import java.util.stream.IntStream;

public class KnapsackBF {
    public static void main(String[] args) {

        int numberOfItems = 5;
        ItemData itemData = Utility.generateExampleData(numberOfItems);

        Backpack backpack = bruteforceBackpack(itemData);


        for (Item item : backpack) {
            System.out.println("item = " + item);
        }

        System.out.println("backpack.calculateSize() = " + backpack.calculateSize());
        System.out.println("backpack.calculateValue() = " + backpack.calculateValue());


    }

    public static Backpack bruteforceBackpack(ItemData itemData) {

        Item[] items = itemData.items();
        int numberOfItems = items.length;
        int capacity = itemData.capacity();


        List<int[]> combinations = Utility.generate01Combinations(5);


        return combinations.stream()
                .map(combination -> {
                    Backpack backpack = new Backpack();
                    IntStream.range(0, numberOfItems)
                            .filter(i -> combination[i] == 1)
                            .mapToObj(i -> items[i])
                            .forEach(backpack::add);

                    int sizeSum = backpack.calculateSize();

                    return (sizeSum <= capacity) ? backpack : null;
                }).filter(Objects::nonNull)
                .max(Comparator.comparingInt(Backpack::calculateValue))
                .orElseThrow();
    }
}
