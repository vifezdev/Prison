package gg.convict.prison.util.menu.fill;

import org.bukkit.entity.Player;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;

import java.util.Map;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 17.12.2020 / 21:20
 * libraries / cc.invictusgames.libraries.menu.fill
 */

public interface IMenuFiller {

    void fill(Menu menu, Player player, Map<Integer, Button> buttons, int size);

}
