package gg.convict.prison.banknote.currency;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public enum Currency {

    TOKEN(() -> PrisonPlugin.get().getPrisonConfig()
            .getTokenCurrency(), Profile::addTokens),

    MONEY(() -> PrisonPlugin.get().getPrisonConfig()
            .getMoneyCurrency(), Profile::addBalance);

    private final Supplier<CurrencyData> currencyData;
    private final BiConsumer<Profile, Integer> consumer;

    public void give(Player player, int amount) {
        Profile profile = ProfileModule.get().getProfileHandler().getProfile(player);

        if (profile != null)
            consumer.accept(profile, amount);
    }

    public String getColoredIcon() {
        CurrencyData data = currencyData.get();

        return data.getPreColor()
                + data.getIcon()
                + data.getPostColor();
    }

    public static Currency getCurrency(String name) {
        for (Currency value : values()) {
            if (value.name().equalsIgnoreCase(name))
                return value;
        }

        return null;
    }

}