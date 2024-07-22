package gg.convict.prison.scoreboard.placeholder;

import gg.convict.core.util.SenderUtil;
import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
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
                return SenderUtil.getName(mine.getOwner());
            case "blocks-left":
                return String.valueOf(100 - mine.getAirPercentage());
            default:
                return null;
        }
    }

}