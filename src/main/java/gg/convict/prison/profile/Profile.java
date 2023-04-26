package gg.convict.prison.profile;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.mongo.MongoModule;
import gg.convict.prison.profile.statistic.ProfileStatistics;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.mongo.MongoService;
import org.hydrapvp.libraries.utils.CC;

import java.util.UUID;

@Getter @Setter
public class Profile {

    private final UUID uuid;
    private long rank = 1L;
    private int tokens = 0;
    private int balance = 0;

    private ProfileStatistics statistics;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.statistics = new ProfileStatistics(this);
    }

    public Profile(Document document) {
        this.uuid = UUID.fromString(document.getString("uuid"));
        this.rank = document.getLong("rank");
        this.tokens = document.getInteger("tokens");
        this.balance = document.getInteger("balance");
    }

    public Document toBson() {
        Document document = new Document();

        document.put("uuid", uuid.toString());
        document.put("rank", rank);
        document.put("tokens", tokens);
        document.put("balance", balance);
        document.put("statistics", statistics.toBson());

        return document;
    }

    public void save() {
        ProfileModule.get().getProfileHandler().saveProfile(this, true);
    }

    public void sendMessage(String message) {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null || !player.isOnline())
            return;

        player.sendMessage(CC.translate(message));
    }

    public void addTokens(int tokens) {
        this.tokens += tokens;
    }

    public void removeTokens(int tokens) {
        this.tokens -= tokens;
    }

    public void addBalance(int balance) {
        this.balance += balance;
    }

    public void removeBalance(int balance) {
        this.balance -= balance;
    }

}