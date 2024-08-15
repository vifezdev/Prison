package gg.convict.prison.leaderboard.entry;

import lombok.Data;
import gg.convict.prison.util.uuid.UUIDCache;

import java.util.UUID;

@Data
public class LeaderboardEntry {

    private final UUID uuid;
    private final Number value;

    public String getName() {
        return UUIDCache.getName(uuid);
    }

}