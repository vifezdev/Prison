package gg.convict.prison.crate;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.hydrapvp.libraries.configuration.StaticConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class CrateHandler implements StaticConfiguration {

    private final Map<String, Crate> crateMap = new HashMap<>();

    public Crate getCrate(String name) {
        return crateMap.get(name.toLowerCase());
    }

    public Crate getCrate(Location location) {
        for (Crate crate : crateMap.values()) {
            if (crate.hasLocation(location))
                return crate;
        }

        return null;
    }

    public Crate getCrate(UUID uuid) {
        for (Crate value : crateMap.values()) {
            if (value.getUuid().equals(uuid))
                return value;
        }

        return null;
    }

    public void addCrate(Crate crate) {
        crateMap.put(crate.getName().toLowerCase(), crate);
    }

}