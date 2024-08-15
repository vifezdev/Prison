package gg.convict.prison.crate.menu;

import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.menu.button.*;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CrateEditMenu extends Menu {

    private final Crate crate;

    @Override
    public String getTitle(Player player) {
        return crate.getDisplayName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            if (!BORDER_SLOTS.contains(i))
                continue;

            buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 3));
        }

        buttonMap.put(11, new CrateEditRewardsButton(crate, this));
        buttonMap.put(12, new CrateToggleBroadcastButton(crate));
        buttonMap.put(13, new CrateToggleChancesButton(crate));
        buttonMap.put(14, new CrateChangeColorButton(crate, this));
        buttonMap.put(15, new CrateChangeRewardButton(crate));

        return buttonMap;
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public boolean isClickUpdate() {
        return true;
    }

    public static final List<Integer> BORDER_SLOTS = new ArrayList<Integer>() {{
        for (int i = 0; i < 9; i++)
            add(i);

        for (int i = 18; i < 27; i++)
            add(i);
    }};

}