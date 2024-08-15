package gg.convict.prison.banknote.command;

import gg.convict.prison.banknote.BankNote;
import gg.convict.prison.banknote.BankNoteModule;
import gg.convict.prison.banknote.menu.BankNoteViewMenu;
import gg.convict.prison.util.uuid.PlainUUID;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import gg.convict.prison.util.CC;
import java.util.UUID;

@RequiredArgsConstructor
public class BankNoteViewCommand {

    public static final String NO_UUID = "c5479915-ee51-413c-bfee-224c585306e9";
    private final BankNoteModule module;

    @Command(names = "banknote view",
            permission = "prison.banknote.view",
            description = "View information of a bank note",
            playerOnly = true)
    public void execute(Player player, @Param(name = "uuid", defaultValue = NO_UUID) PlainUUID uuid) {
        if (!uuid.asUuid().toString().equals(NO_UUID)) {
            BankNote bankNote = module.getBankNote(uuid.asUuid());

            if (bankNote == null) {
                player.sendMessage(CC.RED + "A bank note with that UUID does not exist.");
                return;
            }

            new BankNoteViewMenu(bankNote).openMenu(player);
            return;
        }

        ItemStack itemInHand = player.getInventory().getItemInHand();
        if (itemInHand == null || !module.isBankNote(itemInHand)) {
            player.sendMessage(CC.RED + "You must be holding a bank note to view it.");
            return;
        }

        new BankNoteViewMenu(module.getBankNote(itemInHand)).openMenu(player);
    }

}