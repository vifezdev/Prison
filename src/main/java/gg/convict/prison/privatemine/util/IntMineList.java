package gg.convict.prison.privatemine.util;

public class IntMineList extends MineList<Integer> {

    public IntMineList(Integer... startingValues) {
        addAll(startingValues);
    }

    public IntMineList add(Integer... integers) {
        addAll(integers);
        return this;
    }

    public IntMineList addRange(int min, int max) {
        for (int i = min; i < max; i++)
            add(i);

        return this;
    }

}
