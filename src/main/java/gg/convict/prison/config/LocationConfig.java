package gg.convict.prison.config;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Getter
public class LocationConfig {

    private final String name;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public LocationConfig(Location location) {
        this.name = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public Location getLocation() {
        return new Location(
                Bukkit.getWorld(name),
                x, y, z, yaw, pitch);
    }

    public boolean matches(Location location) {
        return name.equals(location.getWorld().getName())
                && x == location.getX()
                && y == location.getY()
                && z == location.getZ();
    }

}