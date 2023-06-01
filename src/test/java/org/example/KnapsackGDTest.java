package org.example;

import org.example.util.Item;
import org.example.util.ItemData;
import org.junit.Test;

import java.util.ArrayList;

public class KnapsackGDTest {

    @Test
    public void testBruteforceBackpack() {
        Item[] items = new Item[5];
        items[0] = new Item(10, 5, 0);
        items[1] = new Item(40, 4, 1);
        items[2] = new Item(30, 6, 2);
        items[3] = new Item(50, 3, 3);
        items[4] = new Item(30, 2, 4);


        ItemData itemData = new ItemData(10, items);

        ArrayList<Item> backpack = KnapsackGD.greedyBackpack(itemData);

        assert backpack.size() == 3;
        assert backpack.contains(items[3]);
        assert backpack.contains(items[4]);
        assert backpack.contains(items[1]);

        int sizeSum = backpack.stream().mapToInt(Item::size).sum();
        assert sizeSum == 9;


    }


}