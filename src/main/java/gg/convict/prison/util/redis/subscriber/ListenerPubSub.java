package gg.convict.prison.util.redis.subscriber;

import com.google.gson.JsonElement;
import gg.convict.prison.util.Statics;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPubSub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 14.03.2021 / 12:54
 * libraries / cc.invictusgames.libraries.redis.subscriber
 */

@RequiredArgsConstructor
public class ListenerPubSub extends JedisPubSub {

    private final RedisListener listener;
    private final Method method;

    @Override
    public void onMessage(String channel, String message) {
        try {
            JsonElement json = null;
            if (JsonElement.class.isAssignableFrom(method.getParameterTypes()[method.getParameterCount() == 1 ? 0 : 1]))
                json = Statics.JSON_PARSER.parse(message);

            if (method.getParameterCount() == 1)
                method.invoke(listener, json == null ? message : json);
            else method.invoke(listener, channel, json == null ? message : json);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
