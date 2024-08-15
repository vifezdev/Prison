package gg.convict.prison.warp.command;

import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class WarpDeleteCommand {

    private final WarpModule module;

    @Command(names = "warp delete",
            description = "delete a warp",
            permission = "prison.warp.delete")
    public void execute(CommandSender sender, @Param(name = "warp") Warp warp) {
        module.getConfig().removeWarp(warp.getName());
        module.saveConfig();

        sender.sendMessage(CC.format(
                "&fDeleted warp with the name &b%s&f.",
                warp.getDisplayName()
        ));
    }

}