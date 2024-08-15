package gg.convict.prison.scoreboard.placeholder;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.util.placeholder.adapter.PlaceholderAdapter;
import gg.convict.prison.util.uuid.UUIDCache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PrisonMinePlaceholderAdapter implements PlaceholderAdapter {

    @Override
    public String getIdentifier() {
        return "mine";
    }

    @Override
    public String getPlaceholder(Player player, String placeholder) {
        Mine mine = MineModule.get().getHandler().getMine(player.getLocation());

        if (mine == null)
            return null;

        switch (placeholder.toLowerCase()) {
            case "owner":
                return UUIDCache.getName(mine.getOwner());
            case "owner-formatted":
                return Bukkit.getOfflinePlayer(mine.getOwner()).getName();
            case "blocks-left":
                return String.valueOf(100 - mine.getAirPercentage());
            default:
                return null;
        }
    }

}