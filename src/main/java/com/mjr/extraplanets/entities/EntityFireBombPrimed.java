package com.mjr.extraplanets.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFireBombPrimed extends EntityTNTPrimed { // TODO: Clean up
	/** How long the fuse is */
	public int fuse;
	private EntityLivingBase tntPlacedBy;

	public EntityFireBombPrimed(World world) {
		super(world);
		this.fuse = 80;
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
	}

	public EntityFireBombPrimed(World world, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, EntityLivingBase p_i1730_8_) {
		this(world);
		this.setPosition(p_i1730_2_, p_i1730_4_, p_i1730_6_);
		float f = (float) (Math.random() * Math.PI * 2.0D);
		this.motionX = -((float) Math.sin(f)) * 0.02F;
		this.motionY = 0.20000000298023224D;
		this.motionZ = -((float) Math.cos(f)) * 0.02F;
		this.fuse = 80;
		this.prevPosX = p_i1730_2_;
		this.prevPosY = p_i1730_4_;
		this.prevPosZ = p_i1730_6_;
		this.tntPlacedBy = p_i1730_8_;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if (this.fuse-- <= 0) {
			this.setDead();

			if (!this.world.isRemote) {
				this.explode();
			}
		} else {
			this.handleWaterMovement();
			this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

	private void explode() {
		float f = 5.0F;
		this.world.createExplosion(this, this.posX, this.posY + this.height / 16.0F, this.posZ, f, true);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 15; j++) {
				if (this.rand.nextInt(3) == 1)
					this.world.setBlockState(new BlockPos((int) this.posX + i, this.world.getTopSolidOrLiquidBlock(new BlockPos((int) this.posX + i, 0, (int) this.posZ + j)).getY(), (int) this.posZ + j), Blocks.FIRE.getDefaultState());
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.rand.nextInt(3) == 1)
					this.world.setBlockState(new BlockPos((int) this.posX - i, this.world.getTopSolidOrLiquidBlock(new BlockPos((int) this.posX + i, 0, (int) this.posZ + j)).getY(), (int) this.posZ - j), Blocks.FIRE.getDefaultState());
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.rand.nextInt(3) == 1)
					this.world.setBlockState(new BlockPos((int) this.posX + i, this.world.getTopSolidOrLiquidBlock(new BlockPos((int) this.posX + i, 0, (int) this.posZ + j)).getY(), (int) this.posZ - j), Blocks.FIRE.getDefaultState());
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.rand.nextInt(3) == 1)
					this.world.setBlockState(new BlockPos((int) this.posX - i, this.world.getTopSolidOrLiquidBlock(new BlockPos((int) this.posX + i, 0, (int) this.posZ + j)).getY(), (int) this.posZ + j), Blocks.FIRE.getDefaultState());
			}
		}
	}

	/**
	 * returns null or the entityliving it was placed or ignited by
	 */
	@Override
	public EntityLivingBase getTntPlacedBy() {
		return this.tntPlacedBy;
	}
}