package xyz.poketech.diy.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.poketech.diy.ConfigHandler;
import xyz.poketech.diy.DyeItYourself;
import xyz.poketech.diy.util.WorldUtil;

public class EntityAIEjectDye extends EntityAIBase {


    /**
     * The entity owner of this AITask
     */
    private final EntityLiving dyeEjecterEntity;

    /**
     * The world the entity is from
     */
    private final World entityWorld;

    /**
     * Number of ticks since the entity started to eject dye
     */
    int ejectDyeTimer;

    public EntityAIEjectDye(EntityLiving flowerEaterEntityIn) {
        this.dyeEjecterEntity = flowerEaterEntityIn;
        this.entityWorld = flowerEaterEntityIn.world;
        this.setMutexBits(4);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return this.dyeEjecterEntity.getRNG().nextInt(this.dyeEjecterEntity.isChild() ? 50 : 1000) == 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.ejectDyeTimer = this.dyeEjecterEntity.getRNG().nextInt(4000) + 3000;
        this.dyeEjecterEntity.getNavigator().clearPath();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.ejectDyeTimer = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return this.ejectDyeTimer > 0;
    }

    /**
     * Number of ticks since the entity started to eject dye
     */
    public int getEjectDyeTimer() {
        return this.ejectDyeTimer;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        if(ConfigHandler.general.sheepPoopDye) {
            this.ejectDyeTimer = Math.max(0, this.ejectDyeTimer - 1);

            if (this.ejectDyeTimer == 4) {
                BlockPos blockpos = getBlockPos();

                if (this.dyeEjecterEntity instanceof EntitySheep) {
                    EntitySheep sheep = (EntitySheep) this.dyeEjecterEntity;
                    int count = Math.max(0, sheep.getRNG().nextInt(ConfigHandler.general.maxDyePoop - ConfigHandler.general.minDyePoop) + ConfigHandler.general.minDyePoop);
                    WorldUtil.spawnStack(entityWorld, blockpos, new ItemStack(Items.DYE, count, sheep.getFleeceColor().getDyeDamage()));
                    float pitch = (this.dyeEjecterEntity.getRNG().nextFloat() - this.dyeEjecterEntity.getRNG().nextFloat()) * 0.2F + 1.0F;
                    this.dyeEjecterEntity.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, pitch);
                }
            }
        }
    }

    private BlockPos getBlockPos() {
        return new BlockPos(this.dyeEjecterEntity.posX, this.dyeEjecterEntity.posY, this.dyeEjecterEntity.posZ);
    }
}
