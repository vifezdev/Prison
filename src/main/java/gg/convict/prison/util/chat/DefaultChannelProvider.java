package gg.convict.prison.util.chat;

import org.bukkit.entity.Player;

public interface DefaultChannelProvider {

    DefaultChannelProvider DEFAULT = (player) -> ChatService.getDefaultChannel();

    ChatChannel getDefaultChannel(Player player);

}
