package gg.convict.prison.util.configuration;

import com.google.gson.Gson;
import gg.convict.prison.PrisonPlugin;
import lombok.Setter;
import gg.convict.prison.util.Statics;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 12.02.2020 / 21:05
 * libraries / cc.invictusgames.libraries.configuration
 */

public class JsonConfigurationService implements ConfigurationService {

    @Setter
    public static Gson gson = Statics.GSON;

    @Override
    public void saveConfiguration(StaticConfiguration configuration, File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);

            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

            gson.toJson(configuration, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            PrisonPlugin.get().getLogger().warning("Failed to save configuration " + configuration.getClass().getName() + " to file " + file.getName());
        }
    }

    @Override
    public <T extends StaticConfiguration> T loadConfiguration(Class<? extends T> clazz, File file) {
        if ((!file.getParentFile().exists()) && (!file.getParentFile().mkdir())) {
            PrisonPlugin.get().getLogger().warning("Failed to create parent folder for " + file.getName());
            return null;
        }
        try {
            T config = clazz.newInstance();
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    PrisonPlugin.get().getLogger().warning("Failed to create file for " + file.getName());
                    return null;
                }
                saveConfiguration(config, file);
            }
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }

        try {
            T config = gson.fromJson(new BufferedReader(new FileReader(file)), clazz);
            saveConfiguration(config, file);
            return config;
        } catch (FileNotFoundException e) {
            PrisonPlugin.get().getLogger().warning("Failed to load configuration " + clazz.getName() + " from file " + file.getName());
            return null;
        }
    }
}
