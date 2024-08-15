package gg.convict.prison.privatemine.menu.button;

import gg.convict.prison.privatemine.Mine;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class MineManagePermissionsButton extends Button {

    private final Mine mine;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.SKULL_ITEM)
                .setDisplayName(CC.PINK + CC.BOLD + "Edit Permissions")
                .setData(3)
                .setSkullOwner(player.getName())
                .addToLore(
                        "",
                        CC.translate("&fClick to edit the &dpermissions"),
                        CC.translate("&fof players in your &dmine&f.")
                ).build();
    }

}