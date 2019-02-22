package com.mjr.extraplanets.moons.Deimos.worldgen;

import com.mjr.extraplanets.Config;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.world.biome.BiomeGenBase;

public class DeimosBiomes extends BiomeGenBaseGC {

	public static final BiomeGenBase deimos = new BiomeGenDeimos(Config.DEIMOS_BIOME_ID).setBiomeName("Deimos").setHeight(new Height(2.5F, 0.4F));

	protected DeimosBiomes(int par1) {
		super(par1);
		this.rainfall = 0F;
	}
}
