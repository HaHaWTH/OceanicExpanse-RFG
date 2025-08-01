package com.sirsquidly.oe.client.render.entity.layer;

import com.sirsquidly.oe.client.render.entity.RenderDrowned;
import com.sirsquidly.oe.entity.EntityDrowned;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerDrowned<T extends EntityDrowned>  implements LayerRenderer<T>
{
	private final RenderDrowned<T> drownedRenderer;
	
	public LayerDrowned(RenderDrowned<T> DrownedRendererIn)
    {
		this.drownedRenderer = DrownedRendererIn;
    }

	// Playing with the Alpha causes the entire mob to glow, so theya re commented out
	public void doRenderLayer(T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	 {
        this.drownedRenderer.bindTexture(this.drownedRenderer.getEntityEmissiveTexture(entity));
		GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entity.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.drownedRenderer.getMainModel().setModelAttributes(this.drownedRenderer.getMainModel());
        this.drownedRenderer.getMainModel().setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
        this.drownedRenderer.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        this.drownedRenderer.setLightmap(entity);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
    }

    public boolean shouldCombineTextures()
    { return false; }
}
