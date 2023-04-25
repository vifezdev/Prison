package gg.convict.prison.profile.statistic;

import gg.convict.prison.profile.Profile;
import lombok.Data;
import org.bson.Document;

@Data
public class ProfileStatistics {

    private final transient Profile profile;

    private int kills = 0;
    private int deaths = 0;
    private int blocksMined = 0;

    public void update(Document document) {
        this.kills = document.getInteger("kills");
        this.deaths = document.getInteger("deaths");
        this.blocksMined = document.getInteger("blocksMined");
    }

    public Document toBson() {
        Document document = new Document();

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