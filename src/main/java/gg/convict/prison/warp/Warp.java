package gg.convict.prison.warp;

import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

@Data
public class Warp {

    private String name;
    private Location location;

    private ItemStack displayItem;
    private ChatColor displayColor = ChatColor.WHITE;

    public String getDisplayName() {
        return displayColor + name;
    }

}