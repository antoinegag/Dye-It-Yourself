package xyz.poketech.diy.client.render;

import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import xyz.poketech.diy.DyeItYourself;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = DyeItYourself.MODID)
public class RenderPlayer {

    @SubscribeEvent
    public static void render(RenderPlayerEvent.Pre e) {

    }

    @SubscribeEvent
    public static void render(RenderPlayerEvent.Post e) {

    }
}
