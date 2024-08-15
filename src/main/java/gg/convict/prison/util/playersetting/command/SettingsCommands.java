package gg.convict.prison.util.playersetting.command;

import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.playersetting.menu.SettingsMenu;
import org.bukkit.entity.Player;

public class SettingsCommands {

    @Command(names = {"settings", "options", "preferences", "prefs"},
             description = "Open the settings menu")
    public boolean settings(Player sender) {
        new SettingsMenu().openMenu(sender);
        return true;
    }

}
