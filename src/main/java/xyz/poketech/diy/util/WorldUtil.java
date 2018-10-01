package xyz.poketech.diy.util;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldUtil {

    /**
     * Checks if a position is a flower
     *
     * @param world the world to check in
     * @param pos   the position to check
     * @return if there's a flower at the given position
     */
    public static boolean isFlower(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() instanceof BlockFlower;
    }

    /**
     * Checks if the entity is on a flower
     *
     * @param entity
     * @return
     */
    public static boolean isEntityOnFlower(Entity entity) {
        return isFlower(entity.world, new BlockPos(entity.posX, entity.posY, entity.posZ));
    }

    /**
     * Returns the ItemStack of a flower for a given position
     *
     * @param world the world in which the flower is
     * @param pos   the position of the flower
     * @return the ItemStack of the flower
     */
    public static ItemStack getItemStackForBlockAt(World world, BlockPos pos, IBlockState state) {
        return world.getBlockState(pos).getBlock().getPickBlock(state, null, world, pos, null);
    }


    public static void spawnStack(World world, BlockPos pos, ItemStack stack) {
        world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack));
    }

    public static void spawnItem(World world, BlockPos pos, Item item, int meta) {
        //TODO: change the ammount in the config (or let it be random)
        world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(item, 1, meta)));
    }
}
