package gg.convict.prison.tab;

import com.google.common.collect.Table;
import gg.convict.core.CoreShared;
import gg.convict.core.profile.Profile;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.tab.SimpleTab;
import org.hydrapvp.libraries.tab.TabAdapter;
import org.hydrapvp.libraries.tab.TabEntry;
import org.hydrapvp.libraries.utils.CC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrisonTabAdapter implements TabAdapter {

    public static final String LINE = CC.DGRAY +
            CC.STRIKE_THROUGH + StringUtils.repeat("-", 68);

    @Override
    public Table<Integer, Integer, TabEntry> getEntries(Player showingFor) {
        SimpleTab simpleTab = new SimpleTab();
        int x = 0;
        int y = 0;

        for (Profile sortedProfile : getSortedProfiles()) {
            Player player = Bukkit.getPlayer(sortedProfile.getUuid());

            if (player == null || !player.isOnline() || (x >= 4 && y >= 19))
                continue;

            String prefix = sortedProfile.getBestRank().getPrefix();
            simpleTab.addPlayer(x, y, prefix +
                    (prefix.isEmpty() ? "" : " ") + player.getName(), player);

            SlotData slotData = incrementSlot(x, y);
            x = slotData.getX();
            y = slotData.getY();
        }

        return simpleTab.build();
    }

    @Override
    public String getHeader(Player player) {
        return String.join("\n", new ArrayList<String>() {{
            add(LINE);
            add(CC.translate("&b&lConvict &f&lNetwork"));
            add("");
        }});
    }

    @Override
    public String getFooter(Player player) {
        CoreShared core = CoreShared.get();
        int globalCount = core.getServerHandler().getGlobalCount();

        return String.join("\n", new ArrayList<String>() {{
            add("");
            add(CC.format(
                    "&fYou are playing &b%s&f on &bconvict.gg",
                    core.getServerHandler().getCurrentServer().getName()
            ));
            add(CC.format(
                    "&fThere %s &b%s&f player%s across the network.",
                    globalCount == 1 ? "is" : "are",
                    globalCount,
                    globalCount == 1 ? "" : "s")
            );
            add(LINE);
        }});
    }

    public SlotData incrementSlot(int x, int y) {
        if (y == 19) {
            x += 1;
            y = 0;
            return new SlotData(x, y);
        }

        y += 1;
        return new SlotData(x, y);
    }

    public CopyOnWriteArrayList<Profile> getSortedProfiles() {
        CopyOnWriteArrayList<Profile> result = new CopyOnWriteArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers())
            result.add(CoreShared.get()
                    .getProfileHandler().getProfile(player.getUniqueId()));

        result.sort((o1, o2) -> (o2.getBestRank() == null ? 0 : o2.getBestRank().getWeight())
                - (o1.getBestRank() == null ? 0 : o1.getBestRank().getWeight()));

        return result;
    }

}