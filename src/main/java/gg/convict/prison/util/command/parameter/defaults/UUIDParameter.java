package gg.convict.prison.util.command.parameter.defaults;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.util.uuid.UUIDCache;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;
import gg.convict.prison.util.UUIDUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 03.06.2020 / 11:49
 * libraries / cc.invictusgames.libraries.commandapi.parameter.defaults
 */

public class UUIDParameter implements ParameterType<UUID> {

    @Override
    public UUID parse(CommandSender sender, String source) {
        if ((source.equals("@self")) && (sender instanceof Player)) {
            return ((Player) sender).getUniqueId();
        }

        if (UUIDUtils.isUUID(source)) {
            return UUID.fromString(source);
        }

        PrisonPlugin.get();
        UUID uuid = UUIDCache.getUuid(source);
        if (uuid == null) {
            sender.sendMessage(CC.YELLOW + source + CC.RED + " has never joined the server.");
            return null;
        }
        return uuid;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        return PlayerParameter.TAB_COMPLETE_FUNCTION.apply(sender, flags);
    }
}
