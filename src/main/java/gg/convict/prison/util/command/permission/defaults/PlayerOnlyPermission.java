package gg.convict.prison.util.command.permission.defaults;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import gg.convict.prison.util.command.permission.PermissionAdapter;
import gg.convict.prison.util.CC;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 05.03.2021 / 09:10
 * libraries / cc.invictusgames.libraries.command.permission.defaults
 */

public class PlayerOnlyPermission extends PermissionAdapter {

    public PlayerOnlyPermission() {
        super("player");
    }

    public boolean test(CommandSender sender) {
        boolean b = testSilent(sender);
        if (!b)
            sender.sendMessage(CC.RED + "This command can only be used as a player.");
        return b;
    }

    @Override
    public boolean testSilent(CommandSender sender) {
        return sender instanceof Player;
    }
}
