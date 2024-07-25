package gg.convict.prison.warp.command;

import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class WarpCreateCommand {

    private final WarpModule module;

    @Command(names = "warp create",
            description = "Create a new warp",
            permission = "prison.warp.create")
    public void execute(CommandSender sender, @Param(name = "name") String name) {
        Warp warp = module.getWarp(name);
        if (warp != null) {
            sender.sendMessage(CC.RED + "A warp with that name already exists.");
            return;
        }

        warp = new Warp(
                name, null,
                new ItemStack(Material.STONE),
                ChatColor.WHITE
        );

        module.getConfig().getWarps().add(warp);
        module.saveConfig();

        sender.sendMessage(CC.format(
                "&fCreated warp with the name &b%s&f.",
                warp.getName()
        ));
    }

}