package gg.convict.prison.util.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import org.bson.Document;
import gg.convict.prison.util.configuration.defaults.MongoConfig;

import java.util.Collections;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 04.03.2020 / 18:31
 * libraries / cc.invictusgames.libraries.mongo
 */

@Getter
public class MongoService {

    public static final ReplaceOptions
            REPLACE_OPTIONS = new ReplaceOptions().upsert(true);

    @Getter
    private static MongoService instance;

    public static int SERVICE_AMOUNT = 0;

    private final MongoConfig config;
    private final String databaseName;

    private long lastExecution = -1;
    private long lastError = -1;
    private boolean down = false;
    private long lastLatency = -1;
    private long latencyTicks = 0;
    private long averageLatency = -1;

    private MongoClient client;
    private MongoDatabase database;

    public MongoService(MongoConfig config, String databaseName) {
        instance = this;

        this.config = config;
        this.databaseName = databaseName;

        SERVICE_AMOUNT++;
    }

    public boolean connect() {
        if (config.isAuthEnabled()) {
            MongoCredential credential = MongoCredential.createCredential(
                    config.getAuthUsername(),
                    config.getAuthDatabase(),
                    config.getAuthPassword().toCharArray()
            );

            client = new MongoClient(new ServerAddress(config.getHost(), config.getPort()),
                    Collections.singletonList(credential));
        } else client = new MongoClient(config.getHost(), config.getPort());

        try {
            database = client.getDatabase(this.databaseName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        SERVICE_AMOUNT -= 1;

        down = true;
        database = null;
        client = null;
    }

    public <T> T executeCommand(MongoCommand<T> command) {
        lastExecution = System.currentTimeMillis();
        try {
            down = false;
            T t = command.execute(this.client, this.database);
            ++latencyTicks;
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            down = true;
            lastError = System.currentTimeMillis();

            SERVICE_AMOUNT -= 1;
            return null;
        }
    }

    public MongoCollection<Document> getCollection(String name) {
        return this.executeCommand((client, database) -> database.getCollection(name));
    }

    public long getAverageLatency() {
        if (latencyTicks == 0) {
            return -1;
        }
        return averageLatency / latencyTicks;
    }

}
