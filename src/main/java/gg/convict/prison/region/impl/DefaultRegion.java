package gg.convict.prison.region.impl;

import gg.convict.prison.region.Region;
import org.bukkit.ChatColor;

import java.util.UUID;

public class DefaultRegion extends Region {

    public DefaultRegion() {
        super(UUID.randomUUID(), "Wilderness", null, ChatColor.DARK_GREEN);
    }

}