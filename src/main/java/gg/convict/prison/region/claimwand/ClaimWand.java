package gg.convict.prison.region.claimwand;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.utils.CC;

import java.util.Arrays;
import java.util.List;

@Getter
public class ClaimWand {

    private final String name = "claim_wand";

    public List<String> getLore() {
        return Arrays.asList(
                CC.PINK + "Right/Left Click" + CC.WHITE + " a block",
                CC.PINK + "- " + CC.WHITE + "Select claim's corners."
        );
    }

    public ItemStack toItemStack() {
        ItemBuilder itemBuilder = new ItemBuilder(Material.WOOD_HOE);

        itemBuilder.setDisplayName(CC.GREEN + "Claiming Wand");
        itemBuilder.setLore(getLore());

        return itemBuilder.build();
    }

}
