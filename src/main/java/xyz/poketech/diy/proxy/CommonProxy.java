package xyz.poketech.diy.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import xyz.poketech.diy.DyeItYourself;
import xyz.poketech.diy.network.PacketHandler;
import xyz.poketech.diy.network.PacketRequestColor;
import xyz.poketech.diy.network.PacketUpdateColor;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        PacketHandler.registerMessages();
    }

    public void init(FMLInitializationEvent e) {


    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

    }
}
