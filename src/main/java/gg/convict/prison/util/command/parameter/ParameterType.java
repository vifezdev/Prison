package gg.convict.prison.util.command.parameter;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 15.01.2020 / 22:20
 * libraries / cc.invictusgames.libraries.commandapi.parameter
 */

public interface ParameterType<T> {

    T parse(CommandSender sender, String source);

    List<String> tabComplete(CommandSender sender, List<String> flags);

}
