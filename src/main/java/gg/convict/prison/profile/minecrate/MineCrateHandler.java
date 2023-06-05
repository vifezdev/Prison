package gg.convict.prison.profile.minecrate;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class MineCrateHandler {

    private final PrisonPlugin plugin;

    public void give(Player player, int amount) {
        Profile profile = ProfileModule.get()
                .getProfileHandler().getProfile(player);

        if (profile == null)
            return;

        profile.addMineCrates(amount);
    }

}