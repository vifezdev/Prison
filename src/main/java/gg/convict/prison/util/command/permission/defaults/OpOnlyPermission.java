package gg.convict.prison.util.command.permission.defaults;

import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.permission.PermissionAdapter;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 05.03.2021 / 09:10
 * libraries / cc.invictusgames.libraries.command.permission.defaults
 */

public class OpOnlyPermission extends PermissionAdapter {

    public OpOnlyPermission() {
        super("op");
    }

    @Override
    public boolean testSilent(CommandSender sender) {
        return sender.isOp();
    }
}
