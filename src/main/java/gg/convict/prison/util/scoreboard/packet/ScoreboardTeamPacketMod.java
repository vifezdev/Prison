package gg.convict.prison.util.scoreboard.packet;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ScoreboardTeamPacketMod {

    private final PacketPlayOutScoreboardTeam packet;

    public ScoreboardTeamPacketMod(String name, String prefix, String suffix, Collection<?> players, int paramInt) {
        this.packet = new PacketPlayOutScoreboardTeam();
        try {
            packet.a = name;
            packet.h = paramInt;
            if (packet.h == 0 || packet.h == 2) {
                packet.a = name;
                packet.c = prefix;
                packet.d = suffix;
                packet.i = 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (paramInt == 0) {
            this.addAll(players);
        }
    }

    public ScoreboardTeamPacketMod(String name, Collection<?> players, int paramInt) {
        this.packet = new PacketPlayOutScoreboardTeam();
        try {
            packet.i = 3;
            packet.a = name;
            packet.h = paramInt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.addAll(players);
    }

    public void sendToPlayer(Player bukkitPlayer) {
        if (packet != null && bukkitPlayer != null)
            ((CraftPlayer) bukkitPlayer).getHandle().playerConnection.sendPacket(this.packet);
    }

    private void addAll(Collection<?> col) {
        if (col == null) {
            return;
        }
        try {
            this.packet.g.addAll((Collection<String>) col);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
