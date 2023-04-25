package gg.convict.prison.banknote;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.banknote.command.BankNoteCommand;
import gg.convict.prison.banknote.command.BankNoteViewCommand;
import gg.convict.prison.banknote.command.parameter.CurrencyParameter;
import gg.convict.prison.banknote.currency.Currency;
import gg.convict.prison.banknote.listener.BankNoteDupeListener;
import gg.convict.prison.banknote.listener.BankNoteListener;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.command.parameter.ParameterType;
import org.hydrapvp.libraries.plugin.PluginModule;
import org.hydrapvp.libraries.utils.LoreUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BankNoteModule extends PluginModule {

    public BankNoteModule() {
        super("bank-notes", PrisonPlugin.get(), new BankNoteHandler());
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new BankNoteCommand(this),
                new BankNoteViewCommand(this)
        );
    }

    @Override
    public List<Listener> getListeners() {
        return ImmutableList.of(
                new BankNoteListener(this),
                new BankNoteDupeListener(this)
        );
    }

    @Override
    public Map<Class<?>, ParameterType<?>> getParameterTypes() {
        return ImmutableMap.of(
                Currency.class, new CurrencyParameter());
    }

    public boolean isBankNote(ItemStack itemStack) {
        return getBankNote(itemStack) != null;
    }

    public BankNote getBankNote(ItemStack itemStack) {
        if (itemStack == null
                || itemStack.getType() != Material.PAPER
                || !itemStack.hasItemMeta()
                || !itemStack.getItemMeta().hasDisplayName()
                || !itemStack.getItemMeta().hasLore())
            return null;

        String uuidString = LoreUtil.getValue(
                "UUID", itemStack.getItemMeta().getLore());

        return uuidString == null
                ? null
                : getBankNote(UUID.fromString(uuidString));
    }

    public BankNote getBankNote(UUID uuid) {
        for (BankNote note : getHandler().getNotes()) {
            if (note.getUuid().equals(uuid))
                return note;
        }

        return null;
    }

    public BankNoteHandler getHandler() {
        return (BankNoteHandler) super.getConfig();
    }

}