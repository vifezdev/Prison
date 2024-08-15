package gg.convict.prison.region;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.region.claimwand.ClaimWandHandler;
import gg.convict.prison.region.claimwand.listener.ClaimWandListener;
import gg.convict.prison.region.command.*;
import gg.convict.prison.region.command.parameter.RegionFlagParameter;
import gg.convict.prison.region.command.parameter.RegionParameter;
import gg.convict.prison.region.config.RegionConfig;
import gg.convict.prison.region.flag.RegionFlag;
import gg.convict.prison.region.impl.DefaultRegion;
import gg.convict.prison.region.listener.RegionListeners;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.plugin.PluginModule;

import java.util.List;
import java.util.Map;

@Getter
public class RegionModule extends PluginModule {

    public static final DefaultRegion DEFAULT_REGION
            = new DefaultRegion();

    private final ClaimWandHandler claimWandHandler = new ClaimWandHandler();

    public RegionModule() {
        super("regions", PrisonPlugin.get(), new RegionConfig());
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new RegionFlagCommands(this),
                new RegionColorCommand(this),
                new RegionClaimCommand(this),
                new RegionDebugCommand(this),
                new RegionDeleteCommand(this),
                new RegionCreateCommand(this)
        );
    }

    @Override
    public Map<Class<?>, ParameterType<?>> getParameterTypes() {
        return ImmutableMap.of(
                Region.class, new RegionParameter(this),
                RegionFlag.class, new RegionFlagParameter(this));
    }

    @Override
    public List<Listener> getListeners() {
        return ImmutableList.of(
                new RegionListeners(),
                new ClaimWandListener(claimWandHandler));
    }

    public Region fromLocation(Location location) {
        for (Region region : getConfig().getRegions()) {
            if (region.getCuboid() != null
                    && region.getCuboid().contains(location))
                return region;
        }

        return DEFAULT_REGION;
    }

    public Region getRegion(String name) {
        for (Region region : getConfig().getRegions()) {
            if (region.getName().equalsIgnoreCase(name))
                return region;
        }

        return null;
    }

    public void deleteRegion(Region region) {
        getConfig().removeRegion(region.getUuid());
    }

    public RegionConfig getConfig() {
        return (RegionConfig) super.getConfig();
    }

    public static RegionModule get() {
        return PluginModule.getModule(RegionModule.class);
    }

}