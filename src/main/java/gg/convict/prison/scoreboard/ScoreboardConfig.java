package gg.convict.prison.scoreboard;

import lombok.Getter;
import lombok.Setter;
import gg.convict.prison.util.configuration.StaticConfiguration;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ScoreboardConfig implements StaticConfiguration {

    private final String scoreboardTitle = "&b&lConvict &7┃&f Prison";

    private boolean sectionsBarsEnabled = true;
    private String sectionBarText = "&7&m-------------------";

    private final List<String> baseLines = new ArrayList<String>() {{
        add("&a&7&m-------------------");
        add("{sections}");
        add("&a");
        add("&bconvict.gg");
        add("&b&7&m-------------------");
    }};

    private final List<String> combatSectionLines = new ArrayList<String>() {{
        add("&b&l ▎ &3Combat Stats");
        add("&b&l ▎ &fKills: &b{profile:kills}");
        add("&b&l ▎ &fDeaths: &b{profile:deaths}");
        add("&b&l ▎ &fKDR: &b{profile:kd}");
    }};

    private final List<String> miningSectionLines = new ArrayList<String>() {{
        add("&b&l ▎ &3Mine");
        add("&b&l ▎ &fOwner: &b{mine:owner-formatted}");
        add("&b&l ▎ &fBlocks Left: &b{mine:blocks-left}%");
    }};

    private final List<String> statsSectionLines = new ArrayList<String>() {{
        add("&b&l ▎ &3{player:name}");
        add("&b&l ▎ &fRank: &b{profile:rank}");
        add("&b&l ▎ &fTokens: &3⛃&b{profile:tokens-formatted}");
        add("&b&l ▎ &fBalance: &2$&a{profile:balance-formatted}");
    }};

}