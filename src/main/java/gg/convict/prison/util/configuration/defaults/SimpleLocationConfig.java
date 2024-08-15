package gg.convict.prison.util.configuration.defaults;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import gg.convict.prison.util.configuration.StaticConfiguration;


/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 15.03.2020 / 13:54
 * libraries / cc.invictusgames.libraries.configuration.defaults
 */

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class SimpleLocationConfig implements StaticConfiguration {

    public SimpleLocationConfig(Location location) {
        this(location, false);
    }

    public SimpleLocationConfig(Location location, boolean block) {
        setLocation(location, block);
    }

    private String world = "";
    private double x = 0;
    private double y = 0;
    private double z = 0;

    public void setLocation(Location location) {
        this.setLocation(location, false);
    }

    public void setLocation(Location location, boolean block) {
        this.world = location.getWorld().getName();
        if (block) {
            this.x = location.getBlockX();
            this.y = location.getBlockY();
            this.z = location.getBlockZ();
        } else {
            this.x = location.getX();
            this.y = location.getY();
            this.z = location.getZ();
        }
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(this.world), x, y, z);
    }

    public boolean matches(Location location) {
        return world.equalsIgnoreCase(location.getWorld().getName())
                && x == location.getBlockX()
                && y == location.getBlockY()
                && z == location.getBlockZ();
    }

}
