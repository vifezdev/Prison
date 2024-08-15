package gg.convict.prison.util.command.parameter.defaults;

import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffectType;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

public class PotionEffectTypeParameter implements ParameterType<PotionEffectType> {

    @Override
    public PotionEffectType parse(CommandSender sender, String source) {
        PotionEffectType type = PotionEffectType.getByName(source);

        if (type == null)
            sender.sendMessage(CC.format("&cPotion effect &e%s &cnot found.", source));

        return type;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> flags) {
        List<String> completions = new ArrayList<>();
        for (PotionEffectType type : PotionEffectType.values()) {
            if (type == null || type.getName() == null)
                continue;

            completions.add(type.getName());
        }
        return completions;
    }
}
