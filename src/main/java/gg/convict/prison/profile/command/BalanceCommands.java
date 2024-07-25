package gg.convict.prison.profile.command;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import gg.convict.prison.profile.util.MoneyUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

import java.math.BigDecimal;

public class BalanceCommands {

    @Command(names = {"balance", "eco", "bal"},
            description = "View a player's balance",
            async = true)
    public void execute(CommandSender sender, @Param(name = "target",
            defaultValue = "@self") Profile target) {
        sender.sendMessage(CC.format(
                "&fBalance of &b%s&f: &2$&a%s&f.",
                Bukkit.getOfflinePlayer(target.getUuid()).getName(),
                MoneyUtil.format(target.getBalance(), 0)));
    }

    @Command(names = "pay",
            description = "Pay a player money",
            playerOnly = true,
            async = true)
    public void pay(Player player,
                    @Param(name = "target") Profile target,
                    @Param(name = "amount") int amount) {
        if (player.getUniqueId().equals(target.getUuid())) {
            player.sendMessage(CC.RED + "You cannot pay yourself.");
            return;
        }

        if (amount < 50) {
            player.sendMessage(CC.RED + "You cannot pay less than $50.");
            return;
        }

        Profile profile = ProfileModule.get().getProfileHandler().getProfile(player);
        if (profile.getBalance().longValue() < amount) {
            player.sendMessage(CC.RED + "You do not have enough money to pay $" + amount + ".");
            return;
        }

        profile.removeBalance(amount);
        target.addBalance(amount);

        player.sendMessage(CC.format(
                "&fYou have paid &b%s &2$&a%s&f.",
                Bukkit.getOfflinePlayer(target.getUuid()).getName(),
                amount));

        target.sendMessage(CC.format(
                "&fYou have been paid &2$&a%s &fby &b%s&f.",
                amount, player.getDisplayName()));
    }

    @Command(names = {"balance set", "eco set", "bal set"},
            permission = "prison.balance.set",
            description = "Set the balance of a player",
            hidden = true,
            async = true)
    public void set(CommandSender sender,
                    @Param(name = "target") Profile target,
                    @Param(name = "amount") int amount) {
        if (amount < 0) {
            sender.sendMessage(CC.RED + "You cannot set the balance to below 0.");
            return;
        }

        target.setBalance(new BigDecimal(amount));
        sender.sendMessage(CC.format(
                "&fSet the balance of &b%s&f to &2$&a%s&f.",
                Bukkit.getOfflinePlayer(target.getUuid()).getName(),
                amount));
    }

}