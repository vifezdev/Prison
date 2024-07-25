package gg.convict.prison.banknote.command;

import gg.convict.prison.banknote.BankNote;
import gg.convict.prison.banknote.BankNoteModule;
import gg.convict.prison.banknote.currency.Currency;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class BankNoteCommand {

    private final BankNoteModule module;

    @Command(names = "banknote give",
            permission = "prison.banknote.give",
            description = "Give a player a bank note")
    public void execute(CommandSender sender,
                        @Param(name = "target") Player target,
                        @Param(name = "amount") int amount,
                        @Param(name = "currency") Currency currency) {
        BankNote bankNote = new BankNote(
                amount, currency,
                sender instanceof Player ? ((Player) sender).getUniqueId() : null,
                target.getUniqueId());

        module.getHandler().addNote(bankNote);

        target.getInventory().addItem(bankNote.toItemStack());
        target.updateInventory();

        sender.sendMessage(CC.format(
                "&fYou have given &b%s&f a &b%s&f bank note.",
                target.getDisplayName(),
                bankNote.getValue()
        ));

        target.sendMessage(CC.format(
                "&fYou have been given a &b%s&f bank note by &b%s&f.",
                bankNote.getValue(),
                sender.getName()
        ));
    }

}