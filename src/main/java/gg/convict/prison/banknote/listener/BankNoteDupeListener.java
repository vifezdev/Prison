package gg.convict.prison.banknote.listener;

import gg.convict.prison.banknote.BankNoteModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class BankNoteDupeListener implements Listener {

    private final BankNoteModule module;

    @EventHandler
    public void onInventoryCreative(InventoryCreativeEvent event) {
        if (event.getClickedInventory() == null
                || event.getWhoClicked().getGameMode() != GameMode.CREATIVE)
            return;

        if (module.isBankNote(event.getCursor())) {
            event.setCancelled(true);
            return;
        }

        ItemStack item = event.getClickedInventory().getItem(event.getSlot());
        if (item != null && module.isBankNote(item))
            event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked().getGameMode() != GameMode.CREATIVE)
            return;

        if (event.getClickedInventory() == null) {
            if (event.getCursor() != null && module.isBankNote(event.getCursor()))
                event.setCancelled(true);

            return;
        }

        ItemStack item = event.getClickedInventory().getItem(event.getSlot());
        if (item != null && module.isBankNote(item))
            event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getWhoClicked().getGameMode() != GameMode.CREATIVE)
            return;

        if (module.isBankNote(event.getCursor())
                || module.isBankNote(event.getOldCursor())) {
            event.setCancelled(true);
            return;
        }

        for (ItemStack value : event.getNewItems().values()) {
            if (!module.isBankNote(value))
                continue;

            event.setCancelled(true);
            return;
        }
    }

}