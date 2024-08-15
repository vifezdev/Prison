package gg.convict.prison.util.command.parameter.defaults;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;
import gg.convict.prison.util.ItemUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 03.06.2020 / 18:47
 * libraries / cc.invictusgames.libraries.commandapi.parameter.defaults
 */

public class ItemStackParameter implements ParameterType<ItemStack> {

    @Override
    public ItemStack parse(CommandSender sender, String source) {
        ItemStack item = ItemUtils.get(source);
        if (item == null) {
            sender.sendMessage(CC.RED + "Item " + CC.YELLOW + source + CC.RED + " not found.");
            return null;
        }
        return item;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        List<String> completions = new ArrayList<>();
        for (Material value : Material.values()) {
            completions.add(value.name().toLowerCase());
        }
        return completions;
    }
}
