package gg.convict.prison.util.chat.impl;

import gg.convict.prison.util.CC;
import gg.convict.prison.util.chat.ChatChannel;
import gg.convict.prison.util.playersetting.impl.LibrariesSettings;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 17.11.2020 / 20:26
 * libraries / cc.invictusgames.libraries.chat
 */

public class PublicChat extends ChatChannel {

    public PublicChat() {
        super("public",
                CC.RED + "Public",
                null,
                Arrays.asList("pc", "p", "pub", "global", "g", "gc"),
                '!',
                0);
    }

    @Override
    public boolean onChat(Player player, String message) {
        if (!LibrariesSettings.GLOBAL_CHAT.get(player)) {
            player.sendMessage(CC.RED + "You have the global chat disabled.");
            return false;
        }

        return true;
    }

    @Override
    public String getFormat(Player player, CommandSender sender) {
        if (sender instanceof Player && !LibrariesSettings.GLOBAL_CHAT.get((Player) sender))
            return null;

        return "<%1$s" + ChatColor.WHITE + "> %2$s";
    }
}
