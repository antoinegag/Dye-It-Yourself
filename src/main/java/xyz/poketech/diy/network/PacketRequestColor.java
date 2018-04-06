package xyz.poketech.diy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.poketech.diy.DyeItYourself;
import xyz.poketech.diy.util.color.NBTColorUtil;

public class PacketRequestColor implements IMessage {

    private int entityID;

    public PacketRequestColor() {
    }

    public PacketRequestColor(int entityID) {
        this.entityID = entityID;
    }

    PacketRequestColor(Entity entity) {
        this.entityID = entity.getEntityId();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
    }

    public static class ColorRequesthandler implements IMessageHandler<PacketRequestColor, PacketUpdateColor> {

        @Override
        public PacketUpdateColor onMessage(PacketRequestColor message, MessageContext ctx) {

            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;

            Entity entity = serverPlayer.getServerWorld().getEntityByID(message.entityID);

            if (entity != null && entity.getEntityData().hasKey(NBTColorUtil.COLOR_KEY)) {
                int color = entity.getEntityData().getInteger(NBTColorUtil.COLOR_KEY);
                return new PacketUpdateColor(message.entityID, color);
            }
            return null;
        }
    }

}


