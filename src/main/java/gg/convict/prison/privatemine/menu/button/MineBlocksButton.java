package gg.convict.prison.privatemine.menu.button;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.menu.BlockMenu;
import gg.convict.prison.privatemine.util.MaterialUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MineBlocksButton extends Button {

    private final Mine mine;

    @Override
    public ItemStack getItem(Player player) {
        List<String> lore = new ArrayList<>();
        lore.add(CC.format("&fCurrent Block: &c%s", getCurrentBlock()));
        lore.add("");

        // todo check if they level something
        lore.add(CC.translate("&fClick to change the"));
        lore.add(CC.translate("&eblocks &fin &eyour mine&f."));

        return new ItemBuilder(Material.GRASS)
                .setDisplayName(CC.YELLOW + CC.BOLD + "Change Blocks")
                .setData(3)
                .setLore(lore)
                .build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (!mine.canReset()) {
            player.sendMessage(CC.format(
                    "&cYou can reset your mine blocks in &b%s&c.",
                    mine.formatNextReset()
            ));
            return;
        }

        new BlockMenu(mine).openMenu(player);
    }

    public String getCurrentBlock() {
        return MaterialUtil.getName(mine.getBlockMaterial());
    }

}