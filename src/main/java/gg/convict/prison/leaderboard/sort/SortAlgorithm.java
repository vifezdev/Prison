package gg.convict.prison.leaderboard.sort;

import gg.convict.prison.leaderboard.entry.LeaderboardEntry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

@Getter
@RequiredArgsConstructor
public enum SortAlgorithm {

    LONG(
            Long.class,
            (o1, o2) -> (int) ((long) o2.getValue() - (long) o1.getValue())
    ),
    DOUBLE(
            Double.class,
            (o1, o2) -> (int) ((double) o2.getValue() - (double) o1.getValue())
    ),
    INTEGER(
            Integer.class,
            (o1, o2) -> (int) o2.getValue() - (int) o1.getValue()
    );

    private final Class<?> type;
    private final Comparator<LeaderboardEntry<?>> comparator;

    public static SortAlgorithm from(Class<?> type) {
        for (SortAlgorithm algorithm : SortAlgorithm.values()) {
            if (algorithm.getType().equals(type))
                return algorithm;
        }

        return null;
    }

}