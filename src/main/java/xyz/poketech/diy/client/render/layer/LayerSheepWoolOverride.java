package xyz.poketech.diy.client.render.layer;

import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import xyz.poketech.diy.util.color.NBTColorUtil;
import xyz.poketech.diy.util.color.ColorUtil;

import java.util.Random;

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

                boolean isJeb = false;
                boolean isDisco = false;

                if(entitylivingbaseIn.hasCustomName()) {
                    if("jeb_".equals(entitylivingbaseIn.getCustomNameTag())) {
                        return; //Don't render on top of jeb sheeps
                    }

                    if("Blu".equalsIgnoreCase(entitylivingbaseIn.getCustomNameTag()) || "Hyper".equalsIgnoreCase(entitylivingbaseIn.getCustomNameTag()) ) {
                        Tessellator tessellator = Tessellator.getInstance();
                        BufferBuilder bufferbuilder = tessellator.getBuffer();
                        RenderHelper.disableStandardItemLighting();
                        float f = (ageInTicks + partialTicks) / 30;
                        float f1 = 0.0F;

                        Random random = new Random(432L);
                        GlStateManager.disableTexture2D();
                        GlStateManager.shadeModel(7425);
                        GlStateManager.enableBlend();
                        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
                        GlStateManager.disableAlpha();
                        GlStateManager.enableCull();
                        GlStateManager.depthMask(false);
                        GlStateManager.pushMatrix();
                        GlStateManager.translate(0.0F, 0.5f, 0);

                        for (int i = 0; (float)i < 15; ++i)
                        {
                            GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                            GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                            GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
                            float f2 = random.nextFloat() * 3.0F + f1 * 2.0F;
                            float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
                            bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
                            bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(255, 255, 255, 255).endVertex();
                            bufferbuilder.pos(-0.266D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(255, 0, 0, 255).endVertex();
                            bufferbuilder.pos(0.266D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(0, 255, 0, 255).endVertex();
                            bufferbuilder.pos(0.0D, (double)f2, (double)(1.0F * f3)).color(255, 0, 255, 0).endVertex();
                            bufferbuilder.pos(-0.266D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(0, 0, 255, 255).endVertex();
                            tessellator.draw();
                        }

                        GlStateManager.popMatrix();
                        GlStateManager.depthMask(true);
                        GlStateManager.disableCull();
                        GlStateManager.disableBlend();
                        GlStateManager.shadeModel(7424);
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        GlStateManager.enableTexture2D();
                        GlStateManager.enableAlpha();
                        RenderHelper.enableStandardItemLighting();
                    }
                }

                this.sheepRenderer.bindTexture(TEXTURE);
                //float[] afloat = EntitySheep.getDyeRgb(entitylivingbaseIn.getFleeceColor());
                ColorUtil.setGLColorFromInt(entitylivingbaseIn.getEntityData().getInteger(NBTColorUtil.COLOR_KEY));
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
