package xyz.poketech.diy.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class DyeUtil {

    private static Map<IBlockState, EnumDyeColor> stateColorCache = new HashMap<>();

    public static EnumDyeColor getDyeForFlowerAt(World world, BlockPos pos) {

    	IBlockState state = world.getBlockState(pos);
    	
    	// If possible, return early from the cache.
    	if (stateColorCache.containsKey(state)) {
    		
    		return stateColorCache.get(state);
    	}
    	
        //Grab the flower as an ItemStack
        ItemStack stack = WorldUtil.getItemStackForBlockAt(world, pos, state);

        ItemStack dye = getFlowerDye(stack, world);
        EnumDyeColor color = EnumDyeColor.byDyeDamage(dye.getMetadata());
        stateColorCache.put(state, color);
        return color;
    }

    public static ItemStack getFlowerDye(ItemStack stack, World world) {
        //Simulate the crafting of a dye from a flower
        InventoryCrafting ic = new InventoryCrafting(new Container() {
            public boolean canInteractWith(EntityPlayer playerIn) {
                return false;
            }
        }, 1, 1);
        ic.setInventorySlotContents(0, stack);
        return CraftingManager.findMatchingResult(ic, world);
    }
}
