package gg.convict.prison.pickaxe.enchant.command;

import gg.convict.prison.pickaxe.PickaxeData;
import gg.convict.prison.pickaxe.PickaxeModule;
import gg.convict.prison.pickaxe.enchant.Enchant;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.CC;

public class TestEnchantCommand {

    @Command(names = "testenchant",
            permission = "op")
    public void execute(Player player) {
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null
                || itemInHand.getType() != Material.DIAMOND_PICKAXE) {
            player.sendMessage(CC.RED + "You must be holding a pickaxe.");
            return;
        }

        Enchant enchant = PickaxeModule.get()
                .getEnchantHandler().getEnchant("Efficiency");

        PickaxeData data = PickaxeModule.get().getHandler().getData(itemInHand);
        if (data == null) {
            player.sendMessage(CC.RED + "Pickaxe data is null.");
            return;
        }

        data.applyEnchant(player, itemInHand, enchant, 25);
    }

}