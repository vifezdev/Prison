package gg.convict.prison.profile.command;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import gg.convict.prison.profile.util.MoneyUtil;
import gg.convict.prison.util.OfflinePlayerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

import java.math.BigDecimal;

public class TokenCommands {

    @Command(names = {"tokens", "token"},
            description = "View a player's token balance",
            async = true)
    public void execute(CommandSender sender, @Param(name = "target",
            defaultValue = "@self") Profile target) {
        OfflinePlayerUtil.getOfflinePlayer(target.getUuid()).thenAccept(offlinePlayer -> {
            sender.sendMessage(CC.format(
                    "&fToken Balance of &b%s&f: &3⛃&b%s&f.",
                    offlinePlayer.getName(),
                    MoneyUtil.format(target.getBalance(), 0)));
        });
    }

    @Command(names = {"token pay", "tokens pay"},
            description = "Pay a player tokens",
            playerOnly = true,
            async = true)
    public void pay(Player player,
                    @Param(name = "target") Profile target,
                    @Param(name = "amount") int amount) {
        if (player.getUniqueId().equals(target.getUuid())) {
            player.sendMessage(CC.RED + "You cannot pay yourself.");
            return;
        }

        if (amount <= 0) {
            player.sendMessage(CC.RED + "You cannot pay less than ⛃1.");
            return;
        }

        Profile profile = ProfileModule.get().getProfileHandler().getProfile(player);
        if (profile.getTokens().longValue() < amount) {
            player.sendMessage(CC.RED + "You do not have enough tokens to pay ⛃" + amount + ".");
            return;
        }

        profile.removeTokens(amount);
        target.addTokens(amount);

        OfflinePlayerUtil.getOfflinePlayer(target.getUuid()).thenAccept(offlinePlayer -> {
            player.sendMessage(CC.format(
                    "&fYou have paid &3⛃&b%s &fto &b%s&f.",
                    amount, offlinePlayer.getName()));
        });

        target.sendMessage(CC.format(
                "&fYou have been paid &3⛃&b%s &fby &b%s&f.",
                amount,
                player.getDisplayName())
        );
    }

    @Command(names = {"token set", "tokens set"},
            permission = "prison.tokens.set",
            description = "Set the token balance of a player",
            hidden = true,
            async = true)
    public void set(CommandSender sender,
                    @Param(name = "target") Profile target,
                    @Param(name = "amount") int amount) {
        if (amount < 0) {
            sender.sendMessage(CC.RED + "You cannot set the tokens to below 0.");
            return;
        }

        target.setTokens(new BigDecimal(amount));
        OfflinePlayerUtil.getOfflinePlayer(target.getUuid()).thenAccept(offlinePlayer -> {
            sender.sendMessage(CC.format(
                    "&fYou have set the token balance of &b%s &fto &3⛃&b%s&f.",
                    offlinePlayer.getName(),
                    amount)
            );
        });
    }

}