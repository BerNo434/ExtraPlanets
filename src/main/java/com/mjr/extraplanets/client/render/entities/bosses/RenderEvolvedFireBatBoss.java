package com.mjr.extraplanets.client.render.entities.bosses;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.client.model.bosses.ModelEvolvedFireBatBoss;
import com.mjr.extraplanets.entities.bosses.EntityEvolvedFireBatBoss;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEvolvedFireBatBoss extends RenderLiving<EntityEvolvedFireBatBoss> {
	private static final ResourceLocation BAT_TEXTURES = new ResourceLocation(Constants.TEXTURE_PREFIX + "textures/entity/fire_bat.png");

	public RenderEvolvedFireBatBoss(RenderManager renderManager) {
		super(renderManager, new ModelEvolvedFireBatBoss(), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityEvolvedFireBatBoss entity) {
		return BAT_TEXTURES;
	}

	@Override
	protected void preRenderCallback(EntityEvolvedFireBatBoss entityLiving, float partialTickTime) {
		GlStateManager.scale(8.35F, 8.35F, 8.35F);
		GlStateManager.translate(0.0F, 0.9F, 0.0F);
	}

	@Override
	protected void rotateCorpse(EntityEvolvedFireBatBoss entityLiving, float p_77043_2_, float p_77043_3_, float partialTicks) {
		GlStateManager.translate(0.0F, MathHelper.cos(p_77043_2_ * 0.3F) * 0.2F, 0.0F);
		super.rotateCorpse(entityLiving, p_77043_2_, p_77043_3_, partialTicks);
	}
}