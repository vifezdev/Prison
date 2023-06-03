package gg.convict.prison.privatemine.menu.create;

import gg.convict.prison.privatemine.grid.SchematicType;
import gg.convict.prison.privatemine.grid.menu.GridAddMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.menu.Menu;

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
        Map<Integer, Button> buttonMap = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            if (GridAddMenu.CYAN_BORDER_SLOTS.contains(i))
                buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 3));

            if (GridAddMenu.BLUE_BORDER_SLOTS.contains(i))
                buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 11));
        }

        for (SchematicType type : SchematicType.values())
            buttonMap.put(index += 1, new MineCreateButton(type));

        return buttonMap;
    }

}