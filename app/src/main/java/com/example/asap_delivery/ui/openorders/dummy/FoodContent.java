package com.example.asap_delivery.ui.openorders.dummy;

import com.example.asap_delivery.FoodItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class FoodContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<FoodItems> ITEMS = new ArrayList<FoodItems>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, FoodItems> ITEM_MAP = new HashMap<String, FoodItems>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createFoodItem(i));
        }
    }

    private static void addItem(FoodItems item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getTitle(), item);
    }

    private static FoodItems createFoodItem(int position) {
        return new FoodItems(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */

}
