package gg.convict.prison.pickaxe.enchant.menu;

import org.bukkit.entity.Player;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class EnchantMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Enchantments";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();


        return buttonMap;
    }
}
