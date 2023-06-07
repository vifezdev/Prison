package gg.convict.prison.command;

import gg.convict.prison.config.PrisonBranding;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.utils.CC;

public class FlyCommand {

    @Command(names = "fly",
            description = "Toggle your flight status",
            playerOnly = true)
    public void execute(Player player) {
        if (player.getAllowFlight()) {
            player.setFlying(false);
            player.setAllowFlight(false);
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
        }

        player.sendMessage(CC.format(
                "%sYou have %s&f your &bflight&f.",
                PrisonBranding.get().getPrefix(),
                player.getAllowFlight() ? "&aenabled" : "&cdisabled"
        ));
    }

}