package gg.convict.prison.util.menu.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

import java.util.function.Consumer;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 30.12.2019 / 04:32
 * libraries / cc.invictusgames.libraries.menu.buttons
 */

public class ConfirmationButton extends Button {

    private boolean bool;
    private Consumer<Boolean> callable;
    private String name;

    public ConfirmationButton(boolean bool, Consumer<Boolean> callable) {
        this.bool = bool;
        this.callable = callable;
    }

    public ConfirmationButton(boolean bool, String name, Consumer<Boolean> callable) {
        this.bool = bool;
        this.callable = callable;
        this.name = name;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.WOOL, (short) (bool ? 5 : 14))
                .setDisplayName(bool ? CC.GREEN + CC.BOLD + name : CC.RED + CC.BOLD + name)
                .build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        player.closeInventory();
        callable.accept(bool);
    }
}
