package gg.convict.prison.banknote.listener;

import gg.convict.prison.banknote.BankNote;
import gg.convict.prison.banknote.BankNoteModule;
import gg.convict.prison.profile.util.MoneyUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.utils.CC;
import org.hydrapvp.libraries.utils.ChatMessage;

import java.math.BigDecimal;

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
                    player.getDisplayName(),
                    bankNote.getValue());
            return;
        }

        if (bankNote.getAmount() >= module.getHandler().getAnnounceThreshold()) {
            ChatMessage message = new ChatMessage(CC.format(
                    "&3[Bank Notes] &b%s&f redeemed a bank note worth &b%s%s&f.",
                    player.getDisplayName(),
                    bankNote.getCurrency().getColoredIcon(),
                    MoneyUtil.format(BigDecimal.valueOf(bankNote.getAmount()), 0)
            ));

            String uuid = bankNote.getUuid().toString();
            message.hoverText(CC.format("&fUUID: &b%s\n\n&7Click to view bank note.", uuid))
                    .runCommand("/banknote view " + uuid);

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (!onlinePlayer.hasPermission("core.admin"))
                    continue;

                message.send(onlinePlayer);
            }
        }

        bankNote.redeemFor(player);

        if (itemInHand.getAmount() <= 1)
            player.getInventory().setItemInHand(null);
        else
            itemInHand.setAmount(itemInHand.getAmount() - 1);

        player.updateInventory();
    }

}