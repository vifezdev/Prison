package gg.convict.prison.util.redis.subscriber;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 14.03.2021 / 12:30
 * libraries / cc.invictusgames.libraries.redis.subscriber
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisSubscriber {

    String[] channels();

}
