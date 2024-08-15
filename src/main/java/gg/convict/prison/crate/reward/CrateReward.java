package gg.convict.prison.crate.reward;

import gg.convict.prison.crate.util.CrateUtil;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import gg.convict.prison.util.ItemNbtUtil;

import java.util.ArrayList;
import java.util.List;

@Data
public class CrateReward {

    private int slot = 0;
    private int priority = 1;
    private double chance = 0.0;

    private boolean commandReward = false;
    private List<String> commands = new ArrayList<>();

    private ItemStack itemStack;

    public CrateReward(ItemStack itemStack, int slot) {
        removeLore(itemStack);

        this.itemStack = itemStack;
        this.chance = ItemNbtUtil.getDouble(itemStack, "chance");
        this.slot = slot;
    }

    public void executeReward(Player player) {
        if (commandReward) {
            commands.forEach(s -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s));
            return;
        }

        player.getInventory().addItem(itemStack);
    }

    public void removeLore(ItemStack itemStack) {
        if (!itemStack.hasItemMeta())
            return;

        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        if (lore == null)
            return;

        String lastLine = lore.get(lore.size() - 1);

        if (lastLine != null && lastLine.contains("Chance")) {
            lore.remove(lore.size() - 1);
            itemMeta.setLore(lore);
        }
    }

}