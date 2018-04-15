package xyz.poketech.diy.handler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.poketech.diy.ConfigHandler;
import xyz.poketech.diy.DyeItYourself;
import xyz.poketech.diy.ai.EntityAIEatFlower;
import xyz.poketech.diy.network.PacketRequestColor;
import xyz.poketech.diy.util.RandomUtil;
import xyz.poketech.diy.util.WorldUtil;


@Mod.EventBusSubscriber(modid = DyeItYourself.MODID)
public class LivingHandler {

    public static final String NEXT_DYE_KEY = "nextDye";

    @SubscribeEvent
    public static void onEntitySpawn(LivingSpawnEvent.EnteringChunk event) {
        if (event.getEntity() instanceof EntitySheep) {
            EntitySheep sheep = ((EntitySheep) event.getEntity());

            if (ConfigHandler.general.sheepEatFlowers) {
                sheep.tasks.addTask(5, new EntityAIEatFlower(sheep));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityEnterWorld(EntityJoinWorldEvent event) {
        //Sync the sheep color on the client
        if(event.getWorld().isRemote) {
            if(event.getEntity() instanceof EntitySheep) {
                DyeItYourself.NETWORK.sendToServer(new PacketRequestColor(event.getEntity().getEntityId()));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (!entity.world.isRemote && ConfigHandler.dyeDrop.doDropDye && entity instanceof EntitySheep) {

            EntitySheep sheep = (EntitySheep) event.getEntityLiving();
            NBTTagCompound data = sheep.getEntityData();

            if (data.hasKey(NEXT_DYE_KEY)) {
                int nextDye = data.getInteger(NEXT_DYE_KEY);
                if (nextDye == 1) {
                    //Spawn a random amount of dye
                    int count = RandomUtil.getDyeDropAmountSafe();
                    if(count != 0) {
                        WorldUtil.spawnStack(sheep.world, sheep.getPosition(), new ItemStack(Items.DYE, count, sheep.getFleeceColor().getDyeDamage()));

                        //Play the chicken egg sound
                        float pitch = (sheep.getRNG().nextFloat() - sheep.getRNG().nextFloat()) * 0.2F + 1.0F;
                        sheep.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, pitch);
                    }

                    //Set new dye
                    data.setInteger(NEXT_DYE_KEY, RandomUtil.getNextDye());
                } else {
                    data.setInteger(NEXT_DYE_KEY, --nextDye);
                }
            } else {
                data.setInteger(NEXT_DYE_KEY, RandomUtil.getNextDye());
            }
        }
    }
}
