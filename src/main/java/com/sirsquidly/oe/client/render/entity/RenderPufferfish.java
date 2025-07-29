package com.sirsquidly.oe.client.render.entity;

import org.lwjgl.opengl.GL11;

import com.sirsquidly.oe.Main;
import com.sirsquidly.oe.client.model.entity.ModelPufferfish;
import com.sirsquidly.oe.entity.EntityPufferfish;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPufferfish extends RenderLiving<EntityPufferfish>
{
	public static final ResourceLocation PUFF1_TEXTURES = new ResourceLocation(Main.MOD_ID + ":textures/entities/pufferfish1.png");
	public static final ResourceLocation PUFF2_TEXTURES = new ResourceLocation(Main.MOD_ID + ":textures/entities/pufferfish2.png");
	public static final ResourceLocation PUFF3_TEXTURES = new ResourceLocation(Main.MOD_ID + ":textures/entities/pufferfish3.png");
	
	public RenderPufferfish(RenderManager manager)
    {
        super(manager, new ModelPufferfish(), 0.3F);
    }

	@Override
	protected void preRenderCallback(EntityPufferfish entity, float f) {
		float size = 0.9375F;
		if (entity.getGrowingAge() < 0)
        {
			size = (float)((double)size * 0.5D);
            this.shadowSize = 0.25F;
        }
		GlStateManager.scale(size, size, size);
	}

	
	protected ResourceLocation getEntityTexture(EntityPufferfish entity) {
		if (entity.getPuffState() == 0)
        {
            return PUFF1_TEXTURES;
        }
        else if (entity.getPuffState() == 1)
        {
        	return PUFF2_TEXTURES;
        }
        else
        {
        	return PUFF3_TEXTURES;
        }
	}

	protected void applyRotations(EntityPufferfish entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
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
