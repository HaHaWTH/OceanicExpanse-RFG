package com.sirsquidly.oe.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.sirsquidly.oe.Main;
import com.sirsquidly.oe.client.model.entity.ModelSalmon;
import com.sirsquidly.oe.entity.EntitySalmon;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSalmon extends RenderLiving<EntitySalmon>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MOD_ID + ":textures/entities/salmon.png");
	
	public RenderSalmon(RenderManager manager)
    {
        super(manager, new ModelSalmon(), 0.3F);
    }

	@Override
	protected void preRenderCallback(EntitySalmon entity, float f) {
		float size = 0.9375F + ((float)entity.getSalmonSize() * 0.25F - 0.25F);
		
		if (entity.getGrowingAge() < 0)
        {
			size = (float)((double)size * 0.5D);
            this.shadowSize = 0.25F;
        }
		
		GlStateManager.scale(size, size, size);
	}

	
	protected ResourceLocation getEntityTexture(EntitySalmon entity) {
		return TEXTURES;
	}

	protected void applyRotations(EntitySalmon entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
        
        if (entityLiving.isFlopping())
		{
			float flopSide = MathHelper.sin((p_77043_2_ *0.004F) * 90F) * 90F;
			
			GL11.glRotatef(flopSide, 0F, 0F, 1F);
			GL11.glTranslatef(-flopSide/90 * 0.1F, 0.0F, 0F);
		}
    }
}
