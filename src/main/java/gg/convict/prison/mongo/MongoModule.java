package gg.convict.prison.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import gg.convict.prison.PrisonPlugin;
import org.bson.Document;
import org.hydrapvp.libraries.plugin.PluginModule;

import java.util.UUID;

public class MongoModule extends PluginModule {

    public MongoModule() {
        super("mongo", PrisonPlugin.get(), null);
    }

    public Document getProfile(UUID uuid) {
        return getProfiles().find(Filters.eq(
                "uuid",
                uuid.toString()
        )).first();
    }

    public MongoCollection<Document> getProfiles() {
        return PrisonPlugin.get().getMongoService().getCollection("profiles");
    }

    public static MongoModule get() {
        return PluginModule.getModule(MongoModule.class);
    }

}