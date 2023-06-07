package gg.convict.prison.config;

import gg.convict.prison.PrisonPlugin;
import lombok.Data;

@Data
public class PrisonBranding {

    private final String prefix = "&b&lConvict &7● &f";

    public static PrisonBranding get() {
        return PrisonPlugin.get().getPrisonConfig().getBranding();
    }

}