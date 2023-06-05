package gg.convict.prison.warp;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

@Data
@AllArgsConstructor
public class Warp {

    private String name;
    private Location location;

    private ItemStack displayItem;
    private ChatColor displayColor;

    public String getDisplayName() {
        return displayColor + name;
    }

    public void save() {
        WarpConfig config = WarpModule.get().getConfig();

        if (config.hasWarp(name))
            config.removeWarp(name);

        config.addWarp(this);
    }

}