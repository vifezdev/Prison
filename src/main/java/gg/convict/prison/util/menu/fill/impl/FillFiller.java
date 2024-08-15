package gg.convict.prison.util.menu.fill.impl;

import org.bukkit.entity.Player;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;
import gg.convict.prison.util.menu.fill.IMenuFiller;
import gg.convict.prison.util.menu.page.PagedMenu;

import java.util.Map;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 17.12.2020 / 21:21
 * libraries / cc.invictusgames.libraries.menu.fill.implement
 */

public class FillFiller implements IMenuFiller {

    @Override
    public void fill(Menu menu, Player player, Map<Integer, Button> buttons, int size) {
        for (int i = menu instanceof PagedMenu ? 8 : 0; i < size; i++)
            buttons.putIfAbsent(i, Button.createPlaceholder(menu.getPlaceholderItem(player)));
    }

}
