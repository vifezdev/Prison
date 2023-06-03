package gg.convict.prison.privatemine.grid.menu;

import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.privatemine.grid.menu.button.GridAddButton;
import gg.convict.prison.privatemine.grid.menu.button.GridStatusButton;
import gg.convict.prison.privatemine.util.list.MineList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.menu.Menu;
import org.hydrapvp.libraries.utils.CC;

import java.util.HashMap;
import java.util.Map;

public class GridAdminMenu extends Menu {

    public static GridAdminMenu INSTANCE = new GridAdminMenu();

    private final MineList<Integer> cyanBorderSlots
            = MineList.of(0, 2, 4, 6, 8, 18, 20, 22, 24, 26);

    private final MineList<Integer> blueBorderSlots
            = MineList.of(1, 3, 5, 7, 9, 17, 19, 21, 23, 25);

    @Override
    public String getTitle(Player player) {
        return CC.AQUA + "Mine Grid";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            if (cyanBorderSlots.contains(i))
                buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 3));

            if (blueBorderSlots.contains(i))
                buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 11));
        }

        MineHandler handler = MineModule.get().getHandler();
        buttonMap.put(12, new GridAddButton(handler));
        buttonMap.put(14, new GridStatusButton(handler));

        return buttonMap;
    }

    @Override
    public int getSize() {
        return 27;
    }

}