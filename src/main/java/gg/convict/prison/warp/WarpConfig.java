package gg.convict.prison.warp;

import lombok.Getter;
import gg.convict.prison.util.configuration.StaticConfiguration;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WarpConfig implements StaticConfiguration {

    private final List<Warp> warps = new ArrayList<>();

    public void addWarp(Warp warp) {
        warps.add(warp);
    }

    public void removeWarp(String name) {
        Warp warp = WarpModule.get().getWarp(name);

        if (warp != null)
            warps.remove(warp);
    }

    public boolean hasWarp(String name) {
        for (Warp warp : warps) {
            if (warp.getName().equalsIgnoreCase(name))
                return true;
        }

        return false;
    }
}