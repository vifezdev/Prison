package gg.convict.prison.util.redis;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.util.Statics;
import gg.convict.prison.util.configuration.defaults.RedisConfig;
import gg.convict.prison.util.redis.packet.Packet;
import gg.convict.prison.util.redis.packet.PacketPubSub;
import gg.convict.prison.util.redis.subscriber.ListenerPubSub;
import gg.convict.prison.util.redis.subscriber.RedisListener;
import gg.convict.prison.util.redis.subscriber.RedisSubscriber;
import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;


import java.util.Arrays;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 31.12.2019 / 03:20
 * libraries / cc.invictusgames.libraries.redis
 */

@Getter
public class RedisService {

    public static int SERVICE_AMOUNT = 0;

    @Getter
    public static RedisService instance;

    private static RedisConfig backendConfig = null;
    private static JedisPool backendPool = null;

    private long lastExecution = -1;
    private long lastPacket = -1;
    private String lastPacketName = "N/A";
    private long lastError = -1;
    private boolean down = false;

    private final String channel;
    private final JedisPool pool;

    private Jedis subscribeClient;
    private Thread subscribeThread;

    private final RedisConfig config;
    private boolean subscribed = false;

    public RedisService(String channel, RedisConfig config) {
        instance = this;

        this.channel = channel;
        this.config = config;

        if (backendPool == null) {
            backendConfig = PrisonPlugin.get().getPrisonConfig().getRedisConfig();
            if (backendConfig.getDbId() != 0)
                throw new IllegalArgumentException("dbId of backend redis connection (libraries config) must be 0 ("
                        + backendConfig.getDbId() + ")");
            backendPool = new JedisPool(backendConfig.getHost(), backendConfig.getPort());
        }

        this.pool = new JedisPool(config.getHost(), config.getPort());

        SERVICE_AMOUNT++;
    }


    public void publish(Packet packet) {
        this.executeCommand(redis -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("packet", packet.getClass().getName());
            jsonObject.addProperty("data", Statics.PLAIN_GSON.toJson(packet));
            lastPacketName = packet.getClass().getSimpleName();
            redis.publish(this.channel, jsonObject.toString());
            return null;
        }, true, pool, config);
    }

    public void publish(String channel, String message) {
        executeCommand(redis -> {
            redis.publish(channel, message);
            return null;
        });
    }

    public void publish(String channel, JsonElement json) {
        executeCommand(redis -> {
            redis.publish(channel, json.toString());
            return null;
        });
    }

    public void unSubscribe() {
        SERVICE_AMOUNT -= 1;

        subscribeClient = null;
        subscribeThread = null;
        subscribed = false;
    }

    public void subscribe() {
        if (this.subscribeThread != null)
            return;

        this.subscribeThread = new Thread(() -> {
            if (subscribeClient == null) {
                subscribeClient = pool.getResource();
                if (config.isAuthEnabled())
                    subscribeClient.auth(config.getAuthPassword());
            }

            this.subscribed = true;
            JedisPubSub pubSub = new PacketPubSub();
            subscribeClient.subscribe(pubSub, this.channel);
        }, "Libraries - Redis Subscriber");
        this.subscribeThread.setDaemon(true);
        this.subscribeThread.start();
    }

    public void addListener(RedisListener... listeners) {
        Arrays.stream(listeners).forEach(listener ->
                Arrays.stream(listener.getClass().getDeclaredMethods())
                        .filter(method -> method.isAnnotationPresent(RedisSubscriber.class)
                                && method.getParameterCount() < 3
                                && method.getParameterCount() > 0)
                        .forEach(method -> new Thread(() -> {
                            if (subscribeClient == null) {
                                subscribeClient = pool.getResource();
                                if (config.isAuthEnabled())
                                    subscribeClient.auth(config.getAuthPassword());
                            }

                            RedisSubscriber annotation = method.getAnnotation(RedisSubscriber.class);
                            subscribeClient.subscribe(new ListenerPubSub(listener, method), annotation.channels());
                        }).start()));
    }

    public <T> T executeCommand(RedisCommand<T> command) {
        return this.executeCommand(command, false, pool, config);
    }

    public <T> T executeBackendCommand(RedisCommand<T> command) {
        return this.executeCommand(command, false, backendPool, backendConfig);
    }

    private <T> T executeCommand(RedisCommand<T> command, boolean packet, JedisPool pool, RedisConfig config) {
        if (packet)
            lastPacket = System.currentTimeMillis();
        else
            lastExecution = System.currentTimeMillis();

        try (Jedis jedis = pool.getResource()) {
            if (config.isAuthEnabled())
                jedis.auth(config.getAuthPassword());

            jedis.select(config.getDbId());
            down = false;
            return command.execute(jedis);
        } catch (Exception e) {
            e.printStackTrace();
            lastError = System.currentTimeMillis();
            down = true;

            SERVICE_AMOUNT -= 1;
            return null;
        }
    }

}
