package gg.convict.prison.util.playersetting;

import java.util.List;

public interface PlayerSettingProvider {

    List<PlayerSetting> getProvidedSettings();

    int getPriority();

}
