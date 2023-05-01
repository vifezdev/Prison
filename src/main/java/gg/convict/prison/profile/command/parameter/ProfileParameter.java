package gg.convict.prison.profile.command.parameter;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.parameter.ParameterType;
import org.hydrapvp.libraries.command.parameter.defaults.PlayerParameter;
import org.hydrapvp.libraries.utils.CC;
import org.hydrapvp.libraries.uuid.UUIDCache;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProfileParameter implements ParameterType<Profile> {

    @Override
    public Profile parse(CommandSender commandSender, String source) {
        if (Bukkit.isPrimaryThread()) {
            commandSender.sendMessage(CC.RED + "Cannot use ProfileParameter on the primary thread. " +
                    "Please inform server administration to mark issued command as async.");
            return null;
        }

        if (source.equals("@self") && commandSender instanceof Player)
            return ProfileModule.get().getProfileHandler().getProfile(((Player) commandSender).getUniqueId());

        if (Bukkit.getPlayer(source) != null)
            return ProfileModule.get().getProfileHandler().getProfile(Bukkit.getPlayer(source).getUniqueId());

        UUID uuid = UUIDCache.getUuid(source);
        if (uuid == null) {
            commandSender.sendMessage(CC.RED + "This player does not exist.");
            return null;
        }

        Profile profile = ProfileModule.get().getProfileHandler().getProfile(uuid);
        if (profile != null)
            return profile;

        profile = ProfileModule.get().getProfileHandler().loadProfile(uuid);
        if (profile == null)
            commandSender.sendMessage(CC.RED + "This p<layer does not exist.");

        return profile;
    }


    @Override
    public List<String> tabComplete(CommandSender commandSender, List<String> list) {
        return PlayerParameter.TAB_COMPLETE_FUNCTION.apply(commandSender, list);
    }
}
