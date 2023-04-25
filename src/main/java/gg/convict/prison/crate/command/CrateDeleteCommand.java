package gg.convict.prison.crate.command;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import lol.vera.veraspigot.util.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;

@RequiredArgsConstructor
public class CrateDeleteCommand {

    private final CrateModule module;

    @Command(names = "crate delete",
            permission = "prison.crate.delete",
            description = "Delete a crate")
    public void execute(CommandSender sender, @Param(name = "crate") Crate crate) {
        module.getHandler().getCrateMap().remove(crate.getName().toLowerCase());
        module.saveConfig();

        sender.sendMessage(CC.format(
                "%sDeleted crate with name &b%s&f.",
                PrisonPlugin.PREFIX, crate.getName()
        ));
    }

}