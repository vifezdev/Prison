package gg.convict.prison.privatemine.menu.create;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.privatemine.grid.SchematicType;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.github.paperspigot.Title;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class MineCreateButton extends Button {

    private final SchematicType type;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(type.getItemStack())
                .setDisplayName(type.getColor() + type.getTextName())
                .setLore(CC.MENU_BAR,
                        CC.format(
                                "&fClick to create a %s%s&f mine.",
                                type.getColor(), type.getTextName()
                        ),
                        CC.MENU_BAR
                ).build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        MineHandler handler = MineModule.get().getHandler();
        Mine mine = handler.getMine(player);

        if (mine != null) {
            player.sendMessage(CC.RED + "You already have a mine!");
            player.closeInventory();
            return;
        }

        mine = handler.allocateMine(player, type);
        if (mine == null) {
            player.sendMessage(CC.RED + "An error occurred whilst allocating your mine.");
            return;
        }

        mine.teleport(player);
        player.sendTitle(new Title(
                CC.translate("&a&lMINE CREATED"),
                CC.translate("&fTeleported you to your mine"),
                20, 80, 20
        ));
    }

}