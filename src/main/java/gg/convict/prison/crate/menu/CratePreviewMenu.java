package gg.convict.prison.crate.menu;

import gg.convict.prison.crate.Crate;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;
import gg.convict.prison.util.CC;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CratePreviewMenu extends Menu {

    private final Crate crate;

    @Override
    public String getTitle(Player player) {
        return "Preview: " + crate.getDisplayName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();

        crate.getRewards().forEach(crateReward -> {
            ItemStack clone = crateReward.getItemStack().clone();
            ItemBuilder builder = new ItemBuilder(clone);

            if (crate.isShowChances()
                    && clone.getType() != Material.STAINED_GLASS_PANE)
                builder.addToLore(CC.format(
                        "&fChance: &b%s",
                        crateReward.getChance()
                ));

            buttonMap.put(buttonMap.size(),
                    Button.createPlaceholder(builder.build()));
        });

        return buttonMap;
    }

}