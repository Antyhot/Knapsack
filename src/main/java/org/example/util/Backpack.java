package org.example.util;

import java.util.ArrayList;

public class Backpack extends ArrayList<Item> {

    public int calculateValue() {
        return this.stream().mapToInt(Item::value).sum();
    }

    public int calculateSize() {
        return this.stream().mapToInt(Item::size).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total value: %d%n".formatted(this.calculateValue()));
        sb.append("Total size: %d%n".formatted(this.calculateSize()));
        sb.append("Items:\n");

        this.forEach(item -> sb.append("\t%s%n".formatted(item.toString())));

        return sb.toString();

    }
}
