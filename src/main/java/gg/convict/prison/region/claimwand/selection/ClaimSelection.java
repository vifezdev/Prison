package gg.convict.prison.region.claimwand.selection;

import gg.convict.prison.region.Region;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.cuboid.Cuboid;
import org.hydrapvp.libraries.utils.CC;

import java.util.UUID;

@Data
public class ClaimSelection {

    private Region region;

    public UUID uuid;
    public String world;

    public Location pos1;
    public Location pos2;

    public ClaimSelection(Region region, UUID uuid, String world) {
        this.region = region;
        this.uuid = uuid;
        this.world = world;
    }

    public void setPosition(int id, Location location) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null)
            return;

        if (id != 1 && id != 2) {
            player.sendMessage(CC.RED + "Invalid position (" + id + ").");
            return;
        }

        if (id == 1)
            pos1 = location.clone();

        if (id == 2)
            pos2 = location.clone();

        player.sendMessage(CC.format(
                "&fSelected position &b%s&f. &b(%s, %s, %s)",
                id,
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()));
    }

    public boolean doPurchase() {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null)
            return false;

        if (!bothPositionsSet()) {
            player.sendMessage(CC.RED + "You must set both positions of the claim selection.");
            return false;
        }

//        pos1.setY(0);

        Location clone = pos2.clone();
//        clone.setY(clone.getWorld().getMaxHeight());

        region.setCuboid(new Cuboid(pos1, clone));
        region.save();

        player.sendMessage(CC.format(
                "&fSuccessfully claimed land for &b%s&f.",
                region.getDisplayName()
        ));

        return true;
    }

    public boolean bothPositionsSet() {
        return pos1 != null && pos2 != null;
    }

}
