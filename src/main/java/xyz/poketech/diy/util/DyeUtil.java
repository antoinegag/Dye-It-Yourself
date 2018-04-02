package xyz.poketech.diy.util;

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


    private static Map<Integer, EnumDyeColor> cache = new HashMap<>();

    public static EnumDyeColor getDyeForFlowerAt(World world, BlockPos pos) {

        //Grab the flower as an ItemStack
        ItemStack stack = WorldUtil.getItemStackForBlockAt(world, pos);
        ItemStack dye = getFlowerDye(stack, world);
        int meta = dye.getMetadata();
        return cache.computeIfAbsent(stack.getMetadata(), k -> EnumDyeColor.byDyeDamage(meta));
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
