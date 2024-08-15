package gg.convict.prison.util.chatinput;

import org.bukkit.entity.Player;

public interface ChatInputConsumer<T> {

    boolean accept(Player player, T input);

}
