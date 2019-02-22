package com.mjr.extraplanets.moons.Oberon.worldgen.biomes;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.blocks.ExtraPlanets_Blocks;
import com.mjr.extraplanets.moons.Oberon.worldgen.OberonBiomes;

import net.minecraftforge.common.BiomeDictionary;

public class BiomeGenOberonValleys extends OberonBiomes {

	public BiomeGenOberonValleys(int var1) {
		super(var1);
		if (Config.REGISTER_BIOME_TYPES)
			BiomeDictionary.registerBiomeType(this, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
		this.topBlock = ExtraPlanets_Blocks.OBERON_BLOCKS.getStateFromMeta(1);
		this.fillerBlock = ExtraPlanets_Blocks.OBERON_BLOCKS.getStateFromMeta(2);
	}
}
