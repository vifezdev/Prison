package gg.convict.prison.util.placeholder.command;

import gg.convict.prison.util.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import gg.convict.prison.util.placeholder.PlaceholderService;
import gg.convict.prison.util.placeholder.adapter.PlaceholderAdapter;

public class PlaceholderCommands {

    @Command(names = {"placeholder list"},
             permission = "op",
             description = "List available placeholders")
    public boolean list(CommandSender sender) {
        sender.sendMessage(CC.RED + CC.BOLD + "Available Placeholders");
        for (PlaceholderAdapter adapter : PlaceholderService.getAdapterList()) {
            sender.sendMessage(CC.YELLOW + " - " + CC.BLUE + adapter.getClass().getSimpleName() + ": "
                    + CC.YELLOW + adapter.getIdentifier());
        }
        return true;
    }

    @Command(names = {"placeholder test"},
             permission = "op",
             description = "Test a string for replacements")
    public boolean test(Player sender, @Param(name = "text", wildcard = true) String text) {
        sender.sendMessage(CC.BLUE + "Original: " + CC.YELLOW + text);
        sender.sendMessage(CC.BLUE + "Replaced: " + CC.YELLOW + PlaceholderService.replace(sender, text));
        return true;
    }


}
