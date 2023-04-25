package gg.convict.prison.region.config;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import lombok.Getter;
import org.hydrapvp.libraries.configuration.StaticConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class RegionConfig implements StaticConfiguration {

    private final List<Region> regions = new ArrayList<>();

    public void addRegion(Region region) {
        regions.add(region);
        RegionModule.get().saveConfig();
    }

    public boolean hasRegion(UUID uuid) {
        for (Region region : regions) {
            if (region.getUuid().equals(uuid))
                return true;
        }

        return false;
    }

    public void removeRegion(UUID uuid) {
        regions.removeIf(region -> region.getUuid().equals(uuid));
    }

}