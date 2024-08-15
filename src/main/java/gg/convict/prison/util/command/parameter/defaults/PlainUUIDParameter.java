package gg.convict.prison.util.command.parameter.defaults;

import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;
import gg.convict.prison.util.UUIDUtils;
import gg.convict.prison.util.uuid.PlainUUID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlainUUIDParameter implements ParameterType<PlainUUID> {

    @Override
    public PlainUUID parse(CommandSender sender, String source) {
        if (UUIDUtils.isUUID(source))
            return new PlainUUID(UUID.fromString(source));

        sender.sendMessage(CC.RED + "That is not a valid UUID.");
        return null;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        return new ArrayList<>();
    }

}