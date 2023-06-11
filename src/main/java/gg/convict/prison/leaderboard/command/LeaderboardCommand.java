package gg.convict.prison.leaderboard.command;

import gg.convict.prison.leaderboard.AbstractLeaderboard;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

public class LeaderboardCommand {

    @Command(names = {"leaderboard", "leaderboards", "lb"},
            description = "Show the leaderboards",
            playerOnly = true)
    public void execute(Player player, @Param(name = "type") AbstractLeaderboard<?> leaderboard) {
        player.sendMessage(CC.CHAT_BAR);

        player.sendMessage(CC.format(
                "&b&l%s Leaderboards",
                leaderboard.getDisplay()
        ));

        leaderboard.getData(10).forEach(entry -> player.sendMessage(CC.format(
                "&7 - &f%s: &b%s",
                entry.getName(),
                entry.getValue()
        )));

        player.sendMessage(CC.CHAT_BAR);
    }

}