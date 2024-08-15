package gg.convict.prison.util.uuid;

import gg.convict.prison.PrisonPlugin;
import lombok.Getter;
import gg.convict.prison.util.redis.RedisService;
import gg.convict.prison.util.uuid.packets.UUIDUpdatePacket;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class UUIDCache {

    private final Map<String, UUID> nameUuidMap = new HashMap<>();
    private final Map<UUID, String> uuidNameMap = new HashMap<>();

    private final RedisService redisService;

    @Getter
    private static boolean initialized = false;
    public UUIDCache(RedisService redisService) {
        if (initialized)
            throw new IllegalStateException("UUIDCache has already been initialized");

        this.redisService = redisService;

        this.redisService.executeCommand(redis -> {
            for (Map.Entry<String, String> entry : redis.hgetAll("UUIDCache").entrySet()) {
                UUID uuid = UUID.fromString(entry.getKey());
                String name = entry.getValue();

                nameUuidMap.put(name.toLowerCase(), uuid);
                uuidNameMap.put(uuid, name);
            }
            return null;
        });

        initialized = true;
    }

    public static UUID getUuid(String name) {
        return PrisonPlugin.get().getUuidCache().nameUuidMap.get(name.toLowerCase());
    }

    public static UUID uuid(String name) {
        return PrisonPlugin.get().getUuidCache().nameUuidMap.get(name.toLowerCase());
    }

    public static String getName(UUID uuid) {
        return PrisonPlugin.get().getUuidCache().uuidNameMap.get(uuid);
    }

    public static String name(UUID uuid) {
        return PrisonPlugin.get().getUuidCache().uuidNameMap.get(uuid);
    }

    public void update(UUID uuid, String name, boolean async) {
        String oldName = uuidNameMap.getOrDefault(uuid, null);
        if (oldName != null)
            nameUuidMap.remove(oldName.toLowerCase());

        nameUuidMap.put(name.toLowerCase(), uuid);
        uuidNameMap.put(uuid, name);

        Runnable runnable = () ->
                this.redisService.executeBackendCommand(redis -> {
                    redis.hset("UUIDCache", uuid.toString(), name);
                    return null;
                });

        if (async)
            PrisonPlugin.EXECUTOR.execute(runnable);
        else runnable.run();

        PrisonPlugin.get().getRedisService().publish(new UUIDUpdatePacket(uuid, oldName, name));
    }

    public void updateLocally(UUID uuid, String oldName, String newName) {
        nameUuidMap.put(newName.toLowerCase(), uuid);
        if (oldName != null)
            nameUuidMap.remove(oldName.toLowerCase());
        uuidNameMap.put(uuid, newName);
    }

    public void saveAll() {
        this.redisService.executeBackendCommand(redis -> {
            uuidNameMap.forEach((uuid, s) -> redis.hset("UUIDCache", uuid.toString(), s));
            return null;
        });
    }

    public int getCachedAmount() {
        return uuidNameMap.size();
    }

    public Map<String, UUID> getNameUuidMap() {
        return nameUuidMap;
    }

    public Map<UUID, String> getUuidNameMap() {
        return uuidNameMap;
    }
}
