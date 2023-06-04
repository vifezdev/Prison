package gg.convict.prison.pickaxe.enchant;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.pickaxe.PickaxeModule;
import gg.convict.prison.pickaxe.enchant.data.EnchantData;
import gg.convict.prison.pickaxe.enchant.impl.*;
import gg.convict.prison.pickaxe.enchant.impl.minecrate.CrateFinderEnchant;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.*;

@Getter
public class EnchantHandler {

    private final Map<String, Enchant> enchants = new HashMap<>();

    public EnchantHandler() {
        registerEnchants(
                new FortuneEnchant(),
                new JumpBoostEnchant(),
                new UnbreakingEnchant(),
                new EfficiencyEnchant(),
                new CrateFinderEnchant()
        );

        PickaxeModule.get().saveEnchantConfig();
    }

    public void registerEnchants(Enchant... enchants) {
        for (Enchant enchant : enchants) {
            String lowerCaseId = enchant.getId().toLowerCase();
            this.enchants.put(lowerCaseId, enchant);

            System.out.println("[Prison] [EnchantHandler] Registered enchant "
                    + enchant.getId() + " with max level " + enchant.getMaxLevel());

            Map<String, EnchantData> enchantMap = PickaxeModule.get().getEnchantConfig().getEnchantMap();

            if (!enchantMap.containsKey(lowerCaseId))
                enchantMap.put(lowerCaseId, new EnchantData(1, "Description"));

            Bukkit.getPluginManager().registerEvents(enchant, PrisonPlugin.get());
        }
    }

    public Enchant getEnchant(String id) {
        return enchants.get(id.toLowerCase());
    }

}