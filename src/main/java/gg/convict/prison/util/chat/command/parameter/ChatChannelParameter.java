package gg.convict.prison.util.chat.command.parameter;

import gg.convict.prison.util.CC;
import gg.convict.prison.util.chat.ChatChannel;
import gg.convict.prison.util.chat.ChatService;
import gg.convict.prison.util.command.parameter.ParameterType;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 17.11.2020 / 20:42
 * libraries / cc.invictusgames.libraries.chat.command.parameter
 */

public class ChatChannelParameter implements ParameterType<ChatChannel> {

    @Override
    public ChatChannel parse(CommandSender sender, String source) {
        ChatChannel channel = ChatService.fromName(source);

        if (channel == null || (!channel.canAccess(sender))) {
            sender.sendMessage(CC.RED + "Chat Channel " + CC.YELLOW + source + CC.RED + " not found.");
            return null;
        }

        return channel;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        List<String> completions = new ArrayList<>();
        for (ChatChannel channel : ChatService.getChannels()) {
            if (channel.canAccess(sender))
                completions.add(channel.getName());
        }
        return completions;
    }
}
