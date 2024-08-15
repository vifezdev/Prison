package gg.convict.prison.util.command.parameter.defaults;

import gg.convict.prison.util.EnchantmentWrapper;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 03.06.2020 / 18:55
 * libraries / cc.invictusgames.libraries.commandapi.parameter.defaults
 */

public class EnchantmentParameter implements ParameterType<Enchantment> {

    @Override
    public Enchantment parse(CommandSender sender, String source) {
        EnchantmentWrapper enchantment = EnchantmentWrapper.fromString(source);
        if (enchantment == null) {
            sender.sendMessage(CC.RED + "Enchantment " + CC.YELLOW + source + CC.RED + " not found.");
            return null;
        }
        return enchantment.toBukkitEnchant();
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        List<String> completions = new ArrayList<>();
        for (EnchantmentWrapper value : EnchantmentWrapper.values()) {
            completions.add(value.name().toLowerCase());
        }
        return completions;
    }
}
