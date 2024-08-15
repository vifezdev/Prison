package gg.convict.prison.banknote;

import gg.convict.prison.banknote.currency.Currency;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.CC;

import java.util.UUID;

@Data
public class BankNote {

    private final UUID uuid;

    private int amount;
    private boolean redeemed = false;
    private Currency currency;

    private UUID issuedBy;
    private UUID issuedTo;
    private UUID redeemedBy;
    private long issuedAt;
    private long redeemedAt = -1;

    public BankNote(int amount, Currency currency, UUID issuedBy, UUID issuedTo) {
        this.uuid = UUID.randomUUID();
        this.amount = amount;
        this.currency = currency;
        this.issuedBy = issuedBy;
        this.issuedTo = issuedTo;
        this.issuedAt = System.currentTimeMillis();
    }

    public ItemStack toItemStack() {
        return new ItemBuilder(Material.PAPER)
                .setDisplayName(CC.format(
                        "%s &a&lBank Note",
                        getValue()
                )).addToLore(
                        CC.translate("&fUUID: &b" + uuid.toString()),
                        "",
                        CC.translate("&7Right click this bank note"),
                        CC.format("&7to redeem %s &7into your account.", getValue())
                ).build();
    }

    public String getValue() {
        return currency.getColoredIcon() + amount;
    }

    public void redeemFor(Player player) {
        this.redeemed = true;
        this.redeemedBy = player.getUniqueId();
        this.redeemedAt = System.currentTimeMillis();

        currency.give(player, amount);

        player.sendMessage(CC.format(
                "&fYou have redeemed a &b%s&f bank note.",
                getValue()));
    }

}