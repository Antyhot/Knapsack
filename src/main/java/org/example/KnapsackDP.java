package org.example;

import org.example.util.Backpack;
import org.example.util.Item;
import org.example.util.ItemData;
import org.example.util.Utility;

public class KnapsackDP {

    public static void main(String[] args) {

        ItemData itemData = Utility.generateExampleData(5);
        Backpack backpack = dynamicProgrammingBackpack(itemData);

        System.out.println(backpack);

    }


    public static Backpack dynamicProgrammingBackpack(ItemData itemData) {

        Backpack backpack = new Backpack();

        int capacity = itemData.capacity();
        Item[] items = itemData.items();

        int[][] table = new int[items.length + 1][capacity + 1];


        for (int i = 1; i <= items.length; i++) {

            Item currentItem = items[i - 1];
            int size = currentItem.size();

            for (int c = 0; c <= capacity; c++) {

                if (c - size >= 0) {
                    table[i][c] = Math.max(table[i - 1][c - size] + currentItem.value(), table[i-1][c]);
                } else {
                    table[i][c] = table[i - 1][c];
                }

            }

        }


        int c = capacity;
        for (int i = items.length; i > 0; i--) {

            if (table[i][c] > table[i-1][c]) {
                Item item = items[i-1];
                backpack.add(item);

                c -= item.size();
            }

        }


        return backpack;

    }




}
