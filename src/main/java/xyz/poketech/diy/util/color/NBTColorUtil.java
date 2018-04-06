package xyz.poketech.diy.util.color;

import net.minecraft.entity.Entity;

import java.awt.*;

public class NBTColorUtil {

    public static final String COLOR_KEY = "diy_color";

    public static void setEntityColor(Entity entity, int color) {
        entity.getEntityData().setInteger(COLOR_KEY, color);
    }

    public static void setEntityColor(Entity entity, int r, int g, int b) {
        setEntityColor(entity, ColorUtil.getRGB(r, g, b));
    }

    public static void removeEntityColor(Entity entity) {
        entity.getEntityData().removeTag(COLOR_KEY);
    }
}
