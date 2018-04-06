package xyz.poketech.diy.client.render.layer;

import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import xyz.poketech.diy.util.color.NBTColorUtil;
import xyz.poketech.diy.util.color.ColorUtil;

/**
 * Layer to override the sheep wool
 * Based on {@link net.minecraft.client.renderer.entity.layers.LayerSheepWool}
 */
public class LayerSheepWoolOverride implements LayerRenderer<EntitySheep> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
    private final RenderSheep sheepRenderer;
    private final ModelSheep1 sheepModel = new ModelSheep1();

    public LayerSheepWoolOverride(RenderSheep sheepRendererIn) {
        this.sheepRenderer = sheepRendererIn;
    }

    public void doRenderLayer(EntitySheep entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {

            //Only render if a color is set
            if (entitylivingbaseIn.getEntityData().hasKey(NBTColorUtil.COLOR_KEY)) {

                boolean isJeb = entitylivingbaseIn.hasCustomName() && "jeb_".equals(entitylivingbaseIn.getCustomNameTag());

                //Don't render for jeb_ sheeps
                if (!isJeb) {
                    this.sheepRenderer.bindTexture(TEXTURE);
                    //float[] afloat = EntitySheep.getDyeRgb(entitylivingbaseIn.getFleeceColor());
                    ColorUtil.setGLColorFromInt(entitylivingbaseIn.getEntityData().getInteger(NBTColorUtil.COLOR_KEY));
                }

                this.sheepModel.setModelAttributes(this.sheepRenderer.getMainModel());
                this.sheepModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
                this.sheepModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            }
        }
    }

    public boolean shouldCombineTextures() {
        return true;
    }
}
