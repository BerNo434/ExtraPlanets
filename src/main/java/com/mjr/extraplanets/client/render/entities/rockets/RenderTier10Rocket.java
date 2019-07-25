package com.mjr.extraplanets.client.render.entities.rockets;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.FMLClientHandler;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

public class RenderTier10Rocket extends Render {
	protected ResourceLocation rocketTexture;
	protected ResourceLocation rocketTexture2;
	protected ResourceLocation rocketTexture3;
	protected ResourceLocation rocketTexture4;

	protected IModelCustom rocketModelObj;

	public RenderTier10Rocket(IModelCustom spaceshipModel, String textureDomain, String texture) {
		this.rocketModelObj = spaceshipModel;
		this.rocketTexture = new ResourceLocation(textureDomain, "textures/model/" + texture + ".png");
		this.rocketTexture2 = new ResourceLocation(textureDomain, "textures/model/blank_rocket.png");
		this.rocketTexture3 = new ResourceLocation(textureDomain, "textures/model/blank_rocket_grey.png");
		this.rocketTexture4 = new ResourceLocation(textureDomain, "textures/model/blank_rocket_dark_grey.png");
		this.shadowSize = 2F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.rocketTexture;
	}

	@SuppressWarnings("unused")
	public void renderSpaceship(EntitySpaceshipBase entity, double par2, double par4, double par6, float par8, float par9) {
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPushMatrix();
		final float var24 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9 + 180;
		final float var25 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9 + 45;

		GL11.glTranslatef((float) par2, (float) par4 - 1.2F, (float) par6);
		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
		final float var28 = entity.rollAmplitude / 3 - par9;
		float var30 = entity.shipDamage - par9;

		if (var30 < 0.0F) {
			var30 = 0.0F;
		}

		if (var28 > 0.0F) {
			final float i = entity.getLaunched() ? (5 - MathHelper.floor_double(entity.timeUntilLaunch / 85)) / 10F : 0.3F;
			GL11.glRotatef(MathHelper.sin(var28) * var28 * i * par9, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(MathHelper.sin(var28) * var28 * i * par9, 1.0F, 0.0F, 1.0F);
		}

		this.bindEntityTexture(entity);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		GL11.glScalef(0.7F, 0.7F, 0.7F);

		this.rocketModelObj.renderPart("RocketCockpit");
		this.rocketModelObj.renderPart("RoofCockpit");
		this.rocketModelObj.renderPart("Fuel_valve1");
		this.rocketModelObj.renderPart("Fuel_valve002");
		this.rocketModelObj.renderPart("Fuel_valve003");
		this.rocketModelObj.renderPart("Fuel_valve004");
		this.bindTexture(rocketTexture2);
		this.rocketModelObj.renderPart("NoseRocket");
		this.rocketModelObj.renderPart("Nozzle001");
		this.rocketModelObj.renderPart("NozzleKeeper001");
		this.rocketModelObj.renderPart("NozzleKeeper002");
		this.rocketModelObj.renderPart("RocketEngine004");
		this.rocketModelObj.renderPart("RocketEngine005");
		this.rocketModelObj.renderPart("RocketEngine006");
		this.rocketModelObj.renderPart("RocketEngine007");
		this.bindTexture(rocketTexture4);
		this.rocketModelObj.renderPart("RocketEngineBottom004");
		this.rocketModelObj.renderPart("RocketEngineBottom005");
		this.rocketModelObj.renderPart("RocketEngineBottom006");
		this.rocketModelObj.renderPart("RocketEngineBottom007");
		this.bindTexture(rocketTexture);
		this.rocketModelObj.renderPart("FloorCockPit");
		this.bindTexture(rocketTexture4);
		Vector3 teamColor = ClientUtil.updateTeamColor(FMLClientHandler.instance().getClient().thePlayer.getCommandSenderName(), true);
		if (teamColor != null) {
			GL11.glColor3f(teamColor.floatX(), teamColor.floatY(), teamColor.floatZ());
		}
		this.rocketModelObj.renderPart("RocketEnginePlut004");
		this.rocketModelObj.renderPart("RocketEnginePlut005");
		this.rocketModelObj.renderPart("RocketEnginePlut006");
		this.rocketModelObj.renderPart("RocketEnginePlut007");

		if (FMLClientHandler.instance().getClient().thePlayer.ticksExisted / 10 % 2 < 1) {
			GL11.glColor3f(1, 0, 0);
		} else {
			GL11.glColor3f(0, 1, 0);
		}

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.rocketModelObj.renderPart("Light");
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);

		GL11.glColor3f(1, 1, 1);

		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderSpaceship((EntitySpaceshipBase) par1Entity, par2, par4, par6, par8, par9);
	}
}