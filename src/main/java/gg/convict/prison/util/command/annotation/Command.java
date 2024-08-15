package gg.convict.prison.util.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 15.01.2020 / 22:02
 * libraries / cc.invictusgames.libraries.commandapi
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

    String[] names();

    String permission() default "";

    boolean playerOnly() default false;

    boolean async() default false;

    boolean hidden() default false;

    String description() default "N/A";

}
