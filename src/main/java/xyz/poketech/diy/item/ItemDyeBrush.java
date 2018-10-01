package xyz.poketech.diy.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import org.apache.commons.lang3.RandomUtils;
import xyz.poketech.diy.DyeItYourself;
import xyz.poketech.diy.network.PacketHandler;
import xyz.poketech.diy.util.color.ColorUtil;
import xyz.poketech.diy.util.color.NBTColorUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDyeBrush extends Item {

    public ItemDyeBrush() {
        setRegistryName("dye_brush");
        setUnlocalizedName(DyeItYourself.MODID + ".dye_brush");
        setMaxStackSize(1);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if(!playerIn.world.isRemote) {
            if(target instanceof EntitySheep) {
                int color = NBTColorUtil.getColor(stack);
                target.getEntityData().setInteger(NBTColorUtil.COLOR_KEY, color);
                PacketHandler.sendColorUpdate(target.getEntityId(), color, target.getPosition(), playerIn.dimension, 25);
                return true;
            }
        }
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!playerIn.world.isRemote && playerIn.isSneaking()) {
            ItemStack itemStack = playerIn.getHeldItem(handIn);

            if(itemStack.getTagCompound() == null) {
                itemStack.setTagCompound(new NBTTagCompound());
            }

            int r = RandomUtils.nextInt(0, 256);
            int g = RandomUtils.nextInt(0, 256);
            int b = RandomUtils.nextInt(0, 256);

            int color = ColorUtil.getRGB(r,g,b);

            itemStack.getTagCompound().setInteger(NBTColorUtil.COLOR_KEY, color);

            playerIn.sendStatusMessage(new TextComponentTranslation("tooltip.diy.current_color", r, g,b), true);
        }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        int[] rgb = ColorUtil.toRGB(NBTColorUtil.getColor(stack));
        tooltip.add("WIP");
        tooltip.add(I18n.format("tooltip.diy.current_color", rgb[0], rgb[1],rgb[2]));
        tooltip.add(I18n.format("item.diy.dye_brush.tooltip"));
    }
}
