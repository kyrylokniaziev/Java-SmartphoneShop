package org.kyrylo;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Smartphone, Integer> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public void add(Smartphone smartphone, int quantity) {
        items.put(smartphone, items.getOrDefault(smartphone, 0) + quantity);
    }

    public void remove(Smartphone smartphone) {
        if (!items.containsKey(smartphone)) {
            throw new IllegalArgumentException("Smartphone not in cart");
        }
        items.remove(smartphone);
    }

    public Map<Smartphone, Integer> getItems() {
        return items;
    }
}

