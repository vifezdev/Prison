package gg.convict.prison.warp.menu;

import gg.convict.prison.privatemine.grid.menu.GridAddMenu;
import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import org.bukkit.entity.Player;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;

import java.util.List;
import java.util.Map;

public class WarpsMenu extends Menu {

    private final List<Warp> warps;

    public WarpsMenu() {
        this.warps = WarpModule.get().getConfig().getWarps();
    }

    @Override
    public String getTitle(Player player) {
        return "Warps";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        int index = 9;
        Map<Integer, Button> buttonMap = GridAddMenu.fillMenu(getSize());

        for (Warp warp : warps)
            buttonMap.put(index += 1, new WarpButton(warp));

        return buttonMap;
    }

    @Override
    public int getSize() {
        return warps.size() > 9
                ? 36 : 27;
    }

}