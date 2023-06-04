package gg.convict.prison.leaderboard;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.leaderboard.entry.LeaderboardEntry;
import gg.convict.prison.leaderboard.sort.SortAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.hydrapvp.libraries.utils.CC;

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
        });
    }

    public final String getId() {
        return CC.strip(display)
                .replace(" ", "_")
                .toLowerCase();
    }

    public abstract List<LeaderboardEntry> fetchData();

}