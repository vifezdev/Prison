package gg.convict.prison.pickaxe.enchant.impl;

import gg.convict.prison.pickaxe.enchant.Enchant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JumpBoostEnchant extends Enchant {

    public JumpBoostEnchant() {
        super("jump_boost", "Jump Boost");
    }

    @Override
    public void apply(Player player, ItemStack item, Integer level) {
        player.addPotionEffect(new PotionEffect(
                PotionEffectType.JUMP,
                Integer.MAX_VALUE,
                level - 1
        ));
    }

    @Override
    public void remove(Player player, ItemStack item, Integer level) {
        player.removePotionEffect(PotionEffectType.JUMP);
    }

}