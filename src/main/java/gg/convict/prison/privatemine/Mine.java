package gg.convict.prison.privatemine;

import lombok.Data;
import org.hydrapvp.libraries.configuration.defaults.LocationConfig;
import org.hydrapvp.libraries.cuboid.Cuboid;

import java.util.UUID;

@Data
public class Mine {

    private UUID owner;
    private Cuboid cuboid;
    private LocationConfig spawnLocation;

}