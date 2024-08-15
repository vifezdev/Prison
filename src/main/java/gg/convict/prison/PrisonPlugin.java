package gg.convict.prison;

import gg.convict.prison.banknote.BankNoteModule;
import gg.convict.prison.broadcast.BroadcastModule;
import gg.convict.prison.command.FlyCommand;
import gg.convict.prison.command.SpawnCommands;
import gg.convict.prison.config.PrisonConfig;
import gg.convict.prison.crate.CrateModule;
import gg.convict.prison.leaderboard.LeaderboardModule;
import gg.convict.prison.mongo.MongoModule;
import gg.convict.prison.pickaxe.PickaxeModule;
import gg.convict.prison.position.PositionCommand;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.profile.ProfileModule;
import gg.convict.prison.region.RegionModule;
import gg.convict.prison.scoreboard.ScoreboardModule;
import gg.convict.prison.util.chat.ChatListener;
import gg.convict.prison.util.chatinput.ChatInputListener;
import gg.convict.prison.util.command.CommandService;
import gg.convict.prison.util.configuration.ConfigurationService;
import gg.convict.prison.util.configuration.JsonConfigurationService;
import gg.convict.prison.util.menu.listener.MenuListener;
import gg.convict.prison.util.mongo.MongoService;
import gg.convict.prison.util.playersetting.listener.PlayerSettingListener;
import gg.convict.prison.util.plugin.PluginBootstrap;
import gg.convict.prison.util.redis.RedisService;
import gg.convict.prison.util.uuid.UUIDCache;
import gg.convict.prison.util.uuid.UUIDCacheListener;
import gg.convict.prison.util.visibility.VisibilityListener;
import gg.convict.prison.util.workload.WorkloadRunnable;
import gg.convict.prison.warp.WarpModule;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;

@Getter
public class PrisonPlugin extends JavaPlugin {

    @Getter
    @Setter
    private static Function<Void, String> serverNameGetter = unused -> Bukkit.getServerName();

    public static ScheduledExecutorService EXECUTOR
            = Executors.newSingleThreadScheduledExecutor();

    private PrisonConfig prisonConfig;
    private MongoService mongoService;
    private WorkloadRunnable workloadRunnable;
    private ConfigurationService configService;
    private RedisService redisService;
    private UUIDCache uuidCache;

    @Override
    public void onEnable() {
        this.configService = new JsonConfigurationService();
        this.prisonConfig = configService.loadConfiguration(PrisonConfig.class, PrisonConfig.getFile());

        this.mongoService = new MongoService(prisonConfig.getMongoConfig(), "prison");
        this.mongoService.connect();

        this.redisService = new RedisService("prison", prisonConfig.getRedisConfig());
        redisService.subscribe();

        this.uuidCache = new UUIDCache(redisService);

        CommandService.register(this,
                new PositionCommand(), // debug

                new FlyCommand(),
                new SpawnCommands(this)
        );

        PluginBootstrap.registerModules(
                new WarpModule(),
                new MineModule(),
                new MongoModule(),
                new CrateModule(),
                new RegionModule(),
                new ProfileModule(),
                new PickaxeModule(),
                new BankNoteModule(),
                new BroadcastModule(),
                new ScoreboardModule(),
                new LeaderboardModule()
        );

        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new UUIDCacheListener(uuidCache), this);
        Bukkit.getPluginManager().registerEvents(new VisibilityListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatInputListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSettingListener(), this);

        Bukkit.getScheduler().runTaskTimer(this,
                this.workloadRunnable = new WorkloadRunnable(), 1L, 1L);
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        configService.saveConfiguration(
                prisonConfig,
                PrisonConfig.getFile()
        );

        EXECUTOR.shutdown();
    }

    public static PrisonPlugin get() {
        return JavaPlugin.getPlugin(PrisonPlugin.class);
    }

}