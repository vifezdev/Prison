package gg.convict.prison.banknote.command.parameter;

import gg.convict.prison.banknote.currency.Currency;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

public class CurrencyParameter implements ParameterType<Currency> {

    @Override
    public Currency parse(CommandSender sender, String source) {
        Currency currency = Currency.getCurrency(source);

        if (currency == null)
            sender.sendMessage(CC.format(
                    "&cA currency with the name \"%s\" does not exist.",
                    source));

        return currency;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> list) {
        List<String> result = new ArrayList<>();

        for (Currency value : Currency.values())
            result.add(value.name());

        return result;
    }

}