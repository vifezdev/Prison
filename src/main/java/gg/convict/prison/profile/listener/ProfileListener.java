package gg.convict.prison.profile.listener;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.pickaxe.PickaxeModule;
import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.profile.ProfileModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.configuration.defaults.LocationConfig;

@RequiredArgsConstructor
public class ProfileListener implements Listener {

    private final ProfileModule module;

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        module.getProfileHandler().loadProfile(event.getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setFoodLevel(20);
        player.setHealth(20);

        LocationConfig locationConfig = PrisonPlugin.get()
                .getPrisonConfig().getSpawnLocation();

        if (!player.hasPlayedBefore()) {
            if (locationConfig != null)
                player.teleport(locationConfig.getLocation());

            ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
            PickaxeModule.get().setupData(player, itemStack);

            player.getInventory().addItem(itemStack);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        event.setFoodLevel(20);
    }

}