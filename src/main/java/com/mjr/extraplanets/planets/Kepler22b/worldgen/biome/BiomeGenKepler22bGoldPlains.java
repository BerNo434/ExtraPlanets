package com.mjr.extraplanets.planets.Kepler22b.worldgen.biome;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.planets.Kepler22b.worldgen.Kepler22bBiomes;

import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenKepler22bGoldPlains extends Kepler22bBiomes {
	public BiomeGenKepler22bGoldPlains(int var1) {
		super(var1);
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.registerBiomeType(this, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.LUSH);
		this.topBlock = ExtraPlanets_Blocks.GOLD_GRIT.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.GOLD_GRIT.getDefaultState();
		this.getBiomeDecorator().goldTreesPerChunk = 10;
		this.getBiomeDecorator().goldSpheresPerChunk = 2;
	}
}