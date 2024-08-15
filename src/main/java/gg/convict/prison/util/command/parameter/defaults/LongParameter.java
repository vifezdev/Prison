package gg.convict.prison.util.command.parameter.defaults;

import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 15.01.2020 / 22:49
 * libraries / cc.invictusgames.libraries.commandapi.parameter.defaults
 */

public class LongParameter implements ParameterType<Long> {

    @Override
    public Long parse(CommandSender sender, String source) {
        Long value;
        try {
            value = Long.parseLong(source);
        } catch (NumberFormatException e) {
            sender.sendMessage(CC.YELLOW + source + CC.RED + " is not a valid number.");
            return null;
        }
        return value;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        return new ArrayList<>();
    }
}
