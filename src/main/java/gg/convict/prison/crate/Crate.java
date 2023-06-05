package gg.convict.prison.crate;

import gg.convict.prison.crate.reward.CrateReward;
import gg.convict.prison.crate.util.IntRange;
import org.hydrapvp.libraries.utils.CC;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.configuration.defaults.SimpleLocationConfig;
import org.hydrapvp.libraries.utils.RandomCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Crate {

    private final UUID uuid;
    private String name;
    private String color = "&b";
    private Material blockType = Material.CHEST;

    private int itemsPerKey = 3;

    private boolean showChances = true;
    private boolean broadcastRewards = false;

    private Material keyMaterial = Material.TRIPWIRE_HOOK;

    private final List<String> hologramLines = new ArrayList<>();
    private final List<CrateReward> rewards = new ArrayList<>();
    private final List<SimpleLocationConfig> locations = new ArrayList<>();

    private transient IntRange rewardRange = new IntRange(1, 1);

    public Crate(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getDisplayName() {
        return CC.translate(color + name);
    }

    public ItemStack buildKey() {
        ItemStack itemStack = new ItemBuilder(keyMaterial)
                .setDisplayName(getDisplayName() + " Key")
                .addEnchantment(Enchantment.DURABILITY, 10)
                .addToLore(
                        "",
                        CC.translate("&7&oRight click a crate to"),
                        CC.translate("&7&oopen it and receive rewards.")
                ).build();

        itemStack.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return itemStack;
    }

    public boolean canOpen(Player player) {
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null
                || itemInHand.getType() != keyMaterial)
            return false;

        return itemInHand.isSimilar(buildKey())
                && itemInHand.getEnchantmentLevel(Enchantment.DURABILITY) == 10;
    }

    public void open(Player player) {
        RandomCollection<CrateReward> winnablePrizes = new RandomCollection<>();

        for (CrateReward reward : rewards) {
            if (reward.getItemStack().getType() == Material.STAINED_GLASS_PANE)
                continue;

            winnablePrizes.add(reward.getChance(), reward);
        }

        for (int i = 0; i < itemsPerKey; i++)
            winnablePrizes.next().executeReward(player);

        winnablePrizes.clear();
    }

    public int getRealRewards() {
        int amount = 0;

        for (CrateReward reward : rewards) {
            if (reward.getItemStack().getType()
                    == Material.STAINED_GLASS_PANE)
                continue;

            amount += 1;
        }

        return amount;
    }

    public boolean hasLocation(Location location) {
        for (SimpleLocationConfig locationConfig : locations)
            if (locationConfig.getX() == location.getX()
                    && locationConfig.getY() == location.getY()
                    && locationConfig.getZ() == location.getZ()
                    && locationConfig.getWorld().equals(location.getWorld().getName())) {
                return true;
            }

        return false;
    }

}