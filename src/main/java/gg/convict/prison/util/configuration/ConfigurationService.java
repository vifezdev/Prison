package gg.convict.prison.util.configuration;

import java.io.File;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 12.02.2020 / 21:05
 * libraries / cc.invictusgames.libraries.configuration
 */

public interface ConfigurationService {

    void saveConfiguration(StaticConfiguration configuration, File file);

    <T extends StaticConfiguration> T loadConfiguration(Class<? extends T> clazz, File file);

}
