package xyz.poketech.diy.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.poketech.diy.DyeItYourself;
import xyz.poketech.diy.client.render.layer.LayerSheepWoolOverride;
import xyz.poketech.diy.network.PacketUpdateColor;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        DyeItYourself.NETWORK.registerMessage(PacketUpdateColor.ColorPacketHandler.class, PacketUpdateColor.class, 0, Side.CLIENT);

        Render<Entity> renderer = Minecraft.getMinecraft().getRenderManager().getEntityClassRenderObject(EntitySheep.class);
        if (renderer instanceof RenderLivingBase) {
            RenderSheep sheep = (RenderSheep) ((RenderLivingBase<?>) renderer);
            sheep.addLayer(new LayerSheepWoolOverride(sheep));
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);

    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {

    }
}
