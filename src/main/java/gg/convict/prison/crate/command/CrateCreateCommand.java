package gg.convict.prison.crate.command;

import gg.convict.prison.config.PrisonBranding;
import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import org.hydrapvp.libraries.utils.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;

import java.util.UUID;

@RequiredArgsConstructor
public class CrateCreateCommand {

    private final CrateModule module;

    @Command(names = "crate create",
            permission = "prison.crate.create",
            description = "Create a new crate")
    public void execute(CommandSender sender, @Param(name = "name") String name) {
        Crate crate = module.getHandler().getCrate(name);

        if (crate != null) {
            sender.sendMessage(CC.RED + "A crate with that name already exists.");
            return;
        }

        module.getHandler().addCrate(
                new Crate(UUID.randomUUID(), name));

        sender.sendMessage(CC.format(
                "%sCreated crate with name &b%s&f.",
                PrisonBranding.get().getPrefix(), name
        ));
    }

}