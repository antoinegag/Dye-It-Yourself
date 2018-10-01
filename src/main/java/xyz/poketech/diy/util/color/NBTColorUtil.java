package xyz.poketech.diy.util.color;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.awt.*;

public class NBTColorUtil {

    public static final String COLOR_KEY = "diy_color";
    private static final int WHITE = ColorUtil.getRGB(255, 255, 255);

    public static void setEntityColor(Entity entity, int color) {
        entity.getEntityData().setInteger(COLOR_KEY, color);
    }

    public static void setEntityColor(Entity entity, int r, int g, int b) {
        setEntityColor(entity, ColorUtil.getRGB(r, g, b));
    }

    public static void removeEntityColor(Entity entity) {
        entity.getEntityData().removeTag(COLOR_KEY);
    }

    public static int getColor(ItemStack stack) {
        if(stack.getTagCompound() != null) {
            if(stack.getTagCompound().hasKey(COLOR_KEY)) {
                return stack.getTagCompound().getInteger(COLOR_KEY);
            } else {
                stack.getTagCompound().setInteger(COLOR_KEY, WHITE);
            }
        } else {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger(COLOR_KEY, WHITE);
            stack.setTagCompound(tag);
        }
        return WHITE;
    }
}
