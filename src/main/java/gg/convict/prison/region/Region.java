package gg.convict.prison.region;

import gg.convict.prison.region.config.RegionConfig;
import gg.convict.prison.region.flag.RegionFlag;
import lombok.Data;
import org.bukkit.ChatColor;
import org.hydrapvp.libraries.cuboid.Cuboid;

import java.util.*;

@Data
public class Region {

    private final UUID uuid;
    private String name;
    private Cuboid cuboid;
    private ChatColor color;
    private final List<RegionFlag> flags = new ArrayList<>();

    public Region(UUID uuid, String name, Cuboid cuboid, ChatColor color) {
        this.uuid = uuid;
        this.name = name;
        this.cuboid = cuboid;
        this.color = color;
    }

    public String getDisplayName() {
        return color + name;
    }

    public void addFlags(RegionFlag... flags) {
        this.flags.addAll(Arrays.asList(flags));
    }

    public boolean hasFlag(RegionFlag regionFlag) {
        return flags.contains(regionFlag);
    }

    public void save() {
        RegionConfig config = RegionModule.get().getConfig();

        if (config.hasRegion(uuid))
            config.removeRegion(uuid);

        config.addRegion(this);
    }

    public void removeFlags(RegionFlag... flags) {
        this.flags.removeAll(Arrays.asList(flags));
    }

}