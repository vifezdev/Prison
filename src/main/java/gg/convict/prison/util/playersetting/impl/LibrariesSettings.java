package gg.convict.prison.util.playersetting.impl;

import gg.convict.prison.util.CC;
import gg.convict.prison.util.playersetting.PlayerSetting;
import gg.convict.prison.util.playersetting.PlayerSettingProvider;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;

public class LibrariesSettings implements PlayerSettingProvider {

    public static final BooleanSetting GLOBAL_CHAT = new BooleanSetting("libraries", "global_chat") {
        @Override
        public String getDisplayName() {
            return "Global Chat";
        }

        @Override
        public String getEnabledText() {
            return "Global chat is shown";
        }

        @Override
        public String getDisabledText() {
            return "Global chat is hidden";
        }

        @Override
        public List<String> getDescription() {
            return Arrays.asList(
                    CC.YELLOW + "If enabled, you will see messages",
                    CC.YELLOW + "sent in the global chat channel."
            );
        }

        @Override
        public MaterialData getMaterial() {
            return new MaterialData(Material.BOOK_AND_QUILL);
        }

        @Override
        public Boolean getDefaultValue() {
            return true;
        }
    };

    @Override
    public List<PlayerSetting> getProvidedSettings() {
        return Arrays.asList(
                GLOBAL_CHAT
        );
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
