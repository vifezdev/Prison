package gg.convict.prison.profile;

import com.mongodb.client.model.Filters;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.mongo.MongoModule;
import lombok.Data;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.mongo.MongoService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Data
public class ProfileHandler {

    private final Map<UUID, Profile> profileMap = new ConcurrentHashMap<>();
    private final PrisonPlugin plugin;

    public ProfileHandler(PrisonPlugin plugin) {
        this.plugin = plugin;
    }

    public Profile loadProfile(UUID uuid) {
        if (profileMap.containsKey(uuid))
            return getProfile(uuid);

        Document document = MongoModule.get().getProfiles().find(
                Filters.eq("uuid",
                        uuid.toString())).first();

        if (document == null)
            return createProfile(uuid);
        else return profileMap.getOrDefault(uuid,
                profileMap.put(uuid, new Profile(document))
        );
    }

    // used for offline player lookups for example
    public void loadProfileAsync(UUID uuid, Consumer<Profile> consumer) {
        PrisonPlugin.EXECUTOR.execute(() -> {
            if (profileMap.containsKey(uuid)) {
                consumer.accept(getProfile(uuid));
                return;
            }

            Document document = MongoModule.get().getProfiles().find(
                    Filters.eq("uuid",
                            uuid.toString())).first();

            if (document == null)
                consumer.accept(createProfile(uuid));
            else consumer.accept(profileMap.getOrDefault(uuid,
                    profileMap.put(uuid, new Profile(document)))
            );
        });
    }

    public void saveProfiles() {
        for (Profile profile : profileMap.values())
            saveProfile(profile, false);
    }

    public void saveProfile(Profile profile, boolean async) {
        if (async) {
            PrisonPlugin.EXECUTOR.execute(() -> saveProfile(profile, false));
            return;
        }

        MongoModule.get().getProfiles().replaceOne(Filters.eq("uuid",
                profile.getUuid().toString()), profile.toBson(), MongoService.REPLACE_OPTIONS);
    }

    public Profile createProfile(UUID uuid) {
        Profile profile = new Profile(uuid);
        profileMap.put(uuid, profile);
        return profile;
    }

    public Profile getProfile(UUID uuid) {
        return profileMap.get(uuid);
    }

    public Profile getProfile(Player player) {
        return getProfile(player.getUniqueId());
    }

}
