package gg.convict.prison.region.command;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import gg.convict.prison.region.flag.RegionFlag;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class RegionFlagCommands {

    private final RegionModule module;

    @Command(names = "region flag add",
            permission = "prison.region.flag",
            description = "Add a flag to a region")
    public void execute(CommandSender sender,
                        @Param(name = "region") Region region,
                        @Param(name = "flag") RegionFlag flag) {

        if (region.hasFlag(flag)) {
            sender.sendMessage(CC.format(
                    "%s &calready has the &b%s&c flag.",
                    region.getDisplayName(),
                    flag.name()));
            return;
        }

        region.addFlags(flag);
        region.save();

        sender.sendMessage(CC.format(
                "&fAdded flag &b%s&f to &b%s&f.",
                flag.name(),
                region.getDisplayName()));
    }

    @Command(names = "region flag remove",
            permission = "prison.region.flag",
            description = "Remove a flag from a region")
    public void remove(CommandSender sender,
                       @Param(name = "region") Region region,
                       @Param(name = "flag") RegionFlag flag) {

        if (!region.hasFlag(flag)) {
            sender.sendMessage(CC.format(
                    "%s &cdoes not have the &b%s&c flag.",
                    region.getDisplayName(),
                    flag.name()));
            return;
        }

        region.removeFlags(flag);
        region.save();

        sender.sendMessage(CC.format(
                "&fRemoved flag &b%s&f from &b%s&f.",
                flag.name(),
                region.getDisplayName()));
    }

}