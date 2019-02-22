package com.mjr.extraplanets.planets.Kepler22b.worldgen.biome;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.planets.Kepler22b.worldgen.Kepler22bBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenKepler22bIronPlains extends Kepler22bBiomes {
	public BiomeGenKepler22bIronPlains(BiomeProperties properties) {
		super(properties);
		Biome.registerBiome(Config.KEPLER22B_IRON_PLAINS_BIOME_ID, Constants.TEXTURE_PREFIX + this.getBiomeName(), this);
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.addTypes(this, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.LUSH);
		this.topBlock = ExtraPlanets_Blocks.IRON_GRIT.getDefaultState();
		this.fillerBlock = ExtraPlanets_Blocks.IRON_GRIT.getDefaultState();
		this.getBiomeDecorator().ironTreesPerChunk = 10;
		this.getBiomeDecorator().ironSpheresPerChunk = 2;
	}
}