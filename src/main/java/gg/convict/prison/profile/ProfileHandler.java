package gg.convict.prison.profile;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ProfileHandler {

    private final Map<UUID, Profile> profileMap = new HashMap<>();

    public void loadProfile(UUID uuid) {
        if (profileMap.containsKey(uuid))
            return;

        Profile profile = new Profile(uuid, true);
        profileMap.put(uuid, profile);
    }

    public Profile getProfile(Player player) {
        return getProfile(player.getUniqueId());
    }

    public Profile getProfile(UUID uuid) {
        return profileMap.get(uuid);
    }

}