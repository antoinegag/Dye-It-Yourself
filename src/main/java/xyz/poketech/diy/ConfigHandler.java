package xyz.poketech.diy;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = DyeItYourself.MODID, name = DyeItYourself.MODID + "/" + DyeItYourself.MODID, category = "")
@Mod.EventBusSubscriber(modid = DyeItYourself.MODID)
public class ConfigHandler {

    @Config.Comment({"General settings"})
    @Config.LangKey("config.diy.general")
    public static ConfigGeneral general = new ConfigGeneral();

    @Config.Comment({"Dye drop settings"})
    @Config.LangKey("config.diy.dye_dropping")
    public static ConfigDyeDrop dyeDrop = new ConfigDyeDrop();

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(DyeItYourself.MODID)) {
            ConfigManager.sync(event.getModID(), Config.Type.INSTANCE);
        }
    }

    public static class ConfigDyeDrop {

        @Config.Comment({"Sheeps should drop dye"})
        @Config.LangKey("config.diy.sheep_drop_dye")
        public boolean doDropDye = true;

        @Config.Comment({"Max time until next dye"})
        @Config.LangKey("config.diy.sheep_drop_time_higher")
        public int rngUpperBoundTime = 12000;

        @Config.Comment({"Min time until next dye"})
        @Config.LangKey("config.diy.sheep_drop_time_lower")
        public int rngLowerBoundTime = 6000;

        @Config.Comment({"Maximum of dye sheeps should drop at the time"})
        @Config.LangKey("config.diy.max_dye_drop")
        @Config.RangeInt(min = 0)
        public int maxDyeDrop = 2;

        @Config.Comment({"Minimum of dye sheeps should drop at the time"})
        @Config.LangKey("config.diy.min_dye_drop")
        @Config.RangeInt(min = 0)
        public int minDyeDrop = 1;
    }

    public static class ConfigGeneral {

        @Config.Comment({"Sheeps should eat flowers"})
        @Config.LangKey("config.diy.sheep_eat_flowers")
        public boolean sheepEatFlowers = true;

        @Config.Comment({"Sheeps should take the color of the flower they eat"})
        @Config.LangKey("config.diy.sheep_absorb")
        public boolean sheepAbsorbColor = true;
    }
}
