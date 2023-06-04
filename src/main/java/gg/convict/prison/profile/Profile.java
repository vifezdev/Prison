package gg.convict.prison.profile;

import gg.convict.prison.profile.statistic.ProfileStatistics;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.utils.CC;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Profile {

    private final UUID uuid;
    private long rank = 1L;

    private BigDecimal tokens = new BigDecimal(0);
    private BigDecimal balance = new BigDecimal(0);

    private ProfileStatistics statistics;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.statistics = new ProfileStatistics(uuid);
    }

    public Profile(Document document) {
        this.uuid = UUID.fromString(document.getString("uuid"));
        this.rank = document.getLong("rank");
        this.tokens = BigDecimal.valueOf(document.getLong("tokens"));
        this.balance = BigDecimal.valueOf(document.getLong("balance"));

        ProfileStatistics statistics = new ProfileStatistics(uuid);
        if (document.containsKey("statistics"))
            statistics = new ProfileStatistics(document.get("statistics", Document.class));

        this.statistics = statistics;
    }

    public Document toBson() {
        Document document = new Document();

        document.put("uuid", uuid.toString());
        document.put("rank", rank);
        document.put("tokens", tokens.longValue());
        document.put("balance", balance.longValue());
        document.put("statistics", statistics.toBson());

        return document;
    }

    public void sendMessage(String message) {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null || !player.isOnline())
            return;

        player.sendMessage(CC.translate(message));
    }

    public void addTokens(int tokens) {
        this.tokens = this.tokens.add(new BigDecimal(tokens));
    }

    public void removeTokens(int tokens) {
        this.tokens = this.tokens.subtract(new BigDecimal(tokens));
    }

    public void addBalance(int balance) {
        this.balance = this.balance.add(new BigDecimal(balance));
    }

    public void removeBalance(int balance) {
        this.balance = this.balance.subtract(new BigDecimal(balance));
    }

    public void addMineCrates(int amount) {
        statistics.setMineCrates(statistics.getMineCrates() + amount);
    }

}