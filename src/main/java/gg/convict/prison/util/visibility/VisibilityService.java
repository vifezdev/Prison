package gg.convict.prison.util.visibility;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.util.CC;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 24.10.2020 / 06:43
 * libraries / cc.invictusgames.libraries
 */

public class VisibilityService {

    private static final List<VisibilityAdapter> VISIBILITY_ADAPTERS =
            new ArrayList<>(Collections.singletonList(VisibilityAdapter.DEFAULT));
    @Getter
    @Setter
    private static BiFunction<Player, CommandSender, Boolean> onlineTreatProvider = (player, sender) -> true;

    private static boolean initialized = false;

    public static void init() {
        if (initialized) {
            throw new IllegalStateException("VisibilityService has already been initialized");
        }

        initialized = true;
        Bukkit.getPluginManager().registerEvents(new VisibilityListener(), PrisonPlugin.get());
    }

    public static void registerVisibilityAdapter(VisibilityAdapter adapter) {
        VISIBILITY_ADAPTERS.add(adapter);
        VISIBILITY_ADAPTERS.sort(Comparator.comparingInt(VisibilityAdapter::getPriority).reversed());
        PrisonPlugin.get().getLogger().info(String.format(
                "[VisibilityService] Registered %s with priority %d.",
                adapter.getName(), adapter.getPriority()));
    }

    public static void update(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            update(player, target);
            update(target, player);
        }
    }

    public static void update(Player player, Player target) {
        VisibilityAction action = VisibilityAction.NEUTRAL;
        int index = 0;
        while (action == VisibilityAction.NEUTRAL) {
            action = VISIBILITY_ADAPTERS.get(index++).canSee(player, target);
        }

        if (action == VisibilityAction.HIDE)
            player.hidePlayer(target);
        else
            player.showPlayer(target);
    }

    public static List<String> getDebugInfo(Player player, Player target) {
        List<String> debugs = new ArrayList<>();

        VisibilityAction finalAction = VisibilityAction.NEUTRAL;
        for (VisibilityAdapter adapter : VISIBILITY_ADAPTERS) {
            ChatColor color = ChatColor.GRAY;
            VisibilityAction provided = adapter.canSee(player, target);
            if (provided != VisibilityAction.NEUTRAL) {
                color = provided == VisibilityAction.SHOW ? ChatColor.GREEN : ChatColor.RED;

                if (finalAction == VisibilityAction.NEUTRAL)
                    finalAction = provided;
            }

            debugs.add(CC.format(
                    "&9%s (%d): %s",
                    adapter.getName(),
                    adapter.getPriority(),
                    color + provided.name()
            ));
        }

        debugs.add(CC.format(
                "&9Result: &e%s %s &9see &e%s&9.",
                player.getName(),
                CC.colorBoolean(finalAction != VisibilityAction.HIDE, "can", "cannot"),
                target.getName()
        ));
        return debugs;
    }

}
