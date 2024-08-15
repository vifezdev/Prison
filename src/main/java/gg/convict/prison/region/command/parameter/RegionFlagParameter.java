package gg.convict.prison.region.command.parameter;

import gg.convict.prison.region.RegionModule;
import gg.convict.prison.region.flag.RegionFlag;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RegionFlagParameter implements ParameterType<RegionFlag> {

    private final RegionModule module;

    @Override
    public RegionFlag parse(CommandSender sender, String source) {
        RegionFlag region = RegionFlag.getFlag(source);

        if (region == null)
            sender.sendMessage(CC.format(
                    "&cA flag with the name \"%s\" does not exist.",
                    source));

        return region;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> list) {
        List<String> result = new ArrayList<>();

        for (RegionFlag regionFlag : RegionFlag.values())
            result.add(regionFlag.name());

        return result;
    }

}