package net.minestom.server.network.packet.server.play;

import net.minestom.server.network.packet.PacketWriter;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;

public class EntityPositionPacket implements ServerPacket {

    public int entityId;
    public short deltaX, deltaY, deltaZ;
    public boolean onGround;

    @Override
    public void write(PacketWriter writer) {
        writer.writeVarInt(entityId);
        writer.writeShort(deltaX);
        writer.writeShort(deltaY);
        writer.writeShort(deltaZ);
        writer.writeBoolean(onGround);
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.ENTITY_POSITION;
    }
}
