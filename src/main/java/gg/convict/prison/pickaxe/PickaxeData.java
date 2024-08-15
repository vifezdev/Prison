package gg.convict.prison.pickaxe;

import gg.convict.prison.pickaxe.enchant.Enchant;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import gg.convict.prison.util.CC;
import gg.convict.prison.util.ItemNbtUtil;

import java.util.*;

@Getter
@Setter
public class PickaxeData {

    private int blocksMined = 0;
    private final Map<String, Integer> enchants = new HashMap<>();

    public void setup(Player player, ItemStack itemStack) {
        ItemNbtUtil.set(itemStack, "ToolUUID",
                UUID.randomUUID().toString());

        applyItemMeta(player, itemStack);
    }

    public boolean hasEnchant(String enchantId) {
        return enchants.containsKey(enchantId.toLowerCase());
    }

    public int getEnchantLevel(String enchantId) {
        return enchants.getOrDefault(enchantId.toLowerCase(), 0);
    }

    public void incrementBlocksMined(int amount) {
        this.blocksMined += amount;
    }

    public void applyItemMeta(Player player, ItemStack itemStack) {
        List<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(CC.AQUA + CC.BOLD + "Enchants");

        if (enchants.size() == 0) {
            lore.add(CC.RED + "No enchants applied.");
        } else {
            getEnchantments().forEach((enchant, level) -> lore.add(CC.format(
                    "&b&l❙ &f%s: &b%s",
                    enchant.getName(), level
            )));
        }

        lore.add("");
        lore.add(CC.AQUA + CC.BOLD + "Statistics");
        lore.add(CC.format(
                "&fBlocks Mined: &b%s",
                blocksMined
        ));

        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta == null)
            return;

        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.spigot().setUnbreakable(true);
        itemMeta.setDisplayName(CC.format(
                "&b%s's &fDiamond Pickaxe",
                player.getName()
        ));

        itemStack.setItemMeta(itemMeta);
        player.updateInventory();
    }

    public void applyEnchant(Player player, ItemStack itemStack, Enchant enchant, int level) {
        enchants.put(enchant.getId().toLowerCase(), level);

        applyItemMeta(player, itemStack);
        enchant.apply(player, itemStack, level);
    }

    public Map<Enchant, Integer> getEnchantments() {
        Map<Enchant, Integer> result = new HashMap<>();

        this.enchants.forEach((key, value) -> {
            Enchant enchant = PickaxeModule.get().getEnchantHandler().getEnchant(key);

            if (enchant != null)
                result.put(enchant, value);
        });

        return result;
    }

}