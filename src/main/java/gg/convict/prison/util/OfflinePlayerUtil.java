package gg.convict.prison.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class OfflinePlayerUtil {

    public static CompletableFuture<OfflinePlayer> getOfflinePlayer(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            return Bukkit.getOfflinePlayer(uuid);
        });
    }

}
