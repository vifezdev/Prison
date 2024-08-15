package gg.convict.prison.leaderboard.command.parameter;

import gg.convict.prison.leaderboard.AbstractLeaderboard;
import gg.convict.prison.leaderboard.LeaderboardModule;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardParameter implements ParameterType<AbstractLeaderboard<?>> {

    @Override
    public AbstractLeaderboard<?> parse(CommandSender sender, String input) {
        AbstractLeaderboard<?> leaderboard = LeaderboardModule.get().getLeaderboard(input);

        if (leaderboard == null)
            sender.sendMessage(CC.format(
                    "&cA leaderboard with the name &b%s&c does not exist.",
                    input));

        return leaderboard;
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, List<String> list) {
        List<String> result = new ArrayList<>();

        for (AbstractLeaderboard<?> leaderboard : LeaderboardModule.get().getLeaderboards())
            result.add(leaderboard.getId());

        return result;
    }

}