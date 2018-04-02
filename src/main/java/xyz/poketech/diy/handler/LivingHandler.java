package xyz.poketech.diy.handler;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.poketech.diy.ConfigHandler;
import xyz.poketech.diy.ai.EntityAIEatFlower;
import xyz.poketech.diy.ai.EntityAIEjectDye;

@Mod.EventBusSubscriber
public class LivingHandler {

    @SubscribeEvent
    public static void onEntitySpawn(LivingSpawnEvent.EnteringChunk event) {
        if (event.getEntity() instanceof EntitySheep) {
            EntitySheep sheep = ((EntitySheep) event.getEntity());

            if (ConfigHandler.general.sheepEatFlowers) {
                sheep.tasks.addTask(5, new EntityAIEatFlower(sheep));
            }

            if (ConfigHandler.general.sheepPoopDye) {
                //TODO: change that, shouldn't be using an AI task for that...
                sheep.tasks.addTask(6, new EntityAIEjectDye(sheep));
            }
        }
    }
}
