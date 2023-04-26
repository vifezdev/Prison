package gg.convict.prison.privatemine.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class MineList<T> {

    private final List<T> list = new ArrayList<>();

    @SafeVarargs
    public MineList(T... initialList) {
        this.addAll(initialList);
    }

    @SafeVarargs
    public final void addAll(T... t) {
        this.list.addAll(Arrays.asList(t));
    }

    public T getRandom() {
        return this.list.get(ThreadLocalRandom.current().nextInt(this.list.size()));
    }

    public void forEach(Consumer<? super T> action) {
        for (T t : this.list)
            action.accept(t);
    }

    public int size() {
        return this.list.size();
    }

    public void add(T t) {
        this.list.add(t);
    }

    public T get(int index) {
        return this.list.get(index);
    }

    public boolean contains(T t) {
        return this.list.contains(t);
    }

    public static <T> MineList<T> of(T... initialList) {
        return new MineList<>(initialList);
    }

    public static <T> MineList<T> of(List<T> list) {
        MineList<T> mineList = new MineList<>();
        list.forEach(mineList::add);
        return mineList;
    }

    public List<T> build() {
        return this.list;
    }
}
