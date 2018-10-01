package xyz.poketech.diy.network;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import xyz.poketech.diy.DyeItYourself;

public class PacketHandler {


    public static void registerMessages() {

        DyeItYourself.NETWORK.registerMessage(
                PacketRequestColor.ColorRequesthandler.class,
                PacketRequestColor.class,
                1, Side.SERVER
        );
    }

    public static void registerClientMessages() {

        DyeItYourself.NETWORK.registerMessage(
                PacketUpdateColor.ColorPacketHandler.class,
                PacketUpdateColor.class,
                0, Side.CLIENT
        );
    }

    public static void sendColorUpdate(int target, int color, BlockPos pos, int dimension, int range) {
        DyeItYourself.NETWORK.sendToAllAround(
                new PacketUpdateColor(target,  color),
                new NetworkRegistry.TargetPoint(dimension, pos.getX(), pos.getY(), pos.getZ(), range)
        );
    }
}
