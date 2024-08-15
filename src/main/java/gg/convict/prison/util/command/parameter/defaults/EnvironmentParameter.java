package gg.convict.prison.util.command.parameter.defaults;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 15.01.2020 / 22:43
 * libraries / cc.invictusgames.libraries.commandapi.parameter.defaults
 */

public class EnvironmentParameter implements ParameterType<World.Environment> {

    @Override
    public World.Environment parse(CommandSender sender, String source) {
        World.Environment environment;
        try {
            environment = World.Environment.valueOf(source);

        } catch (IllegalArgumentException exception) {
            sender.sendMessage(CC.RED + "Environment " + CC.YELLOW + source + CC.RED + " not found.");
            return null;
        }

        return environment;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        List<String> completions = new ArrayList<>();

        for (World.Environment value : World.Environment.values())
            completions.add(value.name());

        return completions;
    }
}
