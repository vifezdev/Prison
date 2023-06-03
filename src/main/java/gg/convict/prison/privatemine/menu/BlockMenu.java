package gg.convict.prison.privatemine.menu;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.util.MaterialUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.menu.Menu;
import org.hydrapvp.libraries.utils.CC;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class BlockMenu extends Menu {

    public static final Material[] BLOCKS = {
            Material.STONE,
            Material.COAL_ORE
    };

    private final Mine mine;

    @Override
    public String getTitle(Player player) {
        return "Mine Blocks";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();

        for (Material block : BLOCKS)
            buttonMap.put(buttonMap.size(), new BlockButton(block, material -> {
                mine.setBlockMaterial(material);
                mine.resetBlocks(true);
                mine.setLastReset(System.currentTimeMillis());

                player.sendMessage(CC.format(
                        "%sSet the block type of &byour mine&f to &b%s&f.",
                        PrisonPlugin.PREFIX,
                        MaterialUtil.getName(material)
                ));
            }));

        return buttonMap;
    }

    @RequiredArgsConstructor
    public static class BlockButton extends Button {

        private final Material material;
        private final Consumer<Material> consumer;

        @Override
        public ItemStack getItem(Player player) {
            String displayMaterial = MaterialUtil.getName(material);

            return new ItemBuilder(material)
                    .setDisplayName(CC.AQUA + displayMaterial)
                    .addToLore(
                            "",
                            CC.format("&fClick to set the block"),
                            CC.format("&ftype to &b%s", displayMaterial)
                    ).build();
        }

        @Override
        public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
            player.closeInventory();
            consumer.accept(material);
        }
    }

}