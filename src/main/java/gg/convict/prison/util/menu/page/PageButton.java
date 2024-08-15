package gg.convict.prison.util.menu.page;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

/**
 * @author langgezockt (langgezockt@gmail.com)
 * 22.06.2019 / 12:38
 * iUtils / cc.invictusgames.iutils.utils.menu
 */

public class PageButton extends Button {

    private int mod;
    private PagedMenu menu;

    public PageButton(int mod, PagedMenu menu) {
        this.mod = mod;
        this.menu = menu;
    }


    @Override
    public ItemStack getItem(Player player) {
        if (this.hasNext(player)) {
            return new ItemBuilder(Material.CARPET, DyeColor.LIME.getWoolData())
                    .setDisplayName(mod > 0 ? CC.GREEN + CC.BOLD + "Next Page"
                            : CC.GREEN + CC.BOLD + "Previous Page")
                    .build();
        } else {
            return new ItemBuilder(Material.CARPET, DyeColor.GRAY.getWoolData())
                    .setDisplayName(mod > 0 ? CC.GRAY + CC.BOLD + "Next Page"
                            : CC.GRAY + CC.BOLD + "Previous Page")
                    //.setLore(CC.RED + "You are already on", CC.RED + "the " + (mod > 0 ? "Last " : "First ") + "Page")
                    .build();
        }
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType.isShiftClick()) {
            if (hasNext(player)) {
                this.menu.modPage(player, this.mod > 0 ?
                        this.menu.getPages(player) - this.menu.getPage() :
                        1 - this.menu.getPage());
            }
        } else {
            if (hasNext(player)) {
                this.menu.modPage(player, mod);
            }
        }
    }

    private boolean hasNext(Player player) {
        int pg = this.menu.getPage() + this.mod;
        return pg > 0 && this.menu.getPages(player) >= pg;
    }

}
