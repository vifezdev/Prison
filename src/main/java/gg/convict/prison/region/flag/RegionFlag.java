package gg.convict.prison.region.flag;

import gg.convict.prison.region.RegionModule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;

@Getter
@RequiredArgsConstructor
public enum RegionFlag {

    CAN_DAMAGE(Material.DIAMOND_SWORD),
    CAN_BREAK(Material.DIAMOND_PICKAXE),
    CAN_PLACE(Material.STONE);

    private final Material material;

    public boolean isApplicableAt(Location location) {
        return RegionModule.get()
                .fromLocation(location)
                .getFlags().contains(this);
    }

    public static RegionFlag getFlag(String input) {
        for (RegionFlag value : values()) {
            if (value.name().equalsIgnoreCase(input))
                return value;
        }

        return null;
    }

}