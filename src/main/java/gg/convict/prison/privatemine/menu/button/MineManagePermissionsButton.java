package gg.convict.prison.privatemine.menu.button;

import gg.convict.prison.privatemine.Mine;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.utils.CC;

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