package gg.convict.prison.util.scoreboard;

import gg.convict.prison.PrisonPlugin;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import gg.convict.prison.util.scoreboard.nametag.NameTag;
import gg.convict.prison.util.scoreboard.nametag.NameTagAdapter;
import gg.convict.prison.util.scoreboard.thread.ScoreboardUpdateThread;

import java.util.*;
import java.util.logging.Logger;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 21.10.2020 / 07:53
 * libraries / cc.invictusgames.libraries.scoreboard
 */

public class ScoreboardService {


    public static final long UPDATE_TICKS = 2L;

    public static final Map<UUID, PlayerScoreboard> SCOREBOARDS = new WeakHashMap<>();

    @Getter private static boolean initialized = false;

    private static final List<NameTagAdapter> NAME_TAG_ADAPTERS =
            new ArrayList<>(Collections.singletonList(NameTagAdapter.DEFAULT));

    @Getter private final JavaPlugin plugin;
    @Getter private final ScoreboardAdapter adapter;
    @Getter private final Logger logger;

    public ScoreboardService(JavaPlugin plugin, ScoreboardAdapter adapter) {
        if (initialized) {
            throw new IllegalStateException("ScoreboardService has already been initialized");
        }

        this.plugin = plugin;
        this.adapter = adapter;
        this.logger = Bukkit.getLogger();
        Bukkit.getPluginManager().registerEvents(new ScoreboardListener(this), plugin);

        new ScoreboardUpdateThread(this).start();
        initialized = true;
    }

    public static void registerNameTagAdapter(NameTagAdapter adapter) {
        NAME_TAG_ADAPTERS.add(adapter);
        NAME_TAG_ADAPTERS.sort(Comparator.comparingInt(NameTagAdapter::getPriority).reversed());
        PrisonPlugin.get().getLogger().info(String.format(
                "[NameTagAdapter] Registered %s with priority %d.",
                adapter.getName(), adapter.getPriority()));
    }

    public static List<NameTagAdapter> getNameTagAdapters() {
        return NAME_TAG_ADAPTERS;
    }

    public static PlayerScoreboard getBoard(Player player) {
        return SCOREBOARDS.get(player.getUniqueId());
    }

    public void updateScoreboard(Player player) {
        PlayerScoreboard playerScoreboard = getBoard(player);
        if (playerScoreboard != null)
            playerScoreboard.update();
    }

    public static NameTag getNameTag(Player player, Player target) {
        NameTag nameTag = null;
        int index = 0;
        while (nameTag == null) {
            nameTag = ScoreboardService.getNameTagAdapters().get(index++).getNameTag(player, target);
        }
        return nameTag;
    }

    public static void forceUpdateNameTags() {
        Bukkit.getScheduler().runTaskAsynchronously(PrisonPlugin.get(), () ->
                SCOREBOARDS.values().forEach(playerScoreboard -> playerScoreboard.updateNameTags(true)));
    }
}
