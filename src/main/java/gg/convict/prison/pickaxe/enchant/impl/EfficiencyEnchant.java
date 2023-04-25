package gg.convict.prison.pickaxe.enchant.impl;

import gg.convict.prison.pickaxe.enchant.Enchant;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EfficiencyEnchant extends Enchant {

    public EfficiencyEnchant() {
        super("efficiency", "Efficiency");
    }

    @Override
    public void apply(Player player, ItemStack item, Integer level) {
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, level);
    }

}