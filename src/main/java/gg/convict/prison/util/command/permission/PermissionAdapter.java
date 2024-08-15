package gg.convict.prison.util.command.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.CommandService;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 05.03.2021 / 09:04
 * libraries / cc.invictusgames.libraries.command.permission
 */

@RequiredArgsConstructor
@Getter
public abstract class PermissionAdapter {

    private final String permission;

    public boolean test(CommandSender sender) {
        boolean b = testSilent(sender);
        if (!b)
            sender.sendMessage(CommandService.NO_PERMISSION_MESSAGE);
        return b;
    }

    public abstract boolean testSilent(CommandSender sender);

}
