package gg.convict.prison.profile.command;

import gg.convict.core.util.SenderUtil;
import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

public class BalanceCommands {

    @Command(names = {"balance", "eco", "bal"},
            description = "View a player's balance")
    public void execute(CommandSender sender, @Param(name = "target", defaultValue = "@self") Profile target) {
        sender.sendMessage(CC.format(
                "&fBalance of &b%s&f: &2$&a%s&f.",
                SenderUtil.getName(target.getUuid()),
                target.getBalance()));
    }

    @Command(names = "pay",
            description = "Pay a player money",
            playerOnly = true)
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
        if (profile.getBalance() < amount) {
            player.sendMessage(CC.RED + "You do not have enough money to pay $" + amount + ".");
            return;
        }

        profile.setBalance(profile.getBalance() - amount);
        target.setBalance(target.getBalance() + amount);

        player.sendMessage(CC.format(
                "&fYou have paid &b%s &2$&a%s&f.",
                SenderUtil.getName(target.getUuid()),
                amount));

        target.sendMessage(CC.format(
                "&fYou have been paid &2$&a%s &fby &b%s&f.",
                amount, SenderUtil.getName(player)));
    }

    @Command(names = {"balance set", "eco set", "bal set"},
            permission = "prison.balance.set",
            description = "Set the balance of a player",
            hidden = true)
    public void set(CommandSender sender,
                    @Param(name = "target") Profile target,
                    @Param(name = "amount") int amount) {
        if (amount < 0) {
            sender.sendMessage(CC.RED + "You cannot set the balance to below 0.");
            return;
        }

        target.setBalance(amount);
        sender.sendMessage(CC.format(
                "&fSet the balance of &b%s&f to &2$&a%s&f.",
                SenderUtil.getName(target.getUuid()),
                amount));
    }

}