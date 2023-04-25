package gg.convict.prison.crate.command.parameter;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.hydrapvp.libraries.command.parameter.ParameterType;
import org.hydrapvp.libraries.utils.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CrateParameter implements ParameterType<Crate> {

    private final CrateModule module;

    @Override
    public Crate parse(CommandSender sender, String source) {
        Crate crate = module.getHandler().getCrate(source);

        if (crate == null)
            sender.sendMessage(CC.format(
                    "%s&cA crate with that name does not exist.",
                    PrisonPlugin.PREFIX
            ));

        return crate;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> list) {
        List<String> results = new ArrayList<>();

        for (Crate value : module.getHandler().getCrateMap().values())
            results.add(value.getName());

        return results;
    }

}