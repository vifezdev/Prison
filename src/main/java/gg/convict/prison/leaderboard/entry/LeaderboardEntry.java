package gg.convict.prison.leaderboard.entry;

import lombok.Data;
import org.hydrapvp.libraries.uuid.UUIDCache;

import java.util.UUID;

@Data
public class LeaderboardEntry<T> {

    private final UUID uuid;
    private final T value;

    public String getName() {
        return UUIDCache.getName(uuid);
    }

}