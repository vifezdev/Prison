package gg.convict.prison.region.claimwand;

import gg.convict.prison.region.claimwand.selection.ClaimSelection;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ClaimWandHandler {

    private final ClaimWand claimWand = new ClaimWand();
    private final Map<UUID, ClaimSelection> selectionMap = new ConcurrentHashMap<>();

    public void removeSelection(UUID uuid) {
        ClaimSelection selection = getSelection(uuid);

        if (selection != null)
            selectionMap.remove(uuid);
    }

    public ClaimSelection createSelection(Player player, ClaimSelection selection) {
        return selectionMap.getOrDefault(player.getUniqueId(),
                selectionMap.put(player.getUniqueId(), selection));
    }

    public ClaimSelection getSelection(UUID uuid) {
        return selectionMap.get(uuid);
    }

    public void removeClaimWands(Player player) {
        player.getInventory().removeItem(claimWand.toItemStack());
    }

}
