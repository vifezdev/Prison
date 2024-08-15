package gg.convict.prison.privatemine.grid;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;

@Getter
public enum SchematicType {

    NATURE(
            Material.GRASS,
            ChatColor.GREEN,
            "Nature"
    ),
    DESERT(
            Material.SAND,
            ChatColor.YELLOW,
            "Desert"
    ),
    VILLAGE(
            Material.WORKBENCH,
            ChatColor.DARK_RED,
            "Village"
    ),
    FRACTURED_HILLS(
            Material.LEAVES,
            ChatColor.BLUE,
            "Fractured Hills"
    ),
    ORIENTAL(
            new ItemBuilder(Material.WOOL).setData(6).build(),
            ChatColor.LIGHT_PURPLE,
            "Oriental"
    ),
    TAIGA(
            Material.COAL_ORE,
            ChatColor.DARK_GREEN,
            "Taiga"
    ),
    MUSHROOM(
            Material.HUGE_MUSHROOM_2,
            ChatColor.RED,
            "Mushroom"
    ),
    MESA(
            new ItemBuilder(Material.STAINED_CLAY).setData(6).build(),
            ChatColor.GOLD,
            "Mesa"
    ),
    WINTER(
            Material.SNOW_BLOCK,
            ChatColor.WHITE,
            "Winter"
    );

    private final ItemStack itemStack;
    private final ChatColor color;
    private final String textName;

    SchematicType(ItemStack itemStack, ChatColor color, String textName) {
        this.itemStack = itemStack;
        this.color = color;
        this.textName = textName;
    }

    SchematicType(Material material, ChatColor color, String textName) {
        this(new ItemStack(material), color, textName);
    }

    public String getFileName() {
        return name().toLowerCase() + "_mine.schematic";
    }

}