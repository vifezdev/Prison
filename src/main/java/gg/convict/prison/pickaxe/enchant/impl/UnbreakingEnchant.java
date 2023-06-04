package gg.convict.prison.pickaxe.enchant.impl;

import gg.convict.prison.pickaxe.enchant.Enchant;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UnbreakingEnchant extends Enchant {

    public UnbreakingEnchant() {
        super("unbreaking", "Unbreaking");
    }

    @Override
    public void apply(Player player, ItemStack item, Integer level) {
        item.addUnsafeEnchantment(Enchantment.DURABILITY, level);
    }

}