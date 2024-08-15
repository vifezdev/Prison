package gg.convict.prison.pickaxe.enchant;

import gg.convict.prison.pickaxe.enchant.data.EnchantData;
import lombok.Getter;
import gg.convict.prison.util.configuration.StaticConfiguration;

import java.util.HashMap;
import java.util.Map;

@Getter
public class EnchantConfig implements StaticConfiguration {

    private final Map<String, EnchantData> enchantMap = new HashMap<>();

    public int getMaxLevel(String enchantment, int defaultLevel) {
        EnchantData data = getData(enchantment);

        return data == null
                ? defaultLevel
                : data.getMaxLevel();
    }

    public String getDescription(String enchantment) {
        EnchantData data = getData(enchantment);

        return data == null ? "N/A"
                : data.getDescription();
    }

    public EnchantData getData(String enchantment) {
        for (String s : enchantMap.keySet()) {
            if (s.equalsIgnoreCase(enchantment))
                return enchantMap.get(s);
        }

        return null;
    }

}