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
            (o1, o2) -> (int) (o2.getValue().longValue() - o1.getValue().longValue())
    ),
    DOUBLE(
            Double.class,
            (o1, o2) -> (int) (o2.getValue().doubleValue() - o1.getValue().doubleValue())
    ),
    INTEGER(
            Integer.class,
            (o1, o2) -> o2.getValue().intValue() - o1.getValue().intValue()
    );

    private final Class<?> type;
    private final Comparator<LeaderboardEntry> comparator;

    public static SortAlgorithm from(Class<?> type) {
        for (SortAlgorithm algorithm : SortAlgorithm.values()) {
            if (algorithm.getType().equals(type))
                return algorithm;
        }

        return null;
    }

}