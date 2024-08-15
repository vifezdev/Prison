package gg.convict.prison.util.command.permission.defaults;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import gg.convict.prison.util.command.CommandService;
import gg.convict.prison.util.command.permission.PermissionAdapter;
import gg.convict.prison.util.CC;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 05.03.2021 / 09:10
 * libraries / cc.invictusgames.libraries.command.permission.defaults
 */

public class ConsoleOnlyPermission extends PermissionAdapter {

    public ConsoleOnlyPermission() {
        super("console");
    }

    public boolean test(CommandSender sender) {
        boolean b = testSilent(sender);
        if (!b) {
            if (sender.isOp())
                sender.sendMessage(CC.RED + "This command can only be used from console.");
            else sender.sendMessage(CommandService.NO_PERMISSION_MESSAGE);
        }
        return b;
    }

    @Override
    public boolean testSilent(CommandSender sender) {
        return sender instanceof ConsoleCommandSender;
    }
}
