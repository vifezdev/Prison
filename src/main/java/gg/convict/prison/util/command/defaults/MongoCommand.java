package gg.convict.prison.util.command.defaults;

import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.mongo.MongoService;
import gg.convict.prison.util.CC;
import gg.convict.prison.util.TimeUtils;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 04.03.2020 / 18:57
 * libraries / cc.invictusgames.libraries.command.defaults
 */

public class MongoCommand {

    @Command(names = {"mongo"}, permission = "op",
            description = "View information about all Mongo-Services running on the server")
    public boolean mongo(CommandSender sender) {
        if (MongoService.getInstance() == null) {
            sender.sendMessage(CC.RED + "There are no mongo services running.");
            return true;
        }

        sender.sendMessage(CC.YELLOW + "Status: "
                + (MongoService.getInstance().isDown() ? CC.RED + "down" : CC.GREEN + "up"));
        sender.sendMessage(CC.YELLOW + "Last command: "
                + CC.AQUA + (MongoService.getInstance().getLastExecution() == -1 ? "Never?"
                : TimeUtils.formatDetailed(System.currentTimeMillis()
                - MongoService.getInstance().getLastExecution()) +
                " ago"));

        sender.sendMessage(CC.YELLOW + "Last error: " + CC.AQUA
                + (MongoService.getInstance().getLastError() == -1
                ? "Never (wohoo)" : TimeUtils.formatDetailed(System.currentTimeMillis()
                - MongoService.getInstance().getLastError()) + " ago"));

        sender.sendMessage(CC.YELLOW + "Last latency: "

                + CC.AQUA + MongoService.getInstance().getLastLatency() + "ms");
        sender.sendMessage(CC.YELLOW + "Average latency: "

                + CC.AQUA + MongoService.getInstance().getAverageLatency() + "ms");
        sender.sendMessage(CC.YELLOW + "Services registered: "
                + CC.AQUA + MongoService.SERVICE_AMOUNT);
        return true;
    }
}
