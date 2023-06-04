package gg.convict.prison.leaderboard.impl;

import gg.convict.prison.leaderboard.AbstractLeaderboard;
import gg.convict.prison.leaderboard.entry.LeaderboardEntry;
import gg.convict.prison.mongo.MongoModule;
import org.bson.Document;
import org.hydrapvp.libraries.utils.CC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TopTokensLeaderboard extends AbstractLeaderboard<Long> {

    public TopTokensLeaderboard() {
        super(Long.class, CC.AQUA + CC.BOLD + "Top Tokens");
    }

    @Override
    public List<LeaderboardEntry<Long>> fetchData() {
        List<LeaderboardEntry<Long>> result = new ArrayList<>();

        for (Document document : MongoModule.get().getProfiles().find()) {
            if (!document.containsKey("tokens"))
                continue;

            long balance = document.getLong("tokens");
            result.add(new LeaderboardEntry<>(
                    UUID.fromString(document.getString("uuid")),
                    balance
            ));
        }

        return result;
    }

}