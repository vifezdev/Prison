package gg.convict.prison.pickaxe;

import gg.convict.prison.pickaxe.enchant.Enchant;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hydrapvp.libraries.utils.CC;
import org.hydrapvp.libraries.utils.ItemNbtUtil;

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

    public Map<Enchant, Integer> getEnchantments() {
        Map<Enchant, Integer> enchants = new HashMap<>();

        this.enchants.forEach((key, value) -> {
            Enchant enchant = PickaxeModule.get().getEnchantHandler().getEnchant(key);

            if (enchant != null)
                enchants.put(enchant, value);
        });

        return enchants;
    }

}