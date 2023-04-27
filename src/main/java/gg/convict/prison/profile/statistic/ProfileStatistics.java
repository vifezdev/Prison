package gg.convict.prison.profile.statistic;

import lombok.Data;
import org.bson.Document;

import java.util.UUID;

@Data
public class ProfileStatistics {

    private UUID uuid;
    private int kills = 0;
    private int deaths = 0;
    private int blocksMined = 0;

    public ProfileStatistics(UUID uuid) {
        this.uuid = uuid;
    }

    public ProfileStatistics(Document document) {
        this.uuid = UUID.fromString(document.getString("uuid"));
        this.kills = document.getInteger("kills");
        this.deaths = document.getInteger("deaths");
        this.blocksMined = document.getInteger("blocksMined");
    }

    public Document toBson() {
        Document document = new Document();

        document.append("uuid", uuid.toString());
        document.append("kills", kills);
        document.append("deaths", deaths);
        document.append("blocksMined", blocksMined);

        return document;
    }

    public double getKd() {
        if (deaths == 0)
            return kills;

        return (double) deaths / kills;
    }

    public void addKill() {
        this.kills += 1;
    }

    public void addDeath() {
        this.deaths += 1;
    }

    public void addBlocksMined(int amount) {
        this.blocksMined += amount;
    }

}