package gg.convict.prison.warp.command.parameter;

import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.hydrapvp.libraries.command.parameter.ParameterType;
import org.hydrapvp.libraries.utils.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class WarpParameter implements ParameterType<Warp> {

    private final WarpModule module;

    @Override
    public Warp parse(CommandSender sender, String source) {
        Warp warp = module.getWarp(source);

        if (warp == null)
            sender.sendMessage(CC.format(
                    "&cA warp with the name \"%s\" does not exist.",
                    source));

        return warp;
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, List<String> list) {
        List<String> result = new ArrayList<>();

        for (Warp warp : module.getConfig().getWarps())
            result.add(warp.getName());

        return result;
    }

}