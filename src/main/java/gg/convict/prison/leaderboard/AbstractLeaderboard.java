package gg.convict.prison.leaderboard;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.leaderboard.entry.LeaderboardEntry;
import gg.convict.prison.leaderboard.sort.SortAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class AbstractLeaderboard<T> {

    private final List<LeaderboardEntry> data = new ArrayList<>();
    private final Class<T> type;
    private final String display;

    public final void refresh() {
        SortAlgorithm algorithm = SortAlgorithm.from(type);
        if (algorithm == null) {
            PrisonPlugin.get().getLogger().severe(
                    "No sorting algorithm found for type " + type.getSimpleName());
            return;
        }

        PrisonPlugin.EXECUTOR.execute(() -> {
            List<LeaderboardEntry> fetchedData = fetchData();

            fetchedData.removeIf(entry -> !LeaderboardModule.get().canDisplay(entry.getUuid()));
            fetchedData.sort(algorithm.getComparator());

            data.clear();
            data.addAll(fetchedData);
            fetchedData.clear(); // memory
        });
    }

    public final List<LeaderboardEntry> getData(int limit) {
        List<LeaderboardEntry> result = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            if (data.isEmpty() || data.get(i) == null)
                continue;

            result.add(data.get(i));
        }

        return result;
    }

    public final String getId() {
        return CC.strip(display)
                .replace(" ", "_")
                .toLowerCase();
    }

    public abstract List<LeaderboardEntry> fetchData();

}