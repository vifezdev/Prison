package gg.convict.prison.region.command.parameter;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RegionParameter implements ParameterType<Region> {

    private final RegionModule module;

    @Override
    public Region parse(CommandSender sender, String source) {
        Region region = module.getRegion(source);

        if (region == null)
            sender.sendMessage(CC.format(
                    "&cA region with the name \"%s\" does not exist.",
                    source));

        return region;
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, List<String> list) {
        List<String> result = new ArrayList<>();

        for (Region region : module.getConfig().getRegions())
            result.add(region.getName());

        return result;
    }

}