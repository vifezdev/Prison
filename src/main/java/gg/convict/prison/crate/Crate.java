package gg.convict.prison.crate;

import gg.convict.prison.config.LocationConfig;
import gg.convict.prison.crate.reward.CrateReward;
import gg.convict.prison.crate.util.CrateUtil;
import gg.convict.prison.crate.util.IntRange;
import lol.vera.veraspigot.util.CC;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
    private final List<LocationConfig> locations = new ArrayList<>();

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

        List<CrateReward> winnablePrizes = new ArrayList<>();
        double chance = Double.parseDouble(CrateModule.REWARD_FORMAT.format(
                0.0 + rewardRange.getLast() * ThreadLocalRandom.current().nextDouble()));

        for (CrateReward reward : rewards) {
            if (reward.getChance() > chance
                    || reward.getItemStack().getType() == Material.STAINED_GLASS_PANE)
                continue;

            winnablePrizes.add(reward);
        }

        for (int i = 0; i < itemsPerKey; i++)
            CrateUtil.getRandom(winnablePrizes).executeReward(player);

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
        for (LocationConfig locationConfig : locations) {
            if (locationConfig.matches(location))
                return true;
        }

        return false;
    }

}