package gg.convict.prison.util;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCollection<E> {

    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private double total = 0;

    public RandomCollection<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next() {
        double value = ThreadLocalRandom.current().nextDouble() * total;
        return map.higherEntry(value).getValue();
    }

    public void clear() {
        map.clear();
    }

    public int size() {
        return map.size();
    }

}