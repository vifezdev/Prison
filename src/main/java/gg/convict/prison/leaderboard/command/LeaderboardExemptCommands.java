package gg.convict.prison.leaderboard.command;

import gg.convict.prison.leaderboard.LeaderboardModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;
import org.hydrapvp.libraries.uuid.UUIDCache;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class LeaderboardExemptCommands {

    private final LeaderboardModule module;

    @Command(names = "leaderboard exempt list",
            permission = "prison.leaderboard.exempt",
            description = "Lists all leaderboard exempt players")
    public void list(CommandSender sender) {
        List<UUID> exceptions = module.getHandler().getExceptions();

        if (exceptions.isEmpty()) {
            sender.sendMessage(CC.RED + "There are no exempt players.");
            return;
        }

        sender.sendMessage(CC.CHAT_BAR);
        sender.sendMessage(CC.format(
                "&b&lExempt Players &7(%s)&b:",
                exceptions.size()
        ));

        exceptions.forEach(uuid -> sender.sendMessage(CC.format(
                "&7 - &f%s",
                UUIDCache.getName(uuid)
        )));
        sender.sendMessage(CC.CHAT_BAR);
    }

    @Command(names = "leaderboard exempt add",
            permission = "prison.leaderboard.exempt",
            description = "Exempt a player from the leaderboards")
    public void add(CommandSender sender, @Param(name = "target") UUID target) {
        List<UUID> exceptions = module.getHandler().getExceptions();
        if (exceptions.contains(target)) {
            sender.sendMessage(CC.format(
                    "&b%s&c is already exempt from the leaderboards.",
                    UUIDCache.getName(target)));
            return;
        }

        sender.sendMessage(CC.format(
                "&b%s&f is &anow&f exempt from the leaderboards.",
                UUIDCache.getName(target)
        ));
    }

    @Command(names = "leaderboard exempt remove",
            permission = "prison.leaderboard.exempt",
            description = "Remove a player from the exemption list")
    public void remove(CommandSender sender, @Param(name = "target") UUID target) {
        List<UUID> exceptions = module.getHandler().getExceptions();
        if (!exceptions.contains(target)) {
            sender.sendMessage(CC.format(
                    "&b%s&c is not exempt from the leaderboards.",
                    UUIDCache.getName(target)));
            return;
        }

        sender.sendMessage(CC.format(
                "&b%s&f is &cno longer&f exempt from the leaderboards.",
                UUIDCache.getName(target)
        ));
    }

}