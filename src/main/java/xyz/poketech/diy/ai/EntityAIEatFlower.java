package xyz.poketech.diy.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.poketech.diy.ConfigHandler;
import xyz.poketech.diy.util.DyeUtil;
import xyz.poketech.diy.util.WorldUtil;

public class EntityAIEatFlower extends EntityAIBase {

    /**
     * The entity owner of this AITask
     */
    private final EntityLiving flowerEaterEntity;

    /**
     * The world the flower eater entity is eating from
     */
    private final World entityWorld;

    /**
     * Number of ticks since the entity started to eat flowers
     */
    int eatingFlowerTimer;

    public EntityAIEatFlower(EntityLiving flowerEaterEntityIn) {
        this.flowerEaterEntity = flowerEaterEntityIn;
        this.entityWorld = flowerEaterEntityIn.world;
        this.setMutexBits(7);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return this.flowerEaterEntity.getRNG().nextInt(this.flowerEaterEntity.isChild() ? 50 : 1000) == 0 && WorldUtil.isEntityOnFlower(this.flowerEaterEntity);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.eatingFlowerTimer = 40;
        this.entityWorld.setEntityState(this.flowerEaterEntity, (byte) 10);
        this.flowerEaterEntity.getNavigator().clearPath();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
    public void resetTask() {
        this.eatingFlowerTimer = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        return this.eatingFlowerTimer > 0;
    }

    /**
     * Number of ticks since the entity started to eat flowers
     */
    public int getEatingFlowerTimer() {
        return this.eatingFlowerTimer;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
        this.eatingFlowerTimer = Math.max(0, this.eatingFlowerTimer - 1);

        if (this.eatingFlowerTimer == 4) {
            BlockPos blockpos = getBlockPos();

            if (WorldUtil.isEntityOnFlower(this.flowerEaterEntity)) {

                EnumDyeColor color = DyeUtil.getDyeForFlowerAt(this.entityWorld, blockpos);
                if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
                    this.entityWorld.destroyBlock(blockpos, false);
                }

                this.flowerEaterEntity.eatGrassBonus();

                if (this.flowerEaterEntity instanceof EntitySheep && ConfigHandler.general.sheepAbsorbColor) {
                    EntitySheep sheep = (EntitySheep) this.flowerEaterEntity;
                    sheep.setFleeceColor(color);
                }
            }
        }
    }

    private BlockPos getBlockPos() {
        return new BlockPos(this.flowerEaterEntity.posX, this.flowerEaterEntity.posY, this.flowerEaterEntity.posZ);
    }

}
