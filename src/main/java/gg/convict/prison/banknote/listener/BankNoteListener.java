package gg.convict.prison.banknote.listener;

import gg.convict.core.util.SenderUtil;
import gg.convict.prison.banknote.BankNote;
import gg.convict.prison.banknote.BankNoteModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class BankNoteListener implements Listener {

    private final BankNoteModule module;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.getAction().name().startsWith("RIGHT")
                || event.getPlayer().getItemInHand() == null)
            return;

        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        BankNote bankNote = module.getBankNote(itemInHand);

        if (bankNote == null)
            return;

        event.setCancelled(true);
        if (bankNote.isRedeemed()) {
            player.sendMessage(CC.RED + "This bank note has already been redeemed, if you believe this may be an error contact the staff team.");
            CC.send(
                    "&4[ANTI-DUPE] &b%s&f has attempted to redeem a used bank note worth &b%s&f.",
                    "core.admin",
                    SenderUtil.getName(player),
                    bankNote.getValue());
            return;
        }

        if (bankNote.getAmount() >= module.getHandler().getAnnounceThreshold())
            CC.send(
                    "&3[Bank Notes] &b%s&f has redeemed a bank note worth &b%s&f.",
                    "core.admin",
                    SenderUtil.getName(player),
                    bankNote.getValue());

        bankNote.redeemFor(player);

        if (itemInHand.getAmount() <= 1)
            player.getInventory().setItemInHand(null);
        else
            itemInHand.setAmount(itemInHand.getAmount() - 1);

        player.updateInventory();
    }

}