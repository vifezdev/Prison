package gg.convict.prison.pickaxe.enchant;

import gg.convict.prison.pickaxe.PickaxeModule;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Data
public abstract class Enchant {

    private final String id;
    private final String name;

    public int getMaxLevel() {
        return PickaxeModule.get().getEnchantConfig()
                .getMaxLevel(id, 1);
    }

    public void apply(Player player, ItemStack item, Integer level) {
    }

    public void remove(Player player, ItemStack item, Integer level) {
    }

}