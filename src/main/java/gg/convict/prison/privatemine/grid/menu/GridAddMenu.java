package gg.convict.prison.privatemine.grid.menu;

import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.grid.SchematicType;
import gg.convict.prison.privatemine.grid.menu.button.GridAddTypeButton;
import gg.convict.prison.privatemine.util.list.MineList;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.menu.Menu;
import org.hydrapvp.libraries.utils.CC;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GridAddMenu extends Menu {

    public static final MineList<Integer> CYAN_BORDER_SLOTS
            = MineList.of(0, 2, 4, 6, 8, 18, 20, 22, 24, 26);

    public static final MineList<Integer> BLUE_BORDER_SLOTS
            = MineList.of(1, 3, 5, 7, 9, 17, 19, 21, 23, 25);

    private final MineHandler handler;

    @Override
    public String getTitle(Player player) {
        return CC.AQUA + "Add Mines";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        int index = 8;
        Map<Integer, Button> buttonMap = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            if (CYAN_BORDER_SLOTS.contains(i))
                buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 3));

            if (BLUE_BORDER_SLOTS.contains(i))
                buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 11));
        }

        for (SchematicType type : SchematicType.values())
            buttonMap.put(index += 1, new GridAddTypeButton(handler.getMineGrid(), type));

        return buttonMap;
    }

    @Override
    public int getSize() {
        return 27;
    }

}