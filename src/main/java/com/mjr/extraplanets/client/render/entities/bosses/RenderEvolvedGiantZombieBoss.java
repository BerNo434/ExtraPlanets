package com.mjr.extraplanets.client.render.entities.bosses;

import com.mjr.extraplanets.entities.bosses.EntityEvolvedGiantZombieBoss;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEvolvedGiantZombieBoss extends RenderBiped<EntityEvolvedGiantZombieBoss> {
	private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");

	public RenderEvolvedGiantZombieBoss(RenderManager renderManager) {
		super(renderManager, new ModelZombie(), 0.5F);
		LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
			@Override
			protected void initArmor() {
				this.modelLeggings = new ModelZombie(0.5F, true);
				this.modelArmor = new ModelZombie(1.0F, true);
			}
		};
		this.addLayer(layerbipedarmor);
	}

	/**
	 * Allows the render to do state modifications necessary before the model is rendered.
	 */
	@Override
	protected void preRenderCallback(EntityEvolvedGiantZombieBoss entityLiving, float partialTickTime) {
		super.preRenderCallback(entityLiving, partialTickTime);
		GlStateManager.scale(4.35F, 4.35F, 4.35F);
		GlStateManager.rotate((float) (Math.pow(entityLiving.deathTicks, 2) / 5.0F + (Math.pow(entityLiving.deathTicks, 2) / 5.0F - Math.pow(entityLiving.deathTicks - 1, 2) / 5.0F) * partialTickTime), 0.0F, 1.0F, 0.0F);
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	@Override
	public void doRender(EntityEvolvedGiantZombieBoss entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityEvolvedGiantZombieBoss entity) {
		return ZOMBIE_TEXTURES;
	}
}