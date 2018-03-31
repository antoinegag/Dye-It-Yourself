package xyz.poketech.diy.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DyeUtil {

    /**
     * Returns the dye given by a flower
     *
     * @param stack the flower itemstack
     * @param world the world in which the conversion happens
     * @return
     */
    public static EnumDyeColor getDyeFromFlower(ItemStack stack, World world) {
        ItemStack item = getDyeItemFromFlower(stack, world);
        return EnumDyeColor.byDyeDamage(item.getMetadata());
    }

    public static ItemStack getDyeItemFromFlower(ItemStack stack, World world) {
        //Simulate the crafting of a dye from a flower

        //TODO Cache the result here
        InventoryCrafting ic = new InventoryCrafting(new Container() {
            public boolean canInteractWith(EntityPlayer playerIn) {
                return false;
            }
        }, 1, 1);
        ic.setInventorySlotContents(0, stack);
        return CraftingManager.findMatchingResult(ic, world);
    }

    /**
     * Gets the dye for a flower at a given position
     *
     * @param world
     * @param pos
     * @return
     */
    public static EnumDyeColor getDyeFromFlowerAt(World world, BlockPos pos) {
        return getDyeFromFlower(WorldUtil.getItemStackForBlockAt(world, pos), world);
    }

}
