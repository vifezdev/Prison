package gg.convict.prison.scoreboard.placeholder;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.placeholder.adapter.PlaceholderAdapter;
import org.hydrapvp.libraries.uuid.UUIDCache;

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