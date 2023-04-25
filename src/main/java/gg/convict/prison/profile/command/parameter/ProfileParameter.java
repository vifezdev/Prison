package gg.convict.prison.profile.command.parameter;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileHandler;
import gg.convict.prison.profile.ProfileModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.parameter.ParameterType;
import org.hydrapvp.libraries.command.parameter.defaults.PlayerParameter;
import org.hydrapvp.libraries.utils.CC;
import org.hydrapvp.libraries.utils.UUIDUtils;
import org.hydrapvp.libraries.visibility.VisibilityService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProfileParameter implements ParameterType<Profile> {

    private final ProfileModule module;

    @Override
    public Profile parse(CommandSender sender, String source) {
        ProfileHandler profileHandler = module.getProfileHandler();
        if (source.equals("@self") && sender instanceof Player)
            return profileHandler
                    .getProfile(((Player) sender).getUniqueId());

        Player player = UUIDUtils.isUUID(source)
                ? Bukkit.getPlayer(UUID.fromString(source))
                : Bukkit.getPlayer(source);

        if (player != null && VisibilityService.getOnlineTreatProvider().apply(player, sender))
            return profileHandler.getProfile(player);

        sender.sendMessage(CC.RED + "Player " + CC.YELLOW + source + CC.RED + " is not online.");
        return null;
    }

    @Override
    public List<String> tabComplete(CommandSender commandSender, List<String> list) {
        return PlayerParameter.TAB_COMPLETE_FUNCTION.apply(commandSender, list);
    }

}