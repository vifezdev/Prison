package gg.convict.prison.pickaxe.enchant.impl;

import gg.convict.prison.pickaxe.enchant.Enchant;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FortuneEnchant extends Enchant {

    public FortuneEnchant() {
        super("fortune", "Fortune");
    }

    @Override
    public void apply(Player player, ItemStack item, Integer level) {
        item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, level);
    }

}