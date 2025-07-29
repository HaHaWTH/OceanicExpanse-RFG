package com.sirsquidly.oe.client.render.entity;

import com.sirsquidly.oe.Main;
import com.sirsquidly.oe.client.model.entity.ModelTurtle;
import com.sirsquidly.oe.entity.EntityTurtle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTurtle extends RenderLiving<EntityTurtle>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MOD_ID + ":textures/entities/turtle.png");
	
	public RenderTurtle(RenderManager manager)
    {
        super(manager, new ModelTurtle(), 0.8F);
    }

	@Override
	protected void preRenderCallback(EntityTurtle entity, float f) 
	{
		float size = 0.9375F + (entity.isCarryingEgg() ? 0.140625F : 0);
		this.shadowSize = 0.8F;
		
		if (entity.getGrowingAge() < 0)
        {
			size = (float)((double)size * 0.2D);
            this.shadowSize = 0.2F;
        }
		GlStateManager.scale(size, size, size);
	}

	
	protected ResourceLocation getEntityTexture(EntityTurtle entity) {
		return TEXTURES;
	}

    protected void applyRotations(EntityTurtle entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
    }
}
