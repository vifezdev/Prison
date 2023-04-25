package gg.convict.prison.banknote.currency;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.function.BiConsumer;

@Getter
@RequiredArgsConstructor
public enum Currency {

    TOKEN("⛃", "&3", "&b", Profile::addTokens),
    MONEY("$", "&2", "&a", Profile::addBalance);

    private final String icon;
    private final String preColor;
    private final String postColor;
    private final BiConsumer<Profile, Integer> consumer;

    public void give(Player player, int amount) {
        Profile profile = ProfileModule.get().getProfileHandler().getProfile(player);

        if (profile != null)
            consumer.accept(profile, amount);
    }

    public String getColoredIcon() {
        return preColor + icon + postColor;
    }

    public static Currency getCurrency(String name) {
        for (Currency value : values()) {
            if (value.name().equalsIgnoreCase(name))
                return value;
        }

        return null;
    }

}