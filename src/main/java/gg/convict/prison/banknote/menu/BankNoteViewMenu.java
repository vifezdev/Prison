package gg.convict.prison.banknote.menu;

import gg.convict.prison.banknote.BankNote;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;
import gg.convict.prison.util.CC;
import gg.convict.prison.util.TimeUtils;

import java.util.*;

@RequiredArgsConstructor
public class BankNoteViewMenu extends Menu {

    public static final String MENU_BAR = CC.GRAY + CC.STRIKE_THROUGH + "-------------------------------------";
    private final BankNote bankNote;

    @Override
    public String getTitle(Player player) {
        return "Viewing Bank Note";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttonMap = new HashMap<>();

        for (int i = 0; i < 9; i++)
            buttonMap.put(i, Button.createPlaceholder(Material.STAINED_GLASS_PANE, (short) 7));

        buttonMap.replace(4, new BankNoteButton());

        return buttonMap;
    }

    public class BankNoteButton extends Button {

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(Material.PAPER)
                    .setDisplayName(CC.translate(bankNote.getValue() + "&a&l Bank Note"))
                    .setLore(getLore())
                    .build();
        }

        public List<String> getLore() {
            List<String> lore = new ArrayList<>();

            lore.add(MENU_BAR);
            lore.add(CC.format(
                    "&fUUID: &b%s",
                    bankNote.getUuid().toString()));

            UUID issuedBy = bankNote.getIssuedBy();
            lore.add(CC.format(
                    "&fIssued By: &b%s",
                    issuedBy == null ? "&4Console" : Bukkit.getOfflinePlayer(issuedBy).getName()));

            lore.add(CC.format(
                    "&fIssued To: &b%s",
                    Bukkit.getOfflinePlayer(bankNote.getIssuedTo()).getName()));

            lore.add(CC.format(
                    "&fIssued On: &b%s",
                    TimeUtils.formatDate(bankNote.getIssuedAt())));

            lore.add(CC.format(
                    "&fRedeemed: &r%s",
                    bankNote.isRedeemed() ? "&aTrue" : "&cFalse"));

            if (bankNote.isRedeemed()) {
                lore.add(MENU_BAR);
                lore.add(CC.format(
                        "&fRedeemed By: &b%s",
                        Bukkit.getOfflinePlayer(bankNote.getRedeemedBy()).getName()));

                lore.add(CC.format(
                        "&fRedeemed On: &b%s",
                        TimeUtils.formatDate(bankNote.getRedeemedAt())));
            }

            lore.add(MENU_BAR);

            return lore;
        }

    }
}
