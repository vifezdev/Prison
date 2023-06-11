package gg.convict.prison;

import gg.convict.prison.banknote.BankNoteModule;
import gg.convict.prison.broadcast.BroadcastModule;
import gg.convict.prison.command.*;
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
import gg.convict.prison.tab.PrisonTabAdapter;
import gg.convict.prison.warp.WarpModule;
import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.hydrapvp.libraries.command.CommandService;
import org.hydrapvp.libraries.configuration.*;
import org.hydrapvp.libraries.mongo.MongoService;
import org.hydrapvp.libraries.plugin.PluginBootstrap;
import org.hydrapvp.libraries.tab.TabService;
import org.hydrapvp.libraries.workload.WorkloadRunnable;

import java.util.concurrent.*;

@Getter
public class PrisonPlugin extends JavaPlugin {

    public static ScheduledExecutorService EXECUTOR
            = Executors.newSingleThreadScheduledExecutor();

    private PrisonConfig prisonConfig;
    private MongoService mongoService;
    private WorkloadRunnable workloadRunnable;
    private ConfigurationService configService;

    @Override
    public void onEnable() {
        this.configService = new JsonConfigurationService();
        this.prisonConfig = configService.loadConfiguration(PrisonConfig.class, PrisonConfig.getFile());

        this.mongoService = new MongoService(prisonConfig.getMongoConfig(), "prison");
        this.mongoService.connect();

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

        new TabService(this, new PrisonTabAdapter());

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