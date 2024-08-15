package gg.convict.prison.util.uuid.packets;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.util.redis.packet.Packet;

import java.util.UUID;

public class UUIDUpdatePacket implements Packet {

    public UUIDUpdatePacket() {
    }

    public UUIDUpdatePacket(UUID uuid, String oldName, String newName) {
        this.uuid = uuid;
        this.oldName = oldName;
        this.newName = newName;
    }

    private UUID uuid;
    private String oldName;
    private String newName;

    @Override
    public void receive() {
        PrisonPlugin.get().getUuidCache().updateLocally(uuid, oldName, newName);
    }
}
