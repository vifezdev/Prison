package gg.convict.prison.util.command.parameter.defaults;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 03.06.2020 / 19:00
 * libraries / cc.invictusgames.libraries.commandapi.parameter.defaults
 */

public class ChatColorParameter implements ParameterType<ChatColor> {

    @Override
    public ChatColor parse(CommandSender sender, String source) {
        try {
            return ChatColor.valueOf(source.toUpperCase());
        } catch (Exception exception) {
            sender.sendMessage(CC.format(
                    "&cA chat color with the name \"%s\" does not exist.",
                    source));
            return null;
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        List<String> list = new ArrayList<>();

        for (ChatColor value : ChatColor.values())
            list.add(value.name());

        return list;
    }
}
