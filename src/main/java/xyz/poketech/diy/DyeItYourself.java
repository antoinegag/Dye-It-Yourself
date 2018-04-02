package xyz.poketech.diy;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.poketech.diy.ai.EntityAIEatFlower;
import xyz.poketech.diy.ai.EntityAIEjectDye;

@Mod.EventBusSubscriber
@Mod(modid = DyeItYourself.MODID, name = DyeItYourself.NAME, version = DyeItYourself.VERSION, acceptedMinecraftVersions = DyeItYourself.VERSION_RANGE)
public class DyeItYourself {
    public static final String MODID = "diy";
    public static final String NAME = "Dye it yourself";
    public static final String VERSION = "1.0";
    public static final String VERSION_RANGE = "[1.12,1.13)";

    @Mod.Instance
    public static DyeItYourself instance;

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @SubscribeEvent
    public static void onEntitySpawn(LivingSpawnEvent.EnteringChunk event) {
        if (event.getEntity() instanceof EntitySheep) {
            EntitySheep sheep = ((EntitySheep) event.getEntity());

            if (ConfigHandler.general.sheepEatFlowers) {
                sheep.tasks.addTask(5, new EntityAIEatFlower(sheep));
            }

            if (ConfigHandler.general.sheepPoopDye) {
                sheep.tasks.addTask(6, new EntityAIEjectDye(sheep));
            }
        }
    }
}