package gg.convict.prison.util.command.parameter.defaults;

import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 15.01.2020 / 22:41
 * libraries / cc.invictusgames.libraries.commandapi.parameter.defaults
 */

public class StringParameter implements ParameterType<String> {

    @Override
    public String parse(CommandSender sender, String source) {
        return source;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        return new ArrayList<>();
    }
}
