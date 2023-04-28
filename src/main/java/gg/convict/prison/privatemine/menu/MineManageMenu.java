package gg.convict.prison.privatemine.menu;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.menu.button.*;
import gg.convict.prison.privatemine.menu.filler.PrisonMenuFiller;
import gg.convict.prison.privatemine.util.IntMineList;
import gg.convict.prison.privatemine.util.MineList;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.menu.Menu;
import org.hydrapvp.libraries.menu.fill.IMenuFiller;
import org.hydrapvp.libraries.utils.CC;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class MineManageMenu extends Menu {

    private static final IntMineList BORDER_SLOTS = new IntMineList()
            .add(9, 18, 27, 36)
            .add(17, 26, 35, 44)
            .addRange(0, 9)
            .addRange(45, 54);

    private final Mine mine;

    @Override
    public String getTitle(Player player) {
        return CC.AQUA + "Private Mine";
    }

    // todo 1m reset cooldown

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            if (BORDER_SLOTS.contains(i))
                continue;

            buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 15));
        }

        buttonMap.put(21, new MineTeleportButton(mine));
        buttonMap.put(23, new MineManagePermissionsButton(mine));

        buttonMap.put(29, new MineResetButton(mine));
        buttonMap.put(31, new MineBlocksButton(mine));
        buttonMap.put(33, new MineStatusButton(mine));

        return buttonMap;
    }

    @Override
    public IMenuFiller getMenuFiller() {
        return new PrisonMenuFiller();
    }

    @Override
    public int getSize() {
        return 54;
    }

}