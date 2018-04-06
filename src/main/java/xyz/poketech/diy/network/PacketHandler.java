package xyz.poketech.diy.network;

import net.minecraftforge.fml.relauncher.Side;
import xyz.poketech.diy.DyeItYourself;

public class PacketHandler {

    private static int id = 0;


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
}
