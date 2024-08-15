package gg.convict.prison.util.command.defaults;

import gg.convict.prison.PrisonPlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.redis.RedisService;
import gg.convict.prison.util.CC;
import gg.convict.prison.util.TimeUtils;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 02.03.2020 / 17:05
 * libraries / cc.invictusgames.libraries.command.defaults
 */

@RequiredArgsConstructor
public class RedisCommand {

    private final PrisonPlugin instance;

    @Command(names = {"redis"}, permission = "op",
            description = "View information about all Redis-Services running on the server")
    public boolean redis(CommandSender sender) {
        sender.sendMessage(CC.YELLOW + "Status: " + (RedisService.getInstance().isDown()
                ? CC.RED + "down"
                : CC.GREEN + "up"
        ));

        sender.sendMessage(CC.YELLOW + "Last command: " + CC.AQUA
                + (RedisService.getInstance().getLastExecution() == -1 ? "Never?"
                : TimeUtils.formatDetailed(System.currentTimeMillis()
                - RedisService.getInstance().getLastExecution()) + " ago"));

        sender.sendMessage(CC.YELLOW + "Last packet: " + CC.AQUA
                + (RedisService.getInstance().getLastPacket() == -1 ? "Never?"
                : TimeUtils.formatDetailed(System.currentTimeMillis()
                - RedisService.getInstance().getLastPacket()) + " " + "ago") +
                " (" + RedisService.getInstance().getLastPacketName() + ")");

        sender.sendMessage(CC.YELLOW + "Last error: " + CC.AQUA + (RedisService.getInstance().getLastError() == -1 ?
                "Never (wohoo)"
                : TimeUtils.formatDetailed(System.currentTimeMillis()
                - RedisService.getInstance().getLastError()) + " ago"));

        sender.sendMessage(CC.YELLOW + "Cached UUID's: "
                + CC.AQUA + instance.getUuidCache().getCachedAmount());

        sender.sendMessage(CC.YELLOW + "Services registered: "
                + CC.AQUA + RedisService.SERVICE_AMOUNT);
        return true;
    }
}
