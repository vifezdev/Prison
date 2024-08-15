package gg.convict.prison.leaderboard;

import lombok.Getter;
import gg.convict.prison.util.configuration.StaticConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class LeaderboardHandler implements StaticConfiguration {

    private final List<UUID> exceptions = new ArrayList<>();

}