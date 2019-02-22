package com.mjr.extraplanets.planets.Ceres.worldgen;

import com.mjr.extraplanets.Config;

import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenCeres extends CeresBiomes {

	public BiomeGenCeres(int var1) {
		super(var1);
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.registerBiomeType(this, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
	}
}
