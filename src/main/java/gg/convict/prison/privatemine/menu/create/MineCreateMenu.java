package gg.convict.prison.privatemine.menu.create;

import gg.convict.prison.privatemine.grid.SchematicType;
import gg.convict.prison.privatemine.grid.menu.GridAddMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class MineCreateMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Create Mine";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        int index = 8;
        Map<Integer, Button> buttonMap = GridAddMenu.fillMenu(getSize());

        for (SchematicType type : SchematicType.values())
            buttonMap.put(index += 1, new MineCreateButton(type));

        return buttonMap;
    }

    @Override
    public int getSize() {
        return 27;
    }

}