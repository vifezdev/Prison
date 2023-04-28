package gg.convict.prison.privatemine.menu.filler;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.menu.Menu;
import org.hydrapvp.libraries.menu.fill.IMenuFiller;
import org.hydrapvp.libraries.menu.page.PagedMenu;

import java.util.Map;

@Getter
public class PrisonMenuFiller implements IMenuFiller {

    public static Button DARK_BLUE_GLASS
            = Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 11);

    public static Button LIGHT_BLUE_GLASS =
            Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 3);

    @Override
    public void fill(Menu menu, Player player, Map<Integer, Button> buttons, int size) {
        int startIndex = menu instanceof PagedMenu ? 8 : 0;

        for (int i = startIndex; i < size; ++i) {
            Button chosenButton = i % 2 == 0 ? DARK_BLUE_GLASS : LIGHT_BLUE_GLASS;
            if (i < startIndex + 9) {
                buttons.putIfAbsent(i, chosenButton);
                buttons.putIfAbsent(i + (size - 9), chosenButton);
            }

            if (i % 9 == 0) {
                buttons.putIfAbsent(i, chosenButton);
                buttons.putIfAbsent(i + 8, chosenButton);
            }
        }
    }

}