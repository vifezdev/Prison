package gg.convict.prison.util.chat.command;

import gg.convict.prison.util.CC;
import gg.convict.prison.util.chat.ChatChannel;
import gg.convict.prison.util.chat.ChatService;
import gg.convict.prison.util.command.CommandService;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import org.bukkit.entity.Player;


/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 17.11.2020 / 20:42
 * libraries / cc.invictusgames.libraries.chat.command
 */

public class ChatCommand {

    @Command(names = {"chat"}, description = "Change your chat channel", playerOnly = true, async = true)
    public boolean chat(Player sender, @Param(name = "channel", defaultValue = "@list") String channel) {
        if (channel.equals("@list")) {
            sender.sendMessage(CC.SMALL_CHAT_BAR);
            sender.sendMessage(CC.RED + CC.BOLD + "Chat Channels");
            sender.sendMessage(CC.format("&eYou are currently talking in %s &echat.",
                    ChatService.fromPlayer(sender).getDisplayName().toLowerCase()));
            sender.sendMessage(" ");
            for (ChatChannel chatChannel : ChatService.getChannels()) {
                if (chatChannel.canAccess(sender))
                    sender.sendMessage(CC.format(
                            " - %s &f - &c/%s [message] &e(or prefix with &c%s&e)",
                            chatChannel.getDisplayName(),
                            chatChannel.getAliases().size() >= 1 ? chatChannel.getAliases().get(0) :
                                    chatChannel.getName(),
                            chatChannel.getPrefix()
                    ));
            }
            sender.sendMessage(CC.SMALL_CHAT_BAR);
            return true;
        }

        ChatChannel chatChannel = CommandService.getParameter(ChatChannel.class).parse(sender, channel);
        if (chatChannel != null)
            ChatService.setChatChannel(sender, chatChannel, false);
        return true;
    }

    @Command(names = {"publicchat", "pc", "globalchat", "gc"}, description = "Send a message in public chat",
             playerOnly = true, async = true)
    public boolean publicChat(Player sender, @Param(name = "message", wildcard = true) String message) {
        ChatService.getDefaultChannelProvider().getDefaultChannel(sender).chat(sender, message);
        return true;
    }

}



