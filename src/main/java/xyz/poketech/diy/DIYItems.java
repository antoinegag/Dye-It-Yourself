package xyz.poketech.diy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.poketech.diy.item.ItemDyeBrush;

@Mod.EventBusSubscriber(modid = DyeItYourself.MODID)
public class DIYItems {

    @GameRegistry.ObjectHolder(DyeItYourself.MODID + ":dye_brush")
    public static ItemDyeBrush itemDyeBrush;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemDyeBrush());
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModelLoader.setCustomModelResourceLocation(
                itemDyeBrush, 0,
                new ModelResourceLocation(itemDyeBrush.getRegistryName(),
                "inventory")
        );
    }
}
