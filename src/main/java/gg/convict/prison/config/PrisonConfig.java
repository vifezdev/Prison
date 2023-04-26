package gg.convict.prison.config;

import gg.convict.prison.PrisonPlugin;
import lombok.Getter;
import lombok.Setter;
import org.hydrapvp.libraries.configuration.StaticConfiguration;
import org.hydrapvp.libraries.configuration.defaults.LocationConfig;
import org.hydrapvp.libraries.configuration.defaults.MongoConfig;

import java.io.File;

@Getter @Setter
public class PrisonConfig implements StaticConfiguration {

    private LocationConfig spawnLocation = null;
    private final MongoConfig mongoConfig = new MongoConfig();

    public static File getFile() {
        return new File(PrisonPlugin.get().getDataFolder(), "config.json");
    }

}