package gg.convict.prison.leaderboard.impl;

import gg.convict.prison.leaderboard.AbstractLeaderboard;
import gg.convict.prison.leaderboard.entry.LeaderboardEntry;
import gg.convict.prison.mongo.MongoModule;
import org.bson.Document;
import org.hydrapvp.libraries.utils.CC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TopBalanceLeaderboard extends AbstractLeaderboard<Long> {

    public TopBalanceLeaderboard() {
        super(Long.class, CC.DGREEN + CC.BOLD + "Balance Top");
    }

    @Override
    public List<LeaderboardEntry> fetchData() {
        List<LeaderboardEntry> result = new ArrayList<>();

        for (Document document : MongoModule.get().getProfiles().find()) {
            if (!document.containsKey("balance"))
                continue;

            long balance = document.getLong("balance");
            result.add(new LeaderboardEntry(
                    UUID.fromString(document.getString("uuid")),
                    balance
            ));
        }

        return result;
    }

}